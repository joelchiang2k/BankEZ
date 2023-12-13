package com.synergisticit.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;

import jakarta.validation.Valid;

@Controller
public class BranchController {
	@Autowired BranchService branchService;
	
	@RequestMapping("/branchForm")
	public String branchForm(Branch branch, Model model) {
		branchList(model);
		return "branchForm";
	}
	
	@RequestMapping("/saveBranch")
	public String saveBranch(@Valid @ModelAttribute Branch branch, BindingResult br, Model model) {
		branchList(model);
		branchService.save(branch);
		
		return "branchForm";
	}
	
	@RequestMapping("updateBranch")
	public String updateBranch(Branch branch, Model model) {
		Branch b = branchService.findById(branch.getBranchId());
		model.addAttribute("branch", b);
	
		
		branchList(model);
		return "branchForm";
	}
	
	@RequestMapping("deleteBranch")
	public String deleteBranch(Branch branch, Model model, RedirectAttributes ra) {
		branchList(model);
		branchService.deleteById(branch.getBranchId());
		return "redirect:branchForm"; 
	}
	
	public void branchList(Model model) {
		List<Branch> branches = branchService.findAll();
		model.addAttribute("branches", branches);
		
	}
}
