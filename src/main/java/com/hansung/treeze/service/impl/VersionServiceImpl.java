package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Version;
import com.hansung.treeze.persistence.VersionRepository;
import com.hansung.treeze.persistence.VersionSpecifications;
import com.hansung.treeze.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService{

	@Autowired private VersionRepository versionRepository;



	@Override
	public Version saveVersion(Version version) {
		// TODO Auto-generated method stub
		return versionRepository.save(version);
	}
		@Override
	public Version findLastVersion(String userType) {
		// TODO Auto-generated method stub
		return versionRepository.findOne(Specifications.where(VersionSpecifications.isUserType(userType)));
	}
}
