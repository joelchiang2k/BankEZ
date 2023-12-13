package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;

import jakarta.validation.Valid;

@Controller
public class BranchController {
	@Autowired BranchService branchService;
	
	@RequestMapping("/branchForm")
	public String branchForm(Branch branch, Model model) {
		List<Branch> getBranches = branchService.findAll();
		model.addAttribute("branches", getBranches);
		System.out.println("branchService:" + getBranches);
		return "branchForm";
	}
	
	@RequestMapping("/saveBranch")
	public String saveBranch(@Valid @ModelAttribute Branch branch, BindingResult br, Model model) {
		List<Branch> getBranches = branchService.findAll();
		branchService.save(branch);
		model.addAttribute("branches", getBranches);
		return "branchForm";
	}
}
