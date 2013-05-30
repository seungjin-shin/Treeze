package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long> ,JpaSpecificationExecutor<Class>{

}

