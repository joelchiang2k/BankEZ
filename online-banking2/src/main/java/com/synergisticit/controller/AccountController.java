package com.synergisticit.controller;

import java.security.Principal;
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
import com.synergisticit.domain.Gender;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.validation.AccountValidator;

import jakarta.validation.Valid;

@Controller
public class AccountController {
	@Autowired AccountService accountService;
	@Autowired AccountValidator accountValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(accountValidator);
	}
	
	@RequestMapping("/accountForm")
	public ModelAndView accountForm(Account account, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("accountForm");
		getData(model);
		accountList(model);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveAccount")
	public ModelAndView saveAccount(@Valid @ModelAttribute Account account, BindingResult br, Model model) {
		//accountValidator.validate(account, br);
		ModelAndView mav = new  ModelAndView("accountForm");

		
		if(br.hasErrors()) {
			getData(model);
			accountList(model);
			return mav;
		}else {
			getData(model);
			accountService.save(account);
			accountList(model);
			mav.setViewName("redirect:accountForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateAccount")
	public String updateAccount(Account account, Model model) {
		Account b = accountService.findById(account.getAccountId());
		model.addAttribute("account", b);
		getData(model);
		
		accountList(model);
		return "accountForm";
	}
	
	@RequestMapping("/deleteAccount")
	public String deleteAccount(Account account, Model model, RedirectAttributes ra) {
		accountList(model);
		accountService.deleteById(account.getAccountId());
		return "redirect:accountForm"; 
	}
	
	public void accountList(Model model) {
		List<Account> accounts = accountService.findAll();
		
		model.addAttribute("accounts", accounts);
		
	}
	
	public void getData(Model model) {
	
		model.addAttribute("accountTypes", AccountType.values());
		
	}
}
