package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews, Long> {
    Reviews findReviewsByUser_Username(String username);
}
