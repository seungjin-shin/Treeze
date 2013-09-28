package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.ResultOfQuiz;

public interface ResultOfQuizRepository extends JpaRepository<ResultOfQuiz, Long> ,JpaSpecificationExecutor<ResultOfQuiz>{

}

