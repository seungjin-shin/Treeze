package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Timer;
import com.hansung.treeze.persistence.TimerRepository;
import com.hansung.treeze.persistence.TimerSpecifications;
import com.hansung.treeze.service.TimerService;

@Service
public class TimerServiceImpl implements TimerService{

	@Autowired TimerRepository timerRepository;
	
	@Override
	public Timer saveTimer(Timer timer) {
		// TODO Auto-generated method stub
		return timerRepository.save(timer);
	}

	@Override
	public Timer findTimer(Long classId) {
		// TODO Auto-generated method stub
		return timerRepository.findOne(Specifications
				.where(TimerSpecifications.isThisTimer(classId)));
	}

}
