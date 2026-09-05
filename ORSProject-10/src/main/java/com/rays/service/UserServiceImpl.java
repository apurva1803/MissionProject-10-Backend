package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;
import com.rays.email.EmailBuilder;
import com.rays.email.EmailMessage;
import com.rays.email.EmailServiceInt;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

    
    @Autowired
    private EmailServiceInt emailservice;

   
    @Transactional(readOnly = true)
    public UserDTO findByLoginId(String login, UserContext userContext) {
        return baseDao.findByUniqueKey("loginId", login, userContext);
    }

   
    @Override
    public UserDTO register(UserDTO dto, UserContext userContext) {
        baseDao.add(dto, userContext);

        HashMap<String, String> map = new HashMap<>();
        map.put("login", dto.getLoginId());
        map.put("password", dto.getPassword());
        map.put("firstName", dto.getFirstName());

        EmailMessage msg = new EmailMessage();
        
        msg.setTo(dto.getLoginId());
        msg.setSubject("User Registration Successful");
        msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
        msg.setMessageType(EmailMessage.HTML_MSG);

        emailservice.sendMail(msg);
        return dto;
    }

   
    @Override
    public UserDTO authenticate(String loginId, String password) {
        UserDTO dto = findByLoginId(loginId, null);

        if (dto != null) {
            UserContext userContext = new UserContext(dto);
            if (password.equals(dto.getPassword())) {
                dto.setLastLogin(new Timestamp((new Date()).getTime()));
                dto.setUnsucessfullLoginAttempt(0);
                update(dto, userContext);
                return dto;
            } else {
                dto.setUnsucessfullLoginAttempt(1 + dto.getUnsucessfullLoginAttempt());
                update(dto, userContext);
            }

        }
        return null;
    }

    @Override
    public boolean forgotPassword(String loginId) {

        UserDTO dto = findByLoginId(loginId, null);

        if (dto == null) {
            return false;
        }

        HashMap<String, String> map = new HashMap<>();

        map.put("firstName", dto.getFirstName());
        map.put("lastName", dto.getLastName());
        map.put("login", dto.getLoginId());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getForgetPasswordMessage(map);

        EmailMessage email = new EmailMessage();

        email.setTo(dto.getLoginId());
        email.setSubject("Your Password has been forgotten.....");
        email.setMessage(message);
        email.setMessageType(EmailMessage.HTML_MSG);

        emailservice.sendMail(email);

        return true;
    }

   
    @Override
    public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext) {

        UserDTO dto = findByLoginId(loginId, null);

        dto.setCreatedBy(userContext.getLoginId());

        if (dto != null && oldPassword.equals(dto.getPassword())) {

            dto.setPassword(newPassword);
            update(dto, userContext);

            HashMap<String, String> map = new HashMap<>();

            map.put("firstName", dto.getFirstName());
            map.put("lastName", dto.getLastName());
            map.put("login", dto.getLoginId());
            map.put("password", dto.getPassword());

            String message = EmailBuilder.getChangePasswordMessage(map);

            EmailMessage email = new EmailMessage();
            email.setTo(dto.getLoginId());
            email.setSubject("ORS Password Changed Successfully");
            email.setMessage(message);
            email.setMessageType(EmailMessage.HTML_MSG);

            emailservice.sendMail(email);

            return dto;

        } else {
            return null;
        }
    }
}