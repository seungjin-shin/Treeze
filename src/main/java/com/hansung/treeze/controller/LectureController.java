package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.service.LectureService;

@Controller
public class LectureController {
	private static final Logger logger = LoggerFactory
			.getLogger(LectureController.class);
	@Autowired
	private LectureService lectureService;

	@RequestMapping(value = "/createLecture", method = RequestMethod.POST)
	public String createLecture(Lecture model, ModelMap map) {
		lectureService.saveLecture(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/setStateOfLecture", method = RequestMethod.POST)
	public String setStateOfLecture(Lecture model, ModelMap map) {
		lectureService.saveLecture(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/getAllLectures", method = RequestMethod.GET)
	public String getAllLectures(ModelMap map) {
		Object lectures = lectureService.findAll();
		map.put("lectures", lectures);
		return "jsonView";

	}

	@RequestMapping(value = "/getLectures/{lectureName}", method = RequestMethod.GET)
	public String getLectures(@PathVariable String lectureName, ModelMap map) {
		Object lectures = lectureService.findBylectureName(lectureName);
		map.put("lectures", lectures);
		return "jsonView";

	}

	@RequestMapping(value = "/getMyLectures/{lectureName}/{professorEmail}", method = RequestMethod.GET)
	public String getLectures(@PathVariable String lectureName,
			@PathVariable String professorEmail, ModelMap map) {
		Object lectures = lectureService.findMyLectures(lectureName,
				professorEmail);
		map.put("lectures", lectures);
		return "jsonView";

	}

}
