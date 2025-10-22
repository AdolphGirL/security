package com.reyes.securityr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
						.requestMatchers(HttpMethod.GET, "/members").access(null) // SpEL 範例
						.anyRequest().authenticated()
				)
				.build();
	}
	 */
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.formLogin(Customizer.withDefaults())
				/** Disable CSRF protection for simplicity in this example */
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> 
					request
						/** Permit all users to create new members */
						.requestMatchers(HttpMethod.POST, "/members").permitAll()
						.requestMatchers(HttpMethod.GET, "/members").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET, "/selected-courses").hasAuthority("STUDENT")
						.requestMatchers(HttpMethod.GET, "/course-feedback").hasAnyAuthority("TEACHER", "ADMIN")
						/** All other requests require authentication */
						.anyRequest().authenticated()
				)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
