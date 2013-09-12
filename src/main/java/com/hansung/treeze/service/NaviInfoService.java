package com.hansung.treeze.service;

import com.hansung.treeze.model.NaviInfo;


public interface NaviInfoService {

	NaviInfo saveNaviInfo(NaviInfo note);
	void deleteNaviInfoAll(Long classId);
	Object getNaviInfoes(Long classId);

}
