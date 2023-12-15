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

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;

import jakarta.validation.Valid;

@Controller
public class BranchController {
	@Autowired BranchService branchService;
	
	@Autowired BranchValidator branchValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(branchValidator);
	}
	
	@RequestMapping("/branchForm")
	public ModelAndView branchForm(Branch branch, Model model) {
		ModelAndView mav = new ModelAndView("branchForm");
		branchList(model);
		
		return mav;
	}
	
	@RequestMapping("/saveBranch")
	public ModelAndView saveBranch(@Valid @ModelAttribute Branch branch, BindingResult br, Model model) {
		branchValidator.validate(branch, br);
		ModelAndView mav = new  ModelAndView("branchForm");
		
		
		if(br.hasErrors()) {
			branchList(model);
			return mav;
		}else {
			branchService.save(branch);
			branchList(model);
			mav.setViewName("redirect:branchForm");
			return mav;
		}
	}	
	
	@RequestMapping("/updateBranch")
	public String updateBranch(Branch branch, Model model) {
		Branch b = branchService.findById(branch.getBranchId());
		model.addAttribute("branch", b);
	
		
		branchList(model);
		return "branchForm";
	}
	
	@RequestMapping("/deleteBranch")
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
