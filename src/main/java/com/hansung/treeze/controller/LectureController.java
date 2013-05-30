package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.etbike.server.domain.model.Account;
import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.service.LectureService;

/*
 * 1. 교수가 강좌를 등록한다.(송신)

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/createLecture
- Format : JSON
- Class name : Lecture
- Return Value : Boolean 

3-2. Lecture에 온라인 여부 true/로 한다.(송신)

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/setStateOfLecture
- Format : JSON
- Class name : Lecture
- Return Value : Boolean 

4. 학생이 강좌 정보를 갖고온다.(수신)

- Rest : GET방식
- URL 1 : http://113.198.84.74:8080/treeze/getLectures?{lectureName}
- URL 1 : http://113.198.84.74:8080/treeze/getMyLectures?{lectureName}&{professorEmail}
- URL 2: http://113.198.84.74:8080/treeze/getAllLectures
- Format : JSON
- Class name : Lecture
- Return Value : Lecture 리스트를 json으로 


 * */

@Controller
public class LectureController {
	private static final Logger logger = LoggerFactory.getLogger(LectureController.class);
	@Autowired private LectureService lectureService;
	
	@RequestMapping(value="/createLecture", method=RequestMethod.POST)
	public String createLecture(Lecture model, ModelMap map) {
		lectureService.saveLecture(model);
		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value="/setStateOfLecture", method=RequestMethod.POST)
	public String setStateOfLecture(Lecture model, ModelMap map) {
		lectureService.saveLecture(model);
		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value = "/getAllLectures", method = RequestMethod.GET)
	public String getAllLectures(ModelMap map) {
		Lecture lecture = lectureService.findAll();
		map.put("lecture", lecture);
		return "jsonView";

	}

	@RequestMapping(value = "/getLectures/{lectureName}", method = RequestMethod.GET)
	public String getLectures(@PathVariable String lectureName, ModelMap map) {
		Lecture lecture = lectureService.findBylectureName(lectureName);
		map.put("lecture", lecture);
		return "jsonView";

	}

		@RequestMapping(value = "/getMyLectures/{lectureName}/{professorEmail}", method = RequestMethod.GET)
	public String getLectures(@PathVariable String lectureName,@PathVariable String professorEmail, ModelMap map) {
		Lecture lecture = lectureService.findMyLectures(lectureName,professorEmail);
		map.put("lecture", lecture);
		return "jsonView";

	}

}
