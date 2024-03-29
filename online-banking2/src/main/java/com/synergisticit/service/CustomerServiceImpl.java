package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long customerId) {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if(optCustomer.isPresent()) {
			return optCustomer.get();
		}
		return null;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public void deleteById(Long customerId) {
		customerRepository.deleteById(customerId);
	}
	
	@Override
	public boolean existsById(Long customerId) {
		return customerRepository.existsById(customerId);
	}

}
