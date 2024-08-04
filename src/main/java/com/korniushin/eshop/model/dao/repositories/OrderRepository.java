package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByUserId (Long id);
    List<Order> findOrdersByUserId (Long id);

    Order findOrderByUserIdAndOrderStatus (Long id, OrderStatus orderStatus);

}
