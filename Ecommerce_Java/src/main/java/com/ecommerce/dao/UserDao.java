package com.ecommerce.dao;

import com.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);
}
