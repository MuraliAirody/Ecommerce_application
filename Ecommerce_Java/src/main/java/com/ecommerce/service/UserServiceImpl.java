package com.ecommerce.service;

import com.ecommerce.configuration.JwtUtils;
import com.ecommerce.configuration.UserDetailService;
import com.ecommerce.dao.UserDao;
import com.ecommerce.exception.UserException;
import com.ecommerce.models.User;
import com.ecommerce.response.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailService userDetailService;
    @Autowired
    JwtUtils jwtUtils;


    @Override
    public User findUserById(Long userId) throws UserException {
        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        return null;
    }




    @Override
    public ResponseEntity<AuthResponse> signup(Map<String, String> requestMap) {
        log.info("Inside Signup.........{}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User existingUser = userDao.findByEmail(requestMap.get("email"));

                if (existingUser == null) {
                    User newUser = getUserFromMap(requestMap);
                    userDao.save(newUser);
                    // Generate a JWT token for the newly registered user
                    Authentication auth = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
                    );

                    if (auth.isAuthenticated()) {
                            log.info("User registered and authenticated");
                            String token = jwtUtils.generateToken(userDetailService.getUserDetails().getEmail(),
                                    userDetailService.getUserDetails().getRole());
                            AuthResponse authResponse = new AuthResponse(token, true);
                            return ResponseEntity.ok(authResponse);
                    }
                } else {
                    AuthResponse authResponse = new AuthResponse("Email already exists", false);
                    return ResponseEntity.badRequest().body(authResponse);
                }
            } else {
                AuthResponse authResponse = new AuthResponse("Invalid data", false);
                return ResponseEntity.badRequest().body(authResponse);
            }
        } catch (Exception ex) {
            log.error("Error during signup", ex);
            AuthResponse authResponse = new AuthResponse("Something went wrong", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
        }
        AuthResponse authResponse = new AuthResponse("Something went wrong", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("firstName") &&
                requestMap.containsKey("lastName") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setFirstName(requestMap.get("firstName"));
        user.setLastName(requestMap.get("lastName"));
        user.setEmail(requestMap.get("email"));
        user.setRole("User");

        String password = passwordEncoder.encode(requestMap.get("password"));
        user.setPassword(password);

        return user;
    }
//======================================================================================================
    @Override
    public ResponseEntity<AuthResponse> signIn(Map<String, String> requestMap) {
        log.info("inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            log.info("is authenticated{}",auth.isAuthenticated());
            if (auth.isAuthenticated()) {
                log.info("User registered and authenticated");
                String token = jwtUtils.generateToken(userDetailService.getUserDetails().getEmail(),
                        userDetailService.getUserDetails().getRole());
                AuthResponse authResponse = new AuthResponse(token, true);
                return ResponseEntity.ok(authResponse);
            }
        } catch (Exception ex) {
            log.error("exception ", ex);
            AuthResponse authResponse = new AuthResponse("Bad Credentials", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
        }
        AuthResponse authResponse = new AuthResponse("Something went wrong", false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);    }
}
