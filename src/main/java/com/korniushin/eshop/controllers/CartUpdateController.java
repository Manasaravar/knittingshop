package com.korniushin.eshop.controllers;

import ch.qos.logback.core.model.Model;
import com.korniushin.eshop.model.dao.interfaces.OrderPositionService;
import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderStatus;
import com.korniushin.eshop.model.entities.Product;
import com.korniushin.eshop.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor

public class CartUpdateController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductService userService;

    private final OrderPositionService orderPositionService;

    @PostMapping("/add")
    public void add (@RequestParam Long id, @RequestParam Integer quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orders = orderService.findOrdersByUserId(userService.findById(user.getId()).get().getId());
        Order order = new Order();
        for (Order orderIterator: orders) {
            if (orderIterator.getOrderStatus().equals(OrderStatus.CART)) {
                order = orderIterator;
            }
        }
           Product product = productService.findById(id).get();
           orderService.addPosition(order, product, quantity);

    }

    @PostMapping("/change")
    public Integer change (@RequestParam Long id, @RequestParam Integer quantity) {
        return orderPositionService.change(id,quantity);
    }
}
