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

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.service.ClassInfoService;

@Controller
public class ClassInfoController {
	private static final Logger logger = LoggerFactory
			.getLogger(ClassInfoController.class);
	@Autowired
	private ClassInfoService classService;

	@RequestMapping(value = "/createClass", method = RequestMethod.POST)
	public String createClass(ClassInfo model, ModelMap map) {
		ClassInfo temp = classService.saveClass(model);
		temp.setClassId(temp.getId());
		classService.saveClass(temp);
		map.put("New Class", temp);

		return "jsonView";
	}

	@RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
	public String deleteClass(ClassInfo model, ModelMap map) {

		logger.info("무슨에러지 ? "+model.getId());
		logger.info("무슨에러지 ? "+model.getClassIP());
		logger.info("무슨에러지 ? "+model.getClassName());
		logger.info("무슨에러지 ? "+model.getPort());
		logger.info("무슨에러지 ? "+model.getProfessorEmail());
		logger.info("무슨에러지 ? "+model.getClassId());
		logger.info("무슨에러지 ? "+model.getLectureId());
		classService.deleteClass(model);
		map.put("result", "success");
		return "jsonView";
	}

	@RequestMapping(value = "/registerClassIp", method = RequestMethod.POST)
	public String registerClassIp(ClassInfo model, ModelMap map) {
		classService.saveClass(model);
		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value = "/getClasses", method = RequestMethod.GET)
	public String getClasses(@RequestParam("lectureId") Long lectureId,
			ModelMap map) {
		logger.info("" + lectureId);
		Object classes = classService.getClassInfoes(lectureId);
		map.put("classes",classes);
		return "jsonView";
	}
}
