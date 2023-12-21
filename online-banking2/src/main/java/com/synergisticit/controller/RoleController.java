package com.synergisticit.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

import jakarta.validation.Valid;

@Controller
public class RoleController {
	
	@Autowired RoleService roleService;
	
	@RequestMapping("roleForm")
	public ModelAndView roleForm(Role role, Principal principal, Model model) {
		ModelAndView mav = new ModelAndView("roleForm");
		mav.addObject("roles", roleService.findAll());
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}

		return mav;
		
	}
	
	@RequestMapping("saveRole")
	public ModelAndView saveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model) {
		ModelAndView mav = new ModelAndView("roleForm");
		if(br.hasErrors()) {
			mav.addObject("roles", roleService.findAll());
			mav.setViewName("roleForm");
			return mav;
		}else {
			roleService.save(role);
			mav.addObject("roles", roleService.findAll());
			return mav;
		}	
	}

}
