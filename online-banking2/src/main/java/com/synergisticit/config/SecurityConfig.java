package com.synergisticit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.synergisticit.domain.Role;
import com.synergisticit.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	@Autowired UserService userService;
	@Autowired BCryptPasswordEncoder bCrypt;
	@Autowired UserDetailsService u;
	@Autowired AuthenticationSuccessHandler successHandler;
	
//	@Bean
//	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		List<com.synergisticit.domain.User> userList = userService.findAll();
//		List<UserDetails> users = new ArrayList<>();
//		System.out.println(userList);
//		for (com.synergisticit.domain.User user : userList) {
//			List<GrantedAuthority> authority1 = new ArrayList<>();
//			List<Role> roles = user.getRoles();
//			System.out.println("roles:" + roles);
//			System.out.println("name:" + user.getUsername());
//			for(Role role : roles) {
//				System.out.println(role.getName());
//				authority1.add(new SimpleGrantedAuthority(role.getName()));
//			}
//			users.add(new User(user.getUsername(), bCrypt.encode(user.getPassword()), authority1));
//		}
//		System.out.println(users);
//		return new InMemoryUserDetailsManager(users);
//		
//}
	
	
	
	@Bean
	DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(u);
		authProvider.setPasswordEncoder(bCrypt);
		return authProvider;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests().anyRequest().permitAll();
//		http.csrf().disable()
//		.authorizeHttpRequests().requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
//		.requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
//		.requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()// required to display the default form provided by Spring to login
//		.loginPage("/login")
//		.successHandler(successHandler);
		
		http.userDetailsService(u);
		http.authenticationProvider(authProvider());
		
		return http.build();
	}
	
}
