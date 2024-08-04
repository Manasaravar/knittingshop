package com.korniushin.eshop.controllers;

import com.korniushin.eshop.model.dao.interfaces.OrderPositionService;
import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class OrdersAdminController {

    private final OrderService orderService;
    private final OrderPositionService orderPositionService;


    @GetMapping("/orders")
    public String ordersPage (Model model) {

        List<Order> orders = orderService.all();
        Order order = new Order();
        model.addAttribute("orders", orders);
        model.addAttribute("order", order);
        return "orders";
    }

    @PostMapping("/orders")
    public String add(Order order) {
        orderService.save(order);
        return "redirect:/admin/orders";
    }

    @GetMapping("/orders/{id}")

    public String getEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderService.findById(id).get());
        model.addAttribute("orders", orderService.all());
        return "orders";
    }

    @PostMapping("/orders/{id}")

    public String update(Order order) {
        order.setProductsPositions(orderPositionService.findByOrderId(order.getId()));
        orderService.update(order);
        return "redirect:/admin/orders";
    }

    @PostMapping("/orders/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }

}
