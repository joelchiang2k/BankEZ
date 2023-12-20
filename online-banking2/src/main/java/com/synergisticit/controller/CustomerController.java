package com.synergisticit.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Gender;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.validation.AccountValidator;
import com.synergisticit.validation.CustomerValidator;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
	@Autowired CustomerService customerService;
	
	@Autowired CustomerValidator customerValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(customerValidator);
	}
	
	@RequestMapping("/customerForm")
	public ModelAndView accountForm(Customer customer, Model model) {
		ModelAndView mav = new ModelAndView("customerForm");
		getData(model);
		customerList(model);
		
		
		return mav;
	}
	
	@RequestMapping("/saveCustomer")
	public ModelAndView saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult br, Model model) {
		customerValidator.validate(customer, br);
		ModelAndView mav = new ModelAndView("customerForm");
		
		
		if(br.hasErrors()) {
			System.out.println("have error"+br);
			customerList(model);
			return mav;
		}else {
			customerService.save(customer);
			customerList(model);
			mav.setViewName("redirect:customerForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateCustomer")
	public String updateCustomer(Customer customer, Model model) {
		Customer c = customerService.findById(customer.getCustomerId());
		model.addAttribute("customer", c);
		getData(model);
		
		customerList(model);
		return "customerForm";
	}
	
	@RequestMapping("/deleteCustomer")
	public String deleteCustomer(Customer customer, Model model, RedirectAttributes ra) {
		customerList(model);
		customerService.deleteById(customer.getCustomerId());
		return "redirect:customerForm"; 
	}
	
	public void customerList(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		
	}
	
	public void getData(Model model) {
	
		model.addAttribute("genders", Gender.values());
		
	}
}
