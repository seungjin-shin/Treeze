package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hansung.treeze.model.Mindmap;

public interface MindmapRepository extends JpaRepository<Mindmap,Long>{

}
