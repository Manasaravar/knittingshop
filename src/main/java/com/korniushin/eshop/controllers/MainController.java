package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index() {return "redirect:/index";}

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
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
