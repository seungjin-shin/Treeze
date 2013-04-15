package com.hansung.treeze.service;

import com.hansung.treeze.model.PTinfo;
import com.hansung.treeze.model.PTinfoList;


public interface PTinfoService {

	PTinfo save(PTinfo ptInfo);
	boolean saveAll(PTinfoList ptInfoList);
	Object findByAllPTId(Integer ptId);
	
}
