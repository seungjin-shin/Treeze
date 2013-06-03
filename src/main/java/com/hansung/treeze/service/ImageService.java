package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	Object uploadImage(MultipartFile multipartFile, Long classId);
	Object getUploadedImage(Long id);
	Object findByClassId(Long classId);

}
