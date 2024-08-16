package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.OrderPositionService;
import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;
    private final ProductService userService;
    private final OrderPositionService orderPositionService;

    @GetMapping
    public String cart(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderService.findOrdersByUserId(userService.findById(user.getId()).get().getId());
        Order order = new Order();
        for (Order orderIterator: orders) {
            if (orderIterator.getOrderStatus().equals(OrderStatus.CART)) {
                order = orderIterator;
            }
        }

      //  Order order = orderService.findOrderByUserId(userService.findById(user.getId()).get().getId());

            Set<OrderPosition> orderPositions = order.getProductsPositions();
            model.addAttribute("totalPrice", orderService.getOrderTotalPrice(order.getId()));
            model.addAttribute("totalQuantity", orderService.getOrderTotalQuantity(order.getId()));
            model.addAttribute("order", order);
            model.addAttribute("orderPositions", orderPositions);

            return "cart";

    }

    @PostMapping("/paid/{id}")
    public String paid(@PathVariable Long id) {
        orderService.pay(id);
        return "redirect:/";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable long id) {
        orderPositionService.deleteOrderPositionById(id);
        return "redirect:/cart";
    }

    @PostMapping("/address")

    public String addAddress (Model model, String city, String street, String house, String other) {

        if (Objects.equals(city, "") || Objects.equals(street, "") || Objects.equals(house, "")) {
            model.addAttribute("error", "empty");
            return "/cart";
        } else {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Order order = orderService.findOrderByUserId(userService.findById(user.getId()).get().getId());
            order.setAddress(city + ", " + street + ", " + house + ", " + other);
            orderService.save(order);
            //  }
            return "redirect:/cart";
        }
    }
}
