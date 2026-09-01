
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
    public void sendMail(EmailDTO email) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true);

            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());

            // true = HTML email
            helper.setText(email.getMessage(), true);

            mailSender.send(mimeMessage);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(
                    "Failed to send email: " + e.getMessage());
        }
    }
}

