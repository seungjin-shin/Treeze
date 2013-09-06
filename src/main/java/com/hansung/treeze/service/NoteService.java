package com.hansung.treeze.service;

import com.hansung.treeze.model.Note;


public interface NoteService {

	Note saveNote(Note note);
	void deleteNote(Note note);
	Object getNotes(Long classId,String userEmail);
	Note getNote(Long classId,String userEmail,String nodeId);

}
