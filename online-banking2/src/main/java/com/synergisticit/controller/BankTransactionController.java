package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.AccountType;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.TransactionType;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.service.TransactionService;
import com.synergisticit.validation.BankTransactionValidator;

import jakarta.validation.Valid;

@Controller
public class BankTransactionController {
	@Autowired BankTransactionService bankTransactionService;
	
	@Autowired TransactionService transactionService;
	@Autowired AccountService accountService;
	@Autowired BankTransactionValidator bankTransactionValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(bankTransactionValidator);
	}
	
	@RequestMapping("/bankTransactionForm")
	public ModelAndView bankTransactionForm(BankTransaction bankTransaction, Model model) {
		ModelAndView mav = new ModelAndView("bankTransactionForm");
		getData(model);
		bankTransactionList(model);
		
		return mav;
	}
	
	@RequestMapping("/saveBankTransaction")
	public ModelAndView saveBankTransaction(@Valid @ModelAttribute BankTransaction bankTransaction, BindingResult br, Model model) {
		bankTransactionValidator.validate(bankTransaction, br);
		ModelAndView mav = new  ModelAndView("bankTransactionForm");
		
		var bankTransactionAmount = bankTransaction.getBankTransactionAmount();
		var fromAccountId = bankTransaction.getBankTransactionFromAccount();
		var toAccountId = bankTransaction.getBankTransactionToAccount();
		var bankTransactionType = bankTransaction.getBankTransactionType();
		
		Account fromAccount = accountService.findById(fromAccountId);
		Account toAccount = accountService.findById(toAccountId);
		var fromAccountBalance = fromAccount.getAccountBalance();
		var toAccountBalance = toAccount.getAccountBalance();
		
		if(bankTransactionType == TransactionType.DEPOSIT) {
			fromAccountBalance += bankTransactionAmount;
		}
		else if(bankTransactionType == TransactionType.WITHDRAW) {
			fromAccountBalance -= bankTransactionAmount;
		}
		else if(bankTransactionType == TransactionType.TRANSFER) {
			System.out.println("hihihi");
			System.out.println(bankTransactionAmount);
			fromAccountBalance -= bankTransactionAmount;
			toAccountBalance += bankTransactionAmount;
		}
		
		fromAccount.setAccountBalance(fromAccountBalance);
		toAccount.setAccountBalance(toAccountBalance);
		
		if(br.hasErrors()) {
			System.out.println("errors" + br);
			bankTransactionList(model);
			return mav;
		}else {
			bankTransactionService.save(bankTransaction);
			System.out.println(fromAccount.getAccountBalance());
			System.out.println(toAccount.getAccountBalance());
			accountService.save(fromAccount);
			accountService.save(toAccount);
			bankTransactionList(model);
			mav.setViewName("redirect:bankTransactionForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateBankTransaction")
	public String updateBankTransaction(BankTransaction bankTransaction, Model model) {
		BankTransaction b = bankTransactionService.findById(bankTransaction.getBankTransactionId());
		model.addAttribute("bankTransaction", b);
		getData(model);
		
		bankTransactionList(model);
		return "bankTransactionForm";
	}
	
	@RequestMapping("/deleteBankTransaction")
	public String deleteBankTransaction(BankTransaction bankTransaction, Model model, RedirectAttributes ra) {
		bankTransactionList(model);
		bankTransactionService.deleteById(bankTransaction.getBankTransactionId());
		return "redirect:bankTransaction"; 
	}
	
	public void bankTransactionList(Model model) {
		List<BankTransaction> bankTransactions = bankTransactionService.findAll();
		model.addAttribute("bankTransactions", bankTransactions);
		
	}
	
	public void getData(Model model) {
		
		model.addAttribute("bankTransactionTypes", TransactionType.values());
		
	}
}
