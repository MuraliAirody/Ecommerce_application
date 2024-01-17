package com.ecommerce.service;

import com.ecommerce.configuration.JwtFilter;
import com.ecommerce.configuration.JwtUtils;
import com.ecommerce.configuration.UserDetailService;
import com.ecommerce.dao.UserDao;
import com.ecommerce.exception.UserException;
import com.ecommerce.models.User;
import com.ecommerce.request.LoginRequest;
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
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public User findUserById(Long userId) throws UserException {
        log.info("find user by ID {}",userId);
        Optional<User> user=userDao.findById(userId);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        log.info("user service");
//        String email= jwtFilter.getCurrentUser();
          String email = jwtUtils.extractUsername(jwt.substring(7));
        log.info("email"+email);

        User user=userDao.findByEmail(email);

        if(user==null) {
            throw new UserException("user not exist with email "+email);
        }
        log.info("email user {}",user.getEmail());
        return user;
    }

}
