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
	private static final Logger logger = LoggerFactory.getLogger(ClassInfoController.class);
	@Autowired private ClassInfoService classService;


	@RequestMapping(value="/createClass", method=RequestMethod.POST)
	public String createClass(ClassInfo model, ModelMap map) {
		ClassInfo temp = classService.saveClass(model);
		temp.setClassId(temp.getId());
		classService.saveClass(temp);
		map.put("New Class", temp);

		return "jsonView";
	}
	
	@RequestMapping(value="/registerClassIp", method=RequestMethod.POST)
	public String registerClassIp(ClassInfo model, ModelMap map) {
		classService.saveClass(model);
		map.put("result", "success");

		return "jsonView";
	}


		@RequestMapping(value="/getClasses", method=RequestMethod.GET)
	public String getClasses(@RequestParam("lectureId") Long lectureId, ModelMap map) {
logger.info(""+lectureId);
		map.put("classes", classService.getClassInfoes(lectureId));
		return "jsonView";	
	}
}
