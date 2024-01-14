package com.ecommerce.service;

import com.ecommerce.exception.UserException;
import com.ecommerce.models.User;

public class UserServiceImpl implements UserService{
    @Override
    public User findUserById(Long userId) throws UserException {
        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        return null;
    }
}
