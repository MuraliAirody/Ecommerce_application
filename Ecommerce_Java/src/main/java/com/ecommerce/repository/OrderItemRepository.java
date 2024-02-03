package com.ecommerce.repository;

import com.ecommerce.modal.OrderItem;
import com.ecommerce.modal.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItem o WHERE o.product = :product")
    void deleteByProduct(@Param("product") Product product);

}
