package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String addUser(User model, ModelMap map) {
		userService.saveUser(model);
		map.put("result", "success");

		return "jsonView";
	}
}
