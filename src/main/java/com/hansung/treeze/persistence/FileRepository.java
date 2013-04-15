package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.UploadedFile;

public interface FileRepository extends JpaRepository<UploadedFile, Long> ,JpaSpecificationExecutor<UploadedFile>{

}

