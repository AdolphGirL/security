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
	 * Configures an in-memory user details manager with three users. Each user has
	 * a username and a password.
	 * 
	 * @return an instance of InMemoryUserDetailsManager containing the defined
	 *         users
	 * 
	 * @Bean InMemoryUserDetailsManager inMemoryUserDetailManager() { UserDetails
	 *       user1 = User.withUsername("user1").password("{noop}111").build();
	 *       UserDetails user2 =
	 *       User.withUsername("user2").password("{noop}222").build(); UserDetails
	 *       user3 = User.withUsername("user3").password("{noop}333").build();
	 *       return new InMemoryUserDetailsManager(List.of(user1, user2, user3)); }
	 */

	/**
	 * Configures the security filter chain for HTTP requests.
	 * 
	 * @param httpSecurity the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain
	 * @throws Exception if an error occurs during configuration
	 * 
	 *                   SecurityFilterChain securityFilterChain(HttpSecurity
	 *                   httpSecurity) throws Exception { return httpSecurity
	 *                   .formLogin(Customizer.withDefaults())
	 *                   .authorizeHttpRequests(request -> request
	 *                   .requestMatchers(HttpMethod.GET, "/register").permitAll()
	 *                   .requestMatchers(HttpMethod.GET,
	 *                   "/selected-courses").hasAuthority("STUDENT")
	 *                   .requestMatchers(HttpMethod.GET,
	 *                   "/course-feedback").hasAnyAuthority("TEACHER", "ADMIN")
	 *                   .requestMatchers(HttpMethod.GET,
	 *                   "/members").hasAuthority("ADMIN") // Example of using SpEL
	 *                   for access control // SpEL .requestMatchers(HttpMethod.GET,
	 *                   "/members").access(null) .anyRequest().authenticated() )
	 *                   .build(); }
	 */

	
	/***
	 * Spring 在掃描 @Configuration 類別時，會透過 反射 來建立並註冊 Bean。
	 * 因此方法 不需要一定是 public，只要是 可被 Spring 容器訪問的（非 private） 就可以。
	 */
	
	/**
	 * Configures the security filter chain for HTTP requests.
	 * 
	 * @param httpSecurity the HttpSecurity object to configure
	 * @return the configured SecurityFilterChain
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				/** 啟用表單登入 */
				.formLogin(Customizer.withDefaults())
				/** 關閉 CSRF 保護 */
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, "/members").permitAll()
						.requestMatchers(HttpMethod.GET, "/members").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET, "/selected-courses").hasAuthority("STUDENT")
						.requestMatchers(HttpMethod.GET, "/course-feedback").hasAnyAuthority("TEACHER", "ADMIN")
						.anyRequest().authenticated())
				.build();
	}

	/***
	 * Defines a PasswordEncoder bean that uses BCrypt for password hashing.
	 * 
	 * @return a BCryptPasswordEncoder instance
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
