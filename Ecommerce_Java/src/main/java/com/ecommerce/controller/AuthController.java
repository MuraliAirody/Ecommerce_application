package com.ecommerce.controller;

import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.config.JwtTokenValidator;
import com.ecommerce.config.UserDetailService;
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
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Map<String, String> requestMap){
		System.out.println("Inside Signup.........{} "+requestMap);
		try {
			if (validateSignUpMap(requestMap)) {
				User existingUser = userRepository.findByEmail(requestMap.get("email"));

				if (existingUser == null) {
					User newUser = getUserFromMap(requestMap);
					User savedUser = userRepository.save(newUser);
					cartService.createCart(savedUser);

					// Generate a JWT token for the newly registered user
					Authentication auth = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
					);

					if (auth.isAuthenticated()) {
						System.out.println("User registered and authenticated");
						String token = jwtTokenProvider.generateToken(userDetailService.getUserDetails().getEmail(),
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
			System.out.println("exception during signup {}"+ ex);
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
			System.out.println("exception during sign in"+ ex);
			AuthResponse authResponse = new AuthResponse("Bad Credentials", false);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
		}
		AuthResponse authResponse = new AuthResponse("Something went wrong", false);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
    }

}
