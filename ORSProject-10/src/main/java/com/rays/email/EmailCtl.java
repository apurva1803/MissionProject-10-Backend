package com.rays.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(name = "Mail")
public class EmailCtl {

   
    @Autowired
    private EmailServiceInt emailService;

    @GetMapping("/send")
    public String sendMail() {

        // Create email message
        EmailMessage msg = new EmailMessage();
        msg.setTo("cb@gmail.com");
        msg.setSubject("Spring Boot Mail");
        msg.setMessage("Hello, Mail sent successfully!");

        // Send email using service
        emailService.sendMail(msg);

        return "Mail Sent Successfully";
    }
}