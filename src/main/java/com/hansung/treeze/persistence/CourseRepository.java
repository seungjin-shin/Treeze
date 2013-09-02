package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> ,JpaSpecificationExecutor<Course>{

}

