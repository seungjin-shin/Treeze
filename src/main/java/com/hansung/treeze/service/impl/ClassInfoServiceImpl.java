package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.persistence.ClassInfoRepository;
import com.hansung.treeze.persistence.ClassInfoSpecifications;
import com.hansung.treeze.service.ClassInfoService;

@Service
public class ClassInfoServiceImpl implements ClassInfoService{

	@Autowired private ClassInfoRepository classRepository;
	
	@Override
	public ClassInfo saveClass(ClassInfo classInfo) {
		// TODO Auto-generated method stub
		return classRepository.save(classInfo);
	}
	
	@Override
	public Object getClassInfoes(String lectureName, String professorEmail){
		// TODO Auto-generated method stub
		return classRepository.findAll(Specifications.where(ClassInfoSpecifications.isMyClass(lectureName, professorEmail)));
	}


}
