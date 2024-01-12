package com.synergisticit.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    
    
	@RequestMapping("/searchForm")
	public ModelAndView searchForm(Search search, Model model, Principal principal) {
		ModelAndView mav = new ModelAndView("searchForm");
		getData(model);
		//List<BankTransaction> bankTransactions = bankTransactionService.findAll();
		//model.addAttribute("bankTransactions", bankTransactions);
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		
		return mav;
	}
	
	@RequestMapping("/saveSearch")
	public ModelAndView saveSearch(@Valid @ModelAttribute Search search, BindingResult br, Model model) {
		ModelAndView mav = new  ModelAndView("searchForm");
		
		List<BankTransaction> bankTransactionsList = new ArrayList<>();
		
		if(br.hasErrors()) {
			getData(model);
			List<BankTransaction> bankTransactions = bankTransactionService.findAll();
			model.addAttribute("bankTransactions", bankTransactions);
			return mav;
		}else {
			try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
	            // Rest of the code remains the same
	            String query = "SELECT bankTransactionId FROM bankTransaction WHERE bankTransactionFromAccount = ? OR bankTransactionToAccount = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            	Long searchAccountId = search.getSearchAccountId();
	                // Set the parameter values
	                preparedStatement.setLong(1, searchAccountId);
	                preparedStatement.setLong(2, searchAccountId);

	                // Execute the query
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    // Iterate through the result set
	                    while (resultSet.next()) {
	                        // Access values of columns
	                    	Long bankTransactionId = resultSet.getLong("bankTransactionId");
	                    	bankTransactionsList.add(bankTransactionService.findById(bankTransactionId));
	                        // Process the values as needed
	                        System.out.println("bankId" + bankTransactionId);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			getData(model);
			
			System.out.println(bankTransactionsList);
			model.addAttribute("bankTransactions", bankTransactionsList);
			//searchList(model);
			mav.setViewName("searchForm");
			return mav;
		}
	}
	
	public void getData(Model model) {
		
		model.addAttribute("searchTransactionTypes", TransactionType.values());
		
	}

}
