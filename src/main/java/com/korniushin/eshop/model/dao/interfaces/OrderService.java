package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderStatus;
import com.korniushin.eshop.model.entities.Product;

import java.util.List;


public interface OrderService extends DAO<Order> {

    void pay(Long id);
    void addPosition(Order order, Product product, Integer quantity);
    void delPosition(Order order, Product product, Integer quantity);
    Order findOrderByUserId (Long id);
    Integer getOrderTotalQuantity(Long id);
    Double getOrderTotalPrice(long id);
    List<Order> findOrdersByUserId (Long id);
    Order findCartByUserId (Long id);

}
