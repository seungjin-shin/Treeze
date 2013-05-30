package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Note;
import com.hansung.treeze.persistence.NoteRepository;
import com.hansung.treeze.persistence.NoteSpecitications;
import com.hansung.treeze.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired private NoteRepository noteRepository;

	@Override
	public Note saveNote(Note note) {
		// TODO Auto-generated method stub
		return noteRepository.save(mindmap);
	}

	@Override
	public Object getNotes(int classId,String userEmail,String position) {
		// TODO Auto-generated method stub
		return noteRepository.findAll(Specifications.where(NoteSpecitications.isMyNote(classId, userEmail, position)));
	}

}