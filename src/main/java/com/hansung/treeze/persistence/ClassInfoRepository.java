package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.ClassInfo;

public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> ,JpaSpecificationExecutor<ClassInfo>{

}

