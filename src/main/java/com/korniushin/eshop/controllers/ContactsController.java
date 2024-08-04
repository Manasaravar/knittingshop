package com.korniushin.eshop.controllers;


import com.korniushin.eshop.model.Mail;
import com.korniushin.eshop.model.dao.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor

public class ContactsController {


    private final MailService mailService;

    @GetMapping
    public String getContactsPage () {
        return "contacts";
    }

    @PostMapping("/contactUs")
    public String sendTextMailToUs(String senderName, String senderMail, String subject, String message) {

        try {
            mailService.sendMailToUs(senderName, senderMail, subject, message);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "redirect:/contacts";
    }
}

