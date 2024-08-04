package com.korniushin.eshop.model.dao.interfaces;


import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.OrderPosition;

import java.util.Set;

public interface OrderPositionService extends DAO<OrderPosition> {
    Integer change (Long id, Integer quantity);
    void deleteOrderPositionById (Long id);
    Set<OrderPosition> findByOrderId(Long id);
}
