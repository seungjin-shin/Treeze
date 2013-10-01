package com.hansung.treeze.service;

import com.hansung.treeze.model.Timer;


public interface TimerService {
	
	Timer saveTimer(Timer timer);
	Timer findTimer(Long classId);

}
