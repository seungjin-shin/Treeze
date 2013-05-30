package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.User;
import com.hansung.treeze.persistence.UserRepository;
import com.hansung.treeze.persistence.UserSpecitications;
import com.hansung.treeze.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}


}
