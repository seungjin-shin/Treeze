package com.hansung.treeze.service;

import com.hansung.treeze.model.User;


public interface UserService {

	User saveUser(User user);
	User findByEmail(String email);

}
