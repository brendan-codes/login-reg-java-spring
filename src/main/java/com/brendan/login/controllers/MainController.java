package com.brendan.login.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brendan.login.models.User;
import com.brendan.login.services.UserService;
import com.brendan.login.validators.UserValidator;

@Controller
public class MainController {
	
	private UserService userService;
	private UserValidator userValidator;
	
	public MainController(UserValidator uv, UserService us) {
		this.userValidator = uv;
		this.userService = us;
	}
	
	@GetMapping("/")
	public String index(@ModelAttribute("userObject") User user) {
		return "loginreg.jsp";
	}
	
	@PostMapping("/registration")
	public String registerUser(@Valid @ModelAttribute("userObject") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			session.setAttribute("errors", result.getAllErrors());
			return "redirect:/";
		} else {
			User u = userService.register(user);
			session.setAttribute("userid", u.getId());
			return "redirect:/home";
		}
	}
	
	@GetMapping("/home")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
		if(userService.authenticateUser(email, password)) {
			User thisUser = userService.findByEmail(email);
			session.setAttribute("userid", thisUser.getId());
			return "redirect:/home";
		}else {
			session.setAttribute("error", "Invalid login");
			return "redirect:/";
		}
	}
	
	@GetMapping("/logout")
	public String logoutUser() {
			return "redirect:/";
	}
}
