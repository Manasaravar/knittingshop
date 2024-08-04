package com.korniushin.eshop.model.dao.implementations;


import com.korniushin.eshop.model.Mail;
import com.korniushin.eshop.model.dao.interfaces.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.SystemException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class MailServiceImplementation implements MailService {

        private final static Logger log = LoggerFactory.getLogger(MailServiceImplementation.class);

//        //Template template engine
//
//        private final TemplateEngine templateEngine;

        private final JavaMailSender javaMailSender;

        @Value("${spring.mail.username}")
        private String from;


        @Override
        public void sendTextMail(Mail mail){
            //message
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from); //  Send people's mailbox
            message.setSubject("Отзыв " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))); //title
            message.setTo(from); //Who sent to the other party mailbox
            message.setText(mail.getMessage()); //content

            try {
                javaMailSender.send(message); //send
                System.out.println("Mail Sent Successfully...");
            } catch (MailException e) {
                log.error("Pure text mail failed -> Message: {}",e.getMessage());
                try {
                    throw new SystemException("Mail Send Fail");
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        @Override
        public void sendTemplateMail(Mail mail) {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();

                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setFrom(from);
                helper.setTo(mail.getToEmail());
                helper.setSubject(mail.getSubject());
                helper.setText(mail.getMessage());

                FileSystemResource file
                        = new FileSystemResource(new File(mail.getPathToAttachment()));
                helper.addAttachment("Invoice", file);

                javaMailSender.send(message);
                System.out.println("Mail Sent Successfully...");

            } catch (MessagingException e) {
                log.error("Template email failed -> message: {}",e.getMessage());
                try {
                    throw new SystemException ("Mail Send Fail");
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    @Override
    public void sendMailToUs(String senderName, String senderMail, String subject, String message) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(from);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setTo(from);
            simpleMailMessage.setText(message + "\n" + "с уважением " + senderName + " " + senderMail);

            try {
                javaMailSender.send(simpleMailMessage);
                System.out.println("Mail Sent Successfully...");
            } catch (MailException e) {
                log.error("Pure text mail failed -> Message: {}",e.getMessage());
                try {
                    throw new SystemException("Mail Send Fail");
                } catch (SystemException ex) {
                    throw new RuntimeException(ex);
                }
            }
    }
}



