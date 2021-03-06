package com.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.rest.model.User;
import com.demo.rest.service.SecurityService;
import com.demo.rest.service.UserService;
import com.demo.rest.service.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult res, Model model) {
		userValidator.validate(userForm, res);
		
		if (res.hasErrors())
			return "registration";
		
		userService.save(userForm);
		
		securityService.autologin(userForm.getUsername(), userForm.getPassword());
		
		return "redirect:/welcome";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "ERROR");
		if (logout != null) 
			model.addAttribute("logout", "logout");
		
		return "login";
	}
	@RequestMapping(value = {"/","welcome"}, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}
}
