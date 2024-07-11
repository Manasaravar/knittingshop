package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.interfaces.UserService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderPosition;
import com.korniushin.eshop.model.entities.OrderStatus;
import com.korniushin.eshop.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/account")
@RequiredArgsConstructor

public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;


    @GetMapping
    public String accountPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);

        Order order = orderService.findOrderByUserId(user.getId());

            Set<OrderPosition> orderPositions = order.getProductsPositions();
            model.addAttribute("order", order);
            model.addAttribute("orderPositions", orderPositions);

        return "account";
    }

    @PostMapping("/edit")
    public String clientEdit (User user) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User usertoEdit = userService.findByUsername(username);
        usertoEdit.setUsername(user.getUsername());
        usertoEdit.setPhone(user.getPhone());
        usertoEdit.setEmail(user.getEmail());
        userService.edit(usertoEdit);
        return "redirect:/account";
    }


}
