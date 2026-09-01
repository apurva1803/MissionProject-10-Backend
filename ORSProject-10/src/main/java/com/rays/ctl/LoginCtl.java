package com.rays.ctl;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.common.UserContext;
import com.rays.config.JWTUtil;
import com.rays.dto.UserDTO;
import com.rays.email.EmailDTO;
import com.rays.email.EmailServiceImpl;
import com.rays.form.ForgetPasswordForm;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	public EmailServiceImpl emailSender;

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult) throws Exception {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());

		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid Login ID & Password");
		} else {

			UserContext context = new UserContext(dto);

			String token = jwtUtil.generateToken(dto.getId(), dto.getLoginId(), dto.getRoleName());

			res.setSuccess(true);
			res.addData(dto);
			res.addResult("loginId", dto.getLoginId());
			res.addResult("role", dto.getRoleName());
			res.addResult("fname", dto.getFirstName());
			res.addResult("lname", dto.getLastName());
			res.addResult("token", token);
			return res;
		}
		return res;
	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult) {

		ORSResponse res = validate(bindingResult);

		if (!res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.findByLoginId(form.getLoginId(), userContext);

		if (dto != null) {
			res.setSuccess(false);
			res.addMessage("Login Id already exists");
			return res;
		}

		dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setGender(form.getGender());
		dto.setPhone(form.getPhone());

		dto.setStatus("Inactive");
		dto.setRoleId(2L);

		baseService.register(dto, userContext);

		res.setSuccess(true);
		res.addMessage("User has been registered successfully..!!");
		return res;
	}

	@GetMapping("logout")
	public ORSResponse logout(HttpSession session) throws Exception {

		ORSResponse res = new ORSResponse();

		session.invalidate();

		res.addMessage("Logout successfully..!!");

		return res;
	}

	@GetMapping("fp/{login}")
	public ORSResponse forgotPassword(@PathVariable String login, HttpServletRequest request) {
		System.out.println("Forget password get run " + login);
		Enumeration<String> e = request.getHeaderNames();
		String key = null;
		while (e.hasMoreElements()) {
			key = e.nextElement();
			System.out.println(key + " = " + request.getHeader(key));
		}

		ORSResponse res = new ORSResponse(true);
		UserDTO dto = this.baseService.forgotPassword(login);
		if (dto == null) {
			res.setSuccess(false);
			res.addMessage("Invalid Login Id");
		} else {
			res.setSuccess(true);
			res.addMessage("Password has been sent to email id");
		}
		return res;
	}
	
	@PostMapping("forgetPassword")
	public ORSResponse forgetPassword(
	        @RequestBody @Valid ForgetPasswordForm form,
	        BindingResult bindingResult) {

	    ORSResponse res = validate(bindingResult);

	    if (!res.isSuccess()) {
	        return res;
	    }

	    // Find user by login ID
	    UserDTO userDto = baseService.findByLoginId(form.getLoginId(), userContext);

	    if (userDto == null) {
	        res.setSuccess(false);
	        res.addMessage("Invalid Login ID");
	        return res;
	    }

	    // Create email object
	    EmailDTO email = new EmailDTO();

	    email.setTo(userDto.getLoginId());
	    email.setSubject("Forgot Password");
	    email.setMessage(
	            "Hello " + userDto.getFirstName() + ",\n\n"
	            + "Your password is: " + userDto.getPassword()
	            + "\n\nRegards,\nORS Team"
	    );

	    // Send email
	    emailSender.sendMail(email);

	    res.setSuccess(true);
	    res.addMessage("Password has been sent to your registered email");

	    return res;
	}
}