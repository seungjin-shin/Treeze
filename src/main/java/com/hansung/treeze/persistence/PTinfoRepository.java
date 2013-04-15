package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.PTinfo;

public interface PTinfoRepository extends JpaRepository<PTinfo,Long>, JpaSpecificationExecutor<PTinfo>{

}
