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


@Controller
public class NaviInfoController {
	private static final Logger logger = LoggerFactory.getLogger(NaviInfoController.class);
	@Autowired private NaviInfoService naviInfoService;
	
	@RequestMapping(value="/createNaviInfo", method=RequestMethod.POST)
	public String createNaviInfo(NaviInfo model, ModelMap map) {
		
		naviInfoService.saveNaviInfo(model);

		map.put("result", "success");
		return "jsonView";
	}
	
	@RequestMapping(value="/deleteNaviInfoAll", method=RequestMethod.GET)
	public String deleteNaviInfoAll(@RequestParam("classId") Long classId, ModelMap map) {
		
		naviInfoService.deleteNaviInfoAll(classId);
		
		map.put("result", "success");
		return "jsonView";
	}

	@RequestMapping(value="/getNaviInfoes", method=RequestMethod.GET)
	public String getNaviInfoes(@RequestParam("classId") Long classId, ModelMap map) {

		map.put("NaviInfoes", naviInfoService.getNaviInfoes(classId));
		return "jsonView";	
	}

	
}
