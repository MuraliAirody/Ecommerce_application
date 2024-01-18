package com.ecommerce.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
//@Slf4j
public class AppConfig {

	@Autowired
	UserDetailService userDetailService;


	@Autowired
	JwtTokenValidator jwtTokenValidator;



	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		System.out.println("passwordEncoder()..");
		return  new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		System.out.println("authenticationManager()..");
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(){
		System.out.println("daoAuthenticationProvider()..");
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userDetailService);
		return daoAuthenticationProvider;
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		System.out.println("securityFilterChain()..");
		httpSecurity.cors(cors->cors.configurationSource(new CorsConfigurationSource() {

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

						CorsConfiguration cfg = new CorsConfiguration();

						cfg.setAllowedOrigins(List.of(
										"http://localhost:3000"
								)
						);
						//cfg.setAllowedMethods(Arrays.asList("GET", "POST","DELETE","PUT"));
						cfg.setAllowedMethods(Collections.singletonList("*"));
						cfg.setAllowCredentials(true);
						cfg.setAllowedHeaders(Collections.singletonList("*"));
						cfg.setExposedHeaders(List.of("Authorization"));
						cfg.setMaxAge(3600L);
						return cfg;

					}
				}))
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(Authorize -> Authorize
						.requestMatchers("/api/**").authenticated()
						.anyRequest().permitAll()
				)
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		httpSecurity.addFilterBefore(jwtTokenValidator, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}


}
