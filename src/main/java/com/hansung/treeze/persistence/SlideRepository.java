package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Slide;

public interface SlideRepository extends JpaRepository<Slide, Long> ,JpaSpecificationExecutor<Slide>{

}
