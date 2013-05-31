package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	Object uploadImage(MultipartFile multipartFile, int classId);
	Object getUploadedImage(Long id);
	Object findByClassId(int classId);

}
