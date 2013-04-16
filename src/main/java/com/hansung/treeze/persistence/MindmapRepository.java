package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansung.treeze.model.MindmapFile;

public interface MindmapRepository extends JpaRepository<MindmapFile,Long>{

}
