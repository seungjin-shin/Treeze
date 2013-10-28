package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Slide;
import com.hansung.treeze.persistence.SlideRepository;
import com.hansung.treeze.persistence.SlideSpecifications;
import com.hansung.treeze.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
	private SlideRepository slideRepository;

	@Override
	public Slide save(Slide slide) {
		return slideRepository.save(slide);
	}

	@Override
	public List<Slide> findAll() {
		return slideRepository.findAll();
	}

	@Override
	public List<Slide> findByClassId(Long classId) {
		return slideRepository.findAll(SlideSpecifications.isClassId(classId),
				new Sort("page"));
	}
	
	@Override
	public Slide findByClassIdAndPage(Long classId, int page) {
		return slideRepository.findOne(SlideSpecifications.isSlideAndPage(classId, page));
	}

	@Override
	public void delete(Slide slide) {
		slideRepository.delete(slide);
	}

	@Override
	public void delete(Long id) {
		slideRepository.delete(id);
	}

	@Override
	public long count(Long classId) {
		return slideRepository.count(SlideSpecifications.isClassId(classId));

	}

	@Override
	public Slide find(long slideId) {
		return slideRepository.findOne(slideId);
	}

}
