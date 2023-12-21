package com.synergisticit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
	@RequestMapping({"/","home"})
	public String homeABC(Principal principal, Model model) {
		if(principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		return "home";
	}
}
