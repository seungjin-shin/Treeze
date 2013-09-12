package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.NaviInfo;
import com.hansung.treeze.persistence.NaviInfoRepository;
import com.hansung.treeze.persistence.NaviInfoSpecifications;
import com.hansung.treeze.service.NaviInfoService;

@Service
public class NaviInfoServiceImpl implements NaviInfoService {

	@Autowired
	private NaviInfoRepository naviInfoRepository;

	@Override
	public NaviInfo saveNaviInfo(NaviInfo naviInfo) {
		// TODO Auto-generated method stub
		return naviInfoRepository.save(naviInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteNaviInfoAll(Long classId) {
		// TODO Auto-generated method stub

		List<NaviInfo> naviInfo = (List<NaviInfo>) getNaviInfoes(classId);

		if (naviInfo != null) {
			int size = naviInfo.size();

			for (int i = 0; i < size; i++)
				naviInfoRepository.delete(naviInfo.get(i));
		}
	}

	@Override
	public Object getNaviInfoes(Long classId) {
		// TODO Auto-generated method stub
		return naviInfoRepository.findAll(Specifications
				.where(NaviInfoSpecifications.isClassId(classId)));
	}

}