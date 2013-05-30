package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.User;

public interface UserRepository extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>{

}

