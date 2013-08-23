package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Note;
import com.hansung.treeze.persistence.NoteRepository;
import com.hansung.treeze.persistence.NoteSpecifications;
import com.hansung.treeze.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService{

	@Autowired private NoteRepository noteRepository;

	@Override
	public Note saveNote(Note note) {
		// TODO Auto-generated method stub
		return noteRepository.save(note);
	}

	@Override
	public Object getNotes(Long classId, String userEmail) {
		// TODO Auto-generated method stub
		return noteRepository.findAll(Specifications.where(NoteSpecifications.isMyNote(classId, userEmail)));
	}

	@Override
	public Object getNote(Long classId, String userEmail, String nodeId) {
		// TODO Auto-generated method stub
		return noteRepository.findOne(Specifications.where(NoteSpecifications.isMyNote(classId, userEmail, nodeId)));
	}

}