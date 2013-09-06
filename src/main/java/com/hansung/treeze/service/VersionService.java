package com.hansung.treeze.service;

import com.hansung.treeze.model.Version;

public interface VersionService {
	Version saveVersion(Version version);
	Version findLastVersion(String userType);

	
}
