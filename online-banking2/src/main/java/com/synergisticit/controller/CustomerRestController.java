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
import com.synergisticit.domain.Customer;
import com.synergisticit.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("customers")
public class CustomerRestController {
	
	@Autowired CustomerService customerService;
	
	@GetMapping(value = "all")
	public ResponseEntity<List<Customer>> findAll(){
		List<Customer> customers = customerService.findAll();
		System.out.println("customerService.findAll:" + customers);
		if(customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.FOUND);
		}
	}
	
	@GetMapping(value = "findById")
	public ResponseEntity<Customer> findById(@RequestParam long customerId){
		Customer customer = customerService.findById(customerId);
	
		if(customer == null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Customer>(customer, HttpStatus.FOUND);
		}
	}
	
	@RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@RequestParam Long customerId) {
		HttpHeaders headers = new HttpHeaders();
		Customer customer = customerService.findById(customerId);
	    if (customer == null) {
	        return new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
	    } else {
	    	customerService.deleteById(customerId);
	    	headers.add("Customer deleted", String.valueOf(customerId));
	        return new ResponseEntity<Customer>(customer, headers, HttpStatus.NO_CONTENT);
	    }
	}
	
	@PostMapping(value = "save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer, BindingResult br){
		StringBuilder sb = new StringBuilder("");
		if(customerService.existsById(customer.getCustomerId()) || br.hasFieldErrors()) {
			if(br.hasFieldErrors()) {
				List<FieldError> fieldErrors = br.getFieldErrors();
				for(FieldError fieldError : fieldErrors) {
					sb = sb.append("\"" + fieldError.getField()+"\":"+fieldError.getDefaultMessage()+"\n");
				}
				System.out.println("sb: " + sb);
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
			}
			else {
				sb.append("Customer with id" + customer.getCustomerId() + "\"already there");
				return new ResponseEntity<StringBuilder>(sb, HttpStatus.NOT_ACCEPTABLE);
			}
		}else {
			Customer c = customerService.save(customer);
			return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
		}
	
	}
	
	@PutMapping("update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer c){
		Customer customer = customerService.findById(c.getCustomerId());
		if(customer == null) {
			return new ResponseEntity<String>("No customer with id"  + c.getCustomerId(), HttpStatus.NOT_FOUND);
		}else {
			customerService.save(customer);
			return new ResponseEntity<Customer>(c, HttpStatus.OK);
		}
		
	}
}

