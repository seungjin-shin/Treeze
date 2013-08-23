package com.hansung.treeze.service;

import com.hansung.treeze.model.Note;


public interface NoteService {

	Note saveNote(Note note);
	Object getNotes(Long classId,String userEmail);
	Object getNote(Long classId,String userEmail,String nodeId);

}
