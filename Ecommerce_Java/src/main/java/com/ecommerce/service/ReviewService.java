package com.ecommerce.service;

import com.ecommerce.exception.ProductException;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReview(Long productId);
}
