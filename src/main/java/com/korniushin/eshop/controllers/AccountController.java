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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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

        List<Order> orders = orderService
                .findOrdersByUserId(userService.findById(user.getId()).get().getId()).stream()
                .filter(order -> !order.getOrderStatus().equals(OrderStatus.CART))
                .collect(Collectors.toList());

            model.addAttribute("orders", orders);

        return "account";
    }

    @PostMapping("/edit")
    public String clientEdit (User user) {

      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      User userToUpdate = userService.findByUsername(username);
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setEmail(user.getEmail());
        userService.edit(userToUpdate);
        return "redirect:/account";
    }


}
