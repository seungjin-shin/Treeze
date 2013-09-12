package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.NaviInfo;
import com.hansung.treeze.service.NaviInfoService;

/*

11-1. 학생이 서버에게 Note정보를 보낸다.(송신)
- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/attachNotes
- Format : JSON
- Class name : Note
- Return Value :  Boolean

11-2. 학생이 서버로부터 Note정보를 갖고온다.(수신)
- Rest : GET방식
- URL : http://113.198.84.74:8080/treeze/getNotes?{classId}&{userEmail}&{position}
- Format : JSON
- Class name : Note
- Return Value :  Note 리스트를 json으로 

 * */

@Controller
public class NaviInfoController {
	private static final Logger logger = LoggerFactory.getLogger(NaviInfoController.class);
	@Autowired private NaviInfoService naviInfoService;
	
	@RequestMapping(value="/createNaviInfo", method=RequestMethod.POST)
	public String createNote(NaviInfo model, ModelMap map) {
		
		naviInfoService.saveNaviInfo(model);

		map.put("result", "success");
		return "jsonView";
	}
	
	@RequestMapping(value="/deleteNaviInfoAll", method=RequestMethod.GET)
	public String deleteNote(@RequestParam("classId") Long classId, ModelMap map) {
		
		naviInfoService.deleteNaviInfoAll(classId);
		
		map.put("result", "success");
		return "jsonView";
	}

	@RequestMapping(value="/getNaviInfoes", method=RequestMethod.GET)
	public String getNotes(@RequestParam("classId") Long classId, ModelMap map) {

		map.put("NaviInfoes", naviInfoService.getNaviInfoes(classId));
		return "jsonView";	
	}

	
}
