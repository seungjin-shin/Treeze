package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> ,JpaSpecificationExecutor<Note>{

}

