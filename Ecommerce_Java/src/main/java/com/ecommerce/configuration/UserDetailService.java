package com.ecommerce.configuration;


import com.ecommerce.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private com.ecommerce.models.User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("inside loadUserByUsername..{}",username);
        user = userDao.findByEmail(username);
        if(!Objects.isNull(user)){
            return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    public com.ecommerce.models.User getUserDetails(){
        log.info("getUserDetails");
        com.ecommerce.models.User user1 = user;
        user1.setPassword(null);
        log.info("return getUserDetails{}",user1);
        return  user1;
    }

}
