package com.ecommerce.controller;

import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.config.JwtTokenValidator;
import com.ecommerce.config.UserDetailService;
import com.ecommerce.exception.UserException;
import com.ecommerce.modal.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailService userDetailService;
	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	JwtTokenValidator jwtTokenValidator;
	@Autowired
	CartService cartService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
		System.out.println("Inside Signup.........{} "+user.toString());
		try {
			String email = user.getEmail();
			String password = user.getPassword();
			String firstName=user.getFirstName();
			String lastName=user.getLastName();

				User existingUser = userRepository.findByEmail(email);

				if (existingUser == null) {
					User createdUser= new User();
					createdUser.setEmail(email);
					createdUser.setFirstName(firstName);
					createdUser.setLastName(lastName);
					createdUser.setPassword(passwordEncoder.encode(password));
					createdUser.setRole("User");

//					User newUser = getUserFromMap(user);
					User savedUser = userRepository.save(createdUser);
					cartService.createCart(savedUser);

					// Generate a JWT token for the newly registered user
					Authentication auth = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(email,password)
					);

					if (auth.isAuthenticated()) {
						System.out.println("User registered and authenticated");
						String token = jwtTokenProvider.generateToken(userDetailService.getUserDetails().getEmail(),
								userDetailService.getUserDetails().getRole());
						AuthResponse authResponse = new AuthResponse(token, true);
						return ResponseEntity.ok(authResponse);
					}
				} else {
					throw new UserException("Email Is Already Used With Another Account");
				}

		} catch (Exception ex) {
			System.out.println("exception during signup {}"+ ex);
			throw new UserException("Something went wrong");
		}
		throw new UserException("Internal server error");
	}

//	private boolean validateSignUpMap(User user) {
//		return requestMap.containsKey("firstName") &&
//				requestMap.containsKey("lastName") &&
//				requestMap.containsKey("email") &&
//				requestMap.containsKey("password");
//	}

//	private User getUserFromMap(User userReq) {
//		User user = new User();
//		user.setFirstName(userReq.getFirstName());
//		user.setLastName(userReq.getLastName());
//		user.setEmail(userReq.getEmail());
//		user.setRole("User");
//
//		String password = passwordEncoder.encode(user.getPassword());
//		user.setPassword(password);
//
//		return user;
//	}
	
	@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
		System.out.println("inside login");
		try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
			);
			System.out.println("is authenticated{} "+auth.isAuthenticated());
			if (auth.isAuthenticated()) {
				System.out.println("User registered and authenticated");
				String token = jwtTokenProvider.generateToken(userDetailService.getUserDetails().getEmail(),
						userDetailService.getUserDetails().getRole());
				AuthResponse authResponse = new AuthResponse(token, true);
				return ResponseEntity.ok(authResponse);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BadCredentialsException("Bad credentials");
		}
		throw new InternalError("Internal Server Error");
    }

}
