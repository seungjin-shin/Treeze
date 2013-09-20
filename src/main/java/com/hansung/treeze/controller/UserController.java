package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.User;
import com.hansung.treeze.service.UserService;

/*
 0. 교수,학생이 가 로그인한다. (송신)

 - Rest : POST방식
 - URL : http://113.198.84.74:8080/treeze/login
 - Format : JSON
 - Class name : User
 - Return Value : Boolean 
 * */

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	//@Autowired private PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String addUser(User model, ModelMap map) {
		User user = userService.saveUser(model);
		map.put("user", user);

		return "jsonView";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String addUser(@RequestParam("userEmail") String userEmail,
			@RequestParam("password") String password, ModelMap map) {

		User user = null;
		user = userService.findByEmail(userEmail);

		if (user == null)
			return "emailFalse";
		
		else if(!user.getPassword().equals(password))
			return "passwordFalse";
		
		//String currentPassword = passwordEncoder.encodePassword(password, user.getUserName());
		
	//	if(!user.getPassword().equals(currentPassword)){
		//	return "passwordFalse";
		//}

		map.put("user", user);

		return "jsonView";
	}
	
	@RequestMapping(value = "/existsEmail", method = RequestMethod.GET)
	public String existsEmail(@RequestParam("userEmail") String userEmail, ModelMap map) {
		boolean isRegistedUser = userService.existsUserEmail(userEmail);
		
		if(isRegistedUser){
			return "true";
		}
		
		return "false";
	}
	
	@RequestMapping(value = "/existsIdentificationNumber", method = RequestMethod.GET)
	public String existsIdentificationNumber(@RequestParam("identificationNumber") String identificationNumber, ModelMap map) {
		boolean isRegistedIdentificationNumber = userService.existsIdentificationNumber(identificationNumber);
		
		if(isRegistedIdentificationNumber){
			return "true";
		}
		return "false";
	}

}
