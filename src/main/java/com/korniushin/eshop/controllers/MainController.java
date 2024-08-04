package com.korniushin.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index() {return "redirect:/index";}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/delivery")
    public String deliveryPage() {
        return "delivery";
    }

    @GetMapping("/aboutUs")
    public String aboutUsPage() {
        return "aboutUs";
    }
    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }
    @GetMapping("/vacancies")
    public String vacanciesPage(){
        return "vacancies";
    }

}
