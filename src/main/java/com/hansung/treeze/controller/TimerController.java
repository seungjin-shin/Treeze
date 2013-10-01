package com.hansung.treeze.controller;

import java.text.DateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Timer;
import com.hansung.treeze.service.TimerService;

@Controller
public class TimerController {
	
	@Autowired TimerService timerService;
	
	@RequestMapping(value = "/createTimer", method = RequestMethod.POST)
	public String createTimer(Timer model, ModelMap map) {
		timerService.saveTimer(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/getTimer", method = RequestMethod.GET)
	public String getTimer(@RequestParam("classId") Long classId, ModelMap map) {

		map.put("Timer",timerService.findTimer(classId));
		return "jsonView";
	}
	
	@RequestMapping(value = "/getServerTime", method = RequestMethod.GET)
	public String getServerTime(ModelMap map) {

		
		Date date = new Date();

		map.addAttribute("serverTime", date.getHours()+"a"+date.getMinutes()+"a"+date.getSeconds() );
		
		
		return "serverTime";
	}

}
