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
import com.hansung.treeze.model.Class;
import com.hansung.treeze.service.ClassService;

/*

2. 교수가 강의를 등록한다.(송신)

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/createClass
- Format : JSON
- Class name : Class
- Return Value : Boolean 

3-1. 교수가 Class에 본인아이피등록해둔다.(송신)

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/registerClassIp
- Format : JSON
- Class name : Class
- Return Value : Boolean 

5. 학생이 강의 정보들을 갖고온다.(수신)

- Rest : GET방식
- URL : http://113.198.84.74:8080/treeze/getClasses?{lectureName}&{professorEmail}
- Format : JSON
- Class name : Class
- Return Value : Class 리스트를 json으로 
 * */

@Controller
public class ClassController {
	private static final Logger logger = LoggerFactory.getLogger(ClassController.class);
	@Autowired private ClassService classService;


	@RequestMapping(value="/createClass", method=RequestMethod.POST)
	public String createClass(Class model, ModelMap map) {
		classService.saveClass(model);
		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value="/registerClassIp", method=RequestMethod.POST)
	public String registerClassIp(Class model, ModelMap map) {
		classService.saveClass(model);
		map.put("result", "success");

		return "jsonView";
	}


		@RequestMapping(value="/getClasses/{lectureName}/{professorEmail}", method=RequestMethod.GET)
	public String getClasses(@PathVariable String lectureName, @PathVariable String professorEmail, ModelMap map) {

		map.put("page", classService.getClasses(lectureName, professorEmail));
		return "jsonView";	
	}
}
