package com.hansung.treeze.service;

import com.hansung.treeze.model.Mindmap;


public interface MindmapService {

	Mindmap saveMindmap(Mindmap mindmap);
	Mindmap findByclassId(Long classId);
	boolean exists(Long classId);
	
}
