package com.hansung.treeze.service;

import com.hansung.treeze.model.Lecture;


public interface LectureService {

	Lecture saveLecture(Lecture lecture);
	void deleteLecture(Lecture lecture);
	Lecture findOne(Long lectureId);
	Object findAll();
	Object findBylectureName(Long lectureId);
	Object findMyLectures(String professorEmail);

}
