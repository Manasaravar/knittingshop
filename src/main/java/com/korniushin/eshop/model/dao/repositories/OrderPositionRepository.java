package com.korniushin.eshop.model.dao.repositories;


import com.korniushin.eshop.model.entities.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long>{
    Set<OrderPosition> findOrderPositionsByOrderId(Long orderId);

}
