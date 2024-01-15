package com.ecommerce.service;

import com.ecommerce.exception.UserException;
import com.ecommerce.models.User;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.AuthResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;

    ResponseEntity<AuthResponse> signup(Map<String, String> requestMap);

    ResponseEntity<AuthResponse> signIn(LoginRequest loginRequest);
}
