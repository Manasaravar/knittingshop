//package com.korniushin.eshop.controllers;
//
//
//import com.korniushin.eshop.model.Mail;
//import com.korniushin.eshop.model.dao.interfaces.MailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/send")
//public class MailController {
//
//    private final MailService mailService;
//
//    @PostMapping("/textMail")
//    public String sendTextMail(String message){
//        Mail mail = new Mail(null, message,null,null);
//  //      try {
//            mailService.sendTextMail(mail);
////        } catch (Exception e) {
////            return e.getMessage();
////        }
//        return "index";
//
//    }
//
////    @PostMapping("/textMail")
////    public String sendTextMail(String toEmail, String subject, String message){
////        Mail mail = new Mail(subject,message,toEmail,null);
////        try {
////            mailService.sendTextMail(mail);
////        } catch (Exception e) {
////            return e.getMessage();
////        }
////        return "redirect:/textMail";
////    }
////
////
////    @PostMapping("/templateMail")
////    public String sendTemplateMail(Mail mail){
////        try {
////
////            mailService.sendTemplateMail(mail);
////        } catch (Exception e) {
////            return e.getMessage();
////        }
////        return "redirect:/templateMail";
////    }
//
//
//
//}
