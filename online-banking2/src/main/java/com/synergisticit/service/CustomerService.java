package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Customer;

@Service
public interface CustomerService {
	public Customer save(Customer customer);
    public Customer findById(Long customerId);
    public List<Customer> findAll();
    public void deleteById(Long customerId);
}
