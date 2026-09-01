package com.rays.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailCtl {

    @Autowired
    private EmailServiceInt emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(
            @RequestBody EmailDTO email) {

        emailService.sendMail(email);

        return ResponseEntity.ok("Mail sent successfully");
    }
}