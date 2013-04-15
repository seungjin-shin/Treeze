package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.PTinfo;
import com.hansung.treeze.model.PTinfoList;
import com.hansung.treeze.persistence.PTinfoRepository;
import com.hansung.treeze.persistence.PTinfoSpecifications;
import com.hansung.treeze.service.PTinfoService;

@Service
public class PTinfoServiceImpl implements PTinfoService{
	
	@Autowired private PTinfoRepository ptInfoRepository;

	@Override
	public PTinfo save(PTinfo ptInfo) {
		// TODO Auto-generated method stub
		return ptInfoRepository.save(ptInfo);
	}

	@Override
	public boolean saveAll(PTinfoList ptInfoList) {
		// TODO Auto-generated method stub
		
		 List<PTinfo> ptInfoArrayList  = ptInfoList.getPTinfoList();
		for (int i = 0; i < ptInfoArrayList.size(); i++) {
			PTinfo ptInfo = ptInfoArrayList.get(i);
			save(ptInfo);
		}
		
		return true;
	}

	@Override
	public Object findByAllPTId(Integer ptId) {
		// TODO Auto-generated method stub
		return ptInfoRepository.findAll(Specifications.where(PTinfoSpecifications.isPTId(ptId)));
		//return ptInfoRepository.findAll(Specifications.where(isPTId(ptId)));
	}

}
