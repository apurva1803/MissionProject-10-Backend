package com.rays.config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rays.common.UserContext;
import com.rays.common.UserContextHolder;
import com.rays.dto.UserDTO;
import com.rays.service.JWTUserDetailsService;
import com.rays.service.UserServiceInt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private JWTUserDetailsService jwtUserDetailsService;

	@Autowired
	private UserServiceInt userService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			String jwtToken = authorizationHeader.substring(7);

			try {

				String loginId = jwtUtil.extractLoginId(jwtToken);

				if (!jwtUtil.validateToken(jwtToken, loginId)) {
					throw new Exception("Invalid JWT token");
				}

				   if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					   
	                    String role = jwtUtil.extractRole(jwtToken);
	                    
	                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	                            loginId, null,
	                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
	                    );
	                    
	                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                    
	                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	                }

	                UserDTO dto = new UserDTO();
	                dto.setLoginId(loginId);
	                dto.setId(jwtUtil.extractUserId(jwtToken)); 
	                System.out.println("request filter: " + dto.getLoginId());
	                UserContext context = new UserContext(dto);
	                UserContextHolder.setContext(context);
	            } catch (Exception e) {
	                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                response.setContentType("application/json");
	                response.getWriter().write(e.getMessage());
	                return;
	            }
	        } 
	        filterChain.doFilter(request, response);
	    }
	}