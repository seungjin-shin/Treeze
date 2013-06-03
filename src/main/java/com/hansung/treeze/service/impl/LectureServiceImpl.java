package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.persistence.LectureRepository;
import com.hansung.treeze.persistence.LectureSpecifications;
import com.hansung.treeze.service.LectureService;

@Service
public class LectureServiceImpl implements LectureService{

	@Autowired private LectureRepository lectureRepository;


	@Override
	public Lecture saveLecture(Lecture lecture){
		// TODO Auto-generated method stub
		return lectureRepository.save(lecture);
	}

	@Override
	public Object findAll() {
		// TODO Auto-generated method stub
		return lectureRepository.findAll();
	}

	@Override
	public Object findBylectureName(Long lectureId) {
		// TODO Auto-generated method stub
		return lectureRepository.findAll(Specifications.where(LectureSpecifications.isLectureId(lectureId)));
	
	}

		@Override
	public Object findMyLectures(String professorEmail) {
		// TODO Auto-generated method stub
		return lectureRepository.findAll(Specifications.where(LectureSpecifications.isMyLecture(professorEmail)));
		
	}

}
