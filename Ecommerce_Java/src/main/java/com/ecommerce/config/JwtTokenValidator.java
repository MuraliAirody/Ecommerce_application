package com.ecommerce.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
//@Slf4j
public class JwtTokenValidator extends OncePerRequestFilter {


	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserDetailService userDetailService;

	Claims claims = null;
	private String userName = null;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		System.out.println("JwtFilter - > doFilterInternal..");
		if (httpServletRequest.getServletPath().matches("/auth/signin|/auth/signup|/swagger-ui/index.html")) {
			System.out.println("no filter required");
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			System.out.println("filter required");
			String authorizationHeader = httpServletRequest.getHeader("Authorization");
			String token = null;

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7);
				System.out.println("token {} "+token);
				userName = jwtTokenProvider.extractUsername(token);
				System.out.println("username {} "+userName);
				claims = jwtTokenProvider.extractAllClaims(token);
				System.out.println("claims {} "+claims);
			}
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailService.loadUserByUsername(userName);
				System.out.println("userdetails {} "+userDetails);
				if (jwtTokenProvider.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
							= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
					);

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}

	}

	public boolean isAdmin() {
		System.out.println("isAdmin...");
		System.out.println("claim...{} "+claims);
		return "admin".equalsIgnoreCase((String) claims.get("role"));
	}

	public boolean isUser() {
		System.out.println("isUser...");
		return "user".equalsIgnoreCase((String) claims.get("role"));
	}

	public String getCurrentUser() {
		System.out.println("getCurrentUser..");
		return userName;
	}

}