package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Reviews;

import java.util.List;

public interface ReviewService extends DAO<Reviews> {
    Reviews findReviewsByUsername(String username);
    List<Reviews> getReviews(Long id);
}
