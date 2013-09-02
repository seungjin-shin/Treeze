package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Mindmap;
import com.hansung.treeze.persistence.MindmapRepository;
import com.hansung.treeze.persistence.MindmapSpecifications;
import com.hansung.treeze.service.MindmapService;

@Service
public class MindmapServiceImpl implements MindmapService{

	@Autowired private MindmapRepository mindmapRepository;


	@Override
	public Mindmap saveMindmap(Mindmap mindmap) {
		// TODO Auto-generated method stub

		return mindmapRepository.save(mindmap);
	}

	@Override
	public Mindmap findByclassId(Long classId) {
		// TODO Auto-generated method stub
		return mindmapRepository.findOne(Specifications.where(MindmapSpecifications.isClassId(classId)));
	}

	@Override
	public boolean exists(Long classId) {
		// TODO Auto-generated method stub
		Mindmap mindmap = null;
		mindmap = mindmapRepository.findOne(Specifications.where(MindmapSpecifications.isClassId(classId)));
		
		if(mindmap == null)
			return false;
			
		return mindmapRepository.exists(mindmap.getId());
	}

}