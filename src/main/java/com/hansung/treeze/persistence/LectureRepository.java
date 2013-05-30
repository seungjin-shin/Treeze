package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> ,JpaSpecificationExecutor<Lecture>{

}

