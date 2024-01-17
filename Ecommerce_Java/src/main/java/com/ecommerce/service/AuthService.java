package com.ecommerce.service;

import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface AuthService {
    public ResponseEntity<AuthResponse> signUp(@RequestBody Map<String,String> requestMap);

    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest);
}
