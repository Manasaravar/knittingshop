package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.DTO.CategoryDTO;
import com.korniushin.eshop.model.dao.interfaces.ReviewService;
import com.korniushin.eshop.model.dao.repositories.ReviewRepository;
import com.korniushin.eshop.model.entities.Reviews;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplementation implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Reviews> all() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Reviews> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Reviews save(Reviews reviews) {
        return reviewRepository.save(reviews);
    }

    @Override
    public Reviews update(Reviews reviews) {
        Reviews reviewsUpdate = reviewRepository.findById(reviews.getId()).get();
        reviewsUpdate.setComment(reviews.getComment());
        reviewsUpdate.setUser(reviews.getUser());
        return reviewRepository.save(reviews);
    }

    @Override
    public boolean deleteById(Long id) {
        if (reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Reviews findReviewsByUsername(String username) {
        return reviewRepository.findReviewsByUser_Username(username);
    }

    @Override
    public List<Reviews> getReviews(Long productId) {
        List <Reviews> reviews = reviewRepository.findAll().stream()
                .filter(rew -> Objects.equals(rew.getProduct().getId(), productId))
                .toList();
        return reviews;

    }


}
