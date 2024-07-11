package com.korniushin.eshop.model.dao.interfaces;


import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.OrderPosition;

public interface OrderPositionService extends DAO<OrderPosition, Long> {
    Integer change (Long id, Integer quantity);
    void deleteOrderPositionById (Long id);
}
