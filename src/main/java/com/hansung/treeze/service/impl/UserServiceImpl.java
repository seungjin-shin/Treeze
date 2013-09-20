package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.User;
import com.hansung.treeze.persistence.UserRepository;
import com.hansung.treeze.persistence.UserSpecifications;
import com.hansung.treeze.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserRepository userRepository;
//	@Autowired private PasswordEncoder passwordEncoder;
	 
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		
	//	user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user.getUserName()));
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findOne(Specifications.where(UserSpecifications.isEmail(email)));
	}

	@Override
	public boolean existsUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user = userRepository.findOne(Specifications.where(UserSpecifications.isEmail(userEmail)));
		
		if(user == null)
			return false;
		
		return true;
	}

	@Override
	public boolean existsIdentificationNumber(String identificationNumber) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findOne(Specifications.where(UserSpecifications.isIdentificationNumber(identificationNumber)));
		
		if(user == null)
			return false;
		
		return true;
	}

}
