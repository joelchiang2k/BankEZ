package com.synergisticit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.Search;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.service.SearchService;

import jakarta.validation.Valid;

@Controller
public class SearchController {
	@Autowired AccountService accountService;
	@Autowired BankTransactionService bankTransactionService;
	@Autowired SearchService searchService;
	
	@RequestMapping("/searchForm")
	public ModelAndView searchForm(Search search, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("searchForm");
		getData(model);
		searchList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveSearch")
	public ModelAndView saveSearch(@Valid @ModelAttribute Search search, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("searchForm");

		
		if(br.hasErrors()) {
			getData(model);
			searchList(model);
			return mav;
		}else {
			getData(model);
//			accountService.save(account);
			searchList(model);
			mav.setViewName("redirect:accountForm");
			return mav;
		}
	}
	
	public void getData(Model model) {
		
		model.addAttribute("searchTransactionTypes", TransactionType.values());
		
	}
	
	public void searchList(Model model) {
		List<BankTransaction> bankTransactions = bankTransactionService.findAll();
		model.addAttribute("bankTransactions", bankTransactions);
		
	}
}
