package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> ,JpaSpecificationExecutor<Timer>{

}

