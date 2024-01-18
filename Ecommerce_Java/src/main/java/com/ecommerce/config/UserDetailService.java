package com.ecommerce.config;

import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserDetailService implements UserDetailsService {


	@Autowired
	UserRepository userRepository;

	private com.ecommerce.modal.User user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("inside loadUserByUsername..{} "+ username);
		user = userRepository.findByEmail(username);
		if(!Objects.isNull(user)){
			return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("user not found");
		}
	}

	public com.ecommerce.modal.User getUserDetails(){
		System.out.println("getUserDetails");
		com.ecommerce.modal.User user1 = user;
		user1.setPassword(null);
		System.out.println("return getUserDetails{} "+user1);
		return  user1;
	}

}
