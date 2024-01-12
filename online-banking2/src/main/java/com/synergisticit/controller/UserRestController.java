package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Branch;
import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserRestController {
	@Autowired UserService userService;
	@GetMapping(value = "all")
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userService.findAll();

		if(users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<User> findById(@RequestParam long userId){
		User user = userService.findById(userId);
	
		if(user == null) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@RequestParam Long userId) {
		HttpHeaders headers = new HttpHeaders();
		User user = userService.findById(userId);
	    if (user == null) {
	        return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	    } else {
	    	userService.deleteById(userId);
	    	headers.add("User deleted", String.valueOf(userId));
	        return new ResponseEntity<User>(user, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(userService.existsById(user.getUserId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("User with id" + user.getUserId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			User u = userService.save(user);
			return new ResponseEntity<User>(u, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateUser(@RequestBody User u){
		User user = userService.findById(u.getUserId());
		if(user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else {
			userService.save(user);
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
		
	}
}
