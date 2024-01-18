package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.exception.ProductException;
import com.ecommerce.modal.Product;
import com.ecommerce.modal.Rating;
import com.ecommerce.modal.User;
import com.ecommerce.repository.RatingRepository;
import com.ecommerce.request.RatingRequest;
import org.springframework.stereotype.Service;


@Service
public class RatingServiceImplementation implements RatingService{
	
	private RatingRepository ratingRepository;
	private ProductService productService;
	
	public RatingServiceImplementation(RatingRepository ratingRepository,ProductService productService) {
		this.ratingRepository=ratingRepository;
		this.productService=productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		
		Product product=productService.findProductById(req.getProductId());
		
		Rating rating=new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllProductsRating(productId);
	}
	
	

}
