package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Version;

public interface VersionRepository extends JpaRepository<Version, Long> ,JpaSpecificationExecutor<Version>{

}

