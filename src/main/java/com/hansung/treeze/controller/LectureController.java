package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		Lecture temp = lectureService.saveLecture(model);
		temp.setLectureId(temp.getId());
		lectureService.saveLecture(temp);
		map.put("New Lecture", temp);

		return "jsonView";
	}
	
	@RequestMapping(value = "/setStateOfLecture", method = RequestMethod.POST)
	public String setStateOfLecture(Lecture model, ModelMap map) {
		Lecture lecture = lectureService.findOne(model.getLectureId());
		logger.info("state :::::::::::::"+model.getStateOfLecture());
		lecture.setStateOfLecture(model.getStateOfLecture());
		lectureService.saveLecture(lecture);
		map.put("lecture", lecture);

		return "jsonView";
	}

	@RequestMapping(value = "/getAllLectures", method = RequestMethod.GET)
	public String getAllLectures(ModelMap map) {
		Object lectures = lectureService.findAll();
		map.put("lectures", lectures);
		return "jsonView";

	}

	@RequestMapping(value = "/getLectures", method = RequestMethod.GET)
	public String getLectures(@RequestParam("lectureId") Long lectureId, ModelMap map) {
		Object lectures = lectureService.findBylectureName(lectureId);
		logger.info(""+lectureId);
		map.put("lectures", lectures);
		return "jsonView";

	}

	@RequestMapping(value = "/getMyLectures", method = RequestMethod.GET)
	public String getMyLectures(@RequestParam("professorEmail") String professorEmail, ModelMap map) {
		Object lectures = lectureService.findMyLectures(professorEmail);
		map.put("lectures", lectures);
		return "jsonView";

	}

}
