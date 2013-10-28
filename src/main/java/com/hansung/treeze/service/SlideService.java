package com.hansung.treeze.service;

import java.util.List;

import com.hansung.treeze.model.Slide;

public interface SlideService {
	public Slide save(Slide slide);
	
	public List<Slide> findAll();
	
	public List<Slide> findByClassId(Long classId);
	
	public void delete(Slide slide);
	
	public void delete(Long id);
	
	public long count(Long classId);

	public Slide findByClassIdAndPage(Long classId, int page);

	public Slide find(long slideId);
}
