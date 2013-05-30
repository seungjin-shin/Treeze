package com.hansung.treeze.service;

import com.hansung.treeze.model.Note;


public interface NoteService {

	Note saveNote(Note note);
	Object getNotes(int classId,String userEmail,String position);

}
