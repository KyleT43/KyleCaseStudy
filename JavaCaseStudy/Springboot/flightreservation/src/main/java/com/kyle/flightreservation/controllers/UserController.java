package com.kyle.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyle.flightreservation.entities.User;
import com.kyle.flightreservation.repos.UserRepository;
import com.kyle.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;

	private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
//	@RequestMapping("/showReg")
	@RequestMapping(path = "/showReg", method = RequestMethod.GET)
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
		
	}
	
	
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user")User user) {
		LOGGER.info("Inside register()"+user);
		user.setPassword(encoder.encode(user.getPassword()));

		userRepository.save(user);
		return "login/login";
	}
	
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
		
	}
		
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password,
			ModelMap modelMap) {
		LOGGER.info("Inside login() and the email is: " + email);
		LOGGER.error("ERROR");
		LOGGER.warn("WARN");
		LOGGER.info("INFO");
		LOGGER.debug("DEBUG");
		LOGGER.trace("TRACE");
//		User user = userRepository.findByEmail(email);
		boolean loginResponse = securityService.login(email,  password);
//		if(user.getPassword().equals(password)) {
//			return "findFlights";
//		}else {
//			modelMap.addAttribute("msg","Invalid username or password. Please try again.");
//			
//		}
		
		
		if(loginResponse) {
			return "findFlights";
		}else {
			modelMap.addAttribute("msg","Invalid username or password. Please try again.");
			
		}
		return "login/login";
		
	}


}
