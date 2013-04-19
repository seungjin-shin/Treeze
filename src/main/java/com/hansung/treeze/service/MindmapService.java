package com.hansung.treeze.service;

import com.hansung.treeze.model.Mindmap;


public interface MindmapService {

	Mindmap save(Mindmap mindmap);
	Mindmap findByPTId(Long ptId);
	
}
