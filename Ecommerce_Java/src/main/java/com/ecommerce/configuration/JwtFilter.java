package com.ecommerce.configuration;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@Slf4j
public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailService userDetailService;

    Claims claims = null;
    private String userName = null;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtFilter - > doFilterInternal..");
        if (httpServletRequest.getServletPath().matches("/auth/login|/auth/signup|/swagger-ui/index.html")) {
            log.info("no filter required");
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            log.info("filter required");
            String authorizationHeader = httpServletRequest.getHeader("Authorization");
            String token = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring(7);
                log.info("token {}",token);
                userName = jwtUtils.extractUsername(token);
                log.info("username {}",userName);
                claims = jwtUtils.extractAllClaims(token);
                log.info("claims {}",claims);
            }
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailService.loadUserByUsername(userName);
                log.info("userdetails {}",userDetails);
                if (jwtUtils.validateToken(token, userDetails)) {
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
        log.info("isAdmin...");
        log.info("claim...{}",claims);
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public boolean isUser() {
        log.info("isUser...");
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        log.info("getCurrentUser..");
        return userName;
    }

}











