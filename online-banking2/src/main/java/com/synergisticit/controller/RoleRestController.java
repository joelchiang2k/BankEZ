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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("roles")
public class RoleRestController {
	@Autowired RoleService roleService;
	@GetMapping(value = "all")
	public ResponseEntity<List<Role>> findAll(){
		List<Role> roles = roleService.findAll();

		if(roles.isEmpty()) {
			return new ResponseEntity<List<Role>>(roles, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Role>>(roles, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Role> findById(@RequestParam long roleId){
		Role role = roleService.findById(roleId);
	
		if(role == null) {
			return new ResponseEntity<Role>(role, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Role>(role, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Role> deleteRole(@RequestParam Long roleId) {
		HttpHeaders headers = new HttpHeaders();
		Role role = roleService.findById(roleId);
	    if (role == null) {
	        return new ResponseEntity<Role>(role, HttpStatus.NOT_FOUND);
	    } else {
	    	roleService.deleteById(roleId);
	    	headers.add("Role deleted", String.valueOf(roleId));
	        return new ResponseEntity<Role>(role, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveRole(@Valid @RequestBody Role role, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(roleService.existsById(role.getRoleId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Role with id" + role.getRoleId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Role r = roleService.save(role);
			return new ResponseEntity<Role>(r, HttpStatus.CREATED);
		}
	
	}
}
