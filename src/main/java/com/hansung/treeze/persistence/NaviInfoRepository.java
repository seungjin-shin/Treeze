package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.NaviInfo;

public interface NaviInfoRepository extends JpaRepository<NaviInfo, Long> ,JpaSpecificationExecutor<NaviInfo>{

}

