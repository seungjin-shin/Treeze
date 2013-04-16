package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.MindmapFile;


public interface MindmapService {

	Object uploadMindmapXML(MultipartFile multipartFile, String lectureName);
	Object getUploadedMindmapXML(Long ptId);
	
}
