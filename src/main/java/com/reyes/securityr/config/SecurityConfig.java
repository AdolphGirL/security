package com.reyes.securityr.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application. This class is currently
 * empty but can be extended to include security settings such as authentication
 * and authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures an in-memory user details manager with three users.
	 * Each user has a username and a password.
	 * 
	 * @return an instance of InMemoryUserDetailsManager containing the defined users
	 */
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailManager() {
		UserDetails user1 = User.withUsername("user1").password("{noop}111").build();
		UserDetails user2 = User.withUsername("user2").password("{noop}222").build();
		UserDetails user3 = User.withUsername("user3").password("{noop}333").build();
		return new InMemoryUserDetailsManager(List.of(user1, user2, user3));
	}
	
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.formLogin(Customizer.withDefaults())
				.authorizeHttpRequests(request -> request
						.requestMatchers(HttpMethod.GET, "/register").permitAll()
						.requestMatchers(HttpMethod.GET, "/selected-courses").hasAuthority("STUDENT")
						.requestMatchers(HttpMethod.GET, "/course-feedback").hasAnyAuthority("TEACHER", "ADMIN")
						.requestMatchers(HttpMethod.GET, "/members").hasAuthority("ADMIN")
						/** Example of using SpEL for access control
						.requestMatchers(HttpMethod.GET, "/members").access(null) // SpEL */
						.anyRequest().authenticated()
				)
				.build();
	}
	
}
