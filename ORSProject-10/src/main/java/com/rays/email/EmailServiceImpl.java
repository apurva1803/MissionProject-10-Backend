package com.rays.email;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailServiceInt {

    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(EmailMessage msg) {
        try {

            // Create MIME message
        	MimeMessage mimeMessage = mailSender.createMimeMessage();

            // Helper for setting email properties
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set recipient and subject
            helper.setTo(msg.getTo());
            helper.setSubject(msg.getSubject());

            // Set message content (HTML or Text)
            if (msg.getMessageType() == EmailMessage.HTML_MSG) {
                helper.setText(msg.getMessage(), true); // HTML content
            } else {
                helper.setText(msg.getMessage(), false); // Plain text
            }

            // Send email
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}