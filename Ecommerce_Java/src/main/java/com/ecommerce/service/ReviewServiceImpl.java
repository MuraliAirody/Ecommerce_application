package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.ReviewDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.models.Product;
import com.ecommerce.models.Review;
import com.ecommerce.models.User;
import com.ecommerce.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewDao reviewDao;
    @Autowired
    ProductService productService;
    @Autowired
    ProductDao productDao;
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());
        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

//		product.getReviews().add(review);
        productDao.save(product);
        return reviewDao.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewDao.getAllProductsReview(productId);
    }
}
