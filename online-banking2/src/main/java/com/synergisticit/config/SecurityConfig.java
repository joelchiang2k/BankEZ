package com.synergisticit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.synergisticit.domain.Role;
import com.synergisticit.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	UserDetailsService u;
	//@Autowired NoOpPasswordEncoder encoder;
	@Autowired UserService userService;
	@Autowired BCryptPasswordEncoder bCrypt;
	
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		List<com.synergisticit.domain.User> userList = userService.findAll();
		List<UserDetails> users = new ArrayList<>();
		List<GrantedAuthority> authority1 = new ArrayList<>();
//		authority1.add(new SimpleGrantedAuthority("Admin"));
//		authority1.add(new SimpleGrantedAuthority("User"));
		System.out.println(userList);
		for (com.synergisticit.domain.User user : userList) {
			List<Role> roles = user.getRoles();
			System.out.println(roles);
			for(Role role : roles) {
				System.out.println(role.getName());
				authority1.add(new SimpleGrantedAuthority(role.getName()));
			}
			users.add(new User(user.getUsername(), bCrypt.encode(user.getPassword()), authority1));
		}
		UserDetails user1 = new User("joel", bCrypt.encode("joel"), authority1);
		users.add(user1);
	
		
		List<GrantedAuthority> authority2 = new ArrayList<>();
		authority2.add(new SimpleGrantedAuthority("User"));
		UserDetails user2 = new User("bob", bCrypt.encode("bob"), authority2);
		users.add(user2);
		System.out.println(authority1);
		System.out.println(users);
		return new InMemoryUserDetailsManager(users);
		
}
	
	
	
	@Bean
	DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(inMemoryUserDetailsManager());
		authProvider.setPasswordEncoder(bCrypt);
		return authProvider;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests().anyRequest().permitAll()
		http.authorizeHttpRequests().requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
		.requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()
		.anyRequest().authenticated()
//		.and()
//		.httpBasic()
		.and()
		.formLogin();
		//.loginPage("login");
		return http.build();
	}
	
}
