package com.jwt.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jwt.sample.entity.Users;
import com.jwt.sample.repository.UserRepository;

@Controller
public class WelcomeController {

	@Autowired
	private UserRepository userRepo;
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	@GetMapping("/register")
	public String signUpPage(Model model) {
		model.addAttribute("userForm", new Users());
		return "signup_form";
	}
	
	
	@PostMapping("/process_register")
	public String register(Users users) {
		
		userRepo.save(users);
		return "welcome";
	}
	
}
