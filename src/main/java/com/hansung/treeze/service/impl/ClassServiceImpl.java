package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Class;
import com.hansung.treeze.persistence.ClassRepository;
import com.hansung.treeze.persistence.ClassSpecifications;
import com.hansung.treeze.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService{

	@Autowired private ClassRepository classRepository;
	
	@Override
	public Class saveClass(Class classInfo) {
		// TODO Auto-generated method stub
		return classRepository.save(classInfo);
	}
	
	@Override
	public Object getClasses(String lectureName, String professorEmail){
		// TODO Auto-generated method stub
		return classRepository.findAll(Specifications.where(ClassSpecifications.isMyClass(lectureName, professorEmail)));
	}


}
