package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;

public interface ResultOfMultipleChoiceQuizRepository extends JpaRepository<ResultOfMultipleChoiceQuiz, Long> ,JpaSpecificationExecutor<ResultOfMultipleChoiceQuiz>{

}

