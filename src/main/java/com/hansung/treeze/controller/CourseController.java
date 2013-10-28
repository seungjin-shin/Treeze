package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Course;
import com.hansung.treeze.service.CourseService;

@Controller
public class CourseController {
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/createCourse", method = RequestMethod.POST)
	public String createCourse(Course model, ModelMap map) {
		Course course = null;
		course = courseService.findMyCouse(model);
		
		if (course == null) {
			course = courseService.saveCourse(model);
			map.put("New Course", course);
			return "jsonView";
		}

		return "false";
	}
	
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
	public String deleteCourse(Course model, ModelMap map) {

		courseService.deleteCourse(model);
		map.put("result", "success");
		return "jsonView";
	}

}
