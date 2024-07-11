package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.OrderPositionService;
import com.korniushin.eshop.model.dao.repositories.OrderPositionRepository;
import com.korniushin.eshop.model.entities.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPositionImplementation implements OrderPositionService {

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    @Override
    public Integer change(Long id, Integer quantity) {
        OrderPosition persistentOP = orderPositionRepository.findById(id).get();
        persistentOP.setOrderQuantity(persistentOP.getOrderQuantity() + quantity);
        return orderPositionRepository.save(persistentOP).getOrderQuantity();
    }

    @Override
    public void deleteOrderPositionById(Long id) {
        orderPositionRepository.deleteById(id);
    }

    @Override
    public List<OrderPosition> all() {
        return orderPositionRepository.findAll();
    }

    @Override
    public Optional<OrderPosition> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrderPosition save(OrderPosition orderPosition) {
        return orderPositionRepository.save(orderPosition);
    }

    @Override
    public OrderPosition update(OrderPosition orderPosition) {
        return orderPositionRepository.save(orderPosition);
    }

    @Override
    public boolean deleteById(Long id) {
        if (orderPositionRepository.findById(id).isPresent()) {
            orderPositionRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
