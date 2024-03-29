package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.modal.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	@Query("Select r From Rating r where r.product.id=:productId")
	public List<Rating> getAllProductsRating(@Param("productId") Long productId);

}
