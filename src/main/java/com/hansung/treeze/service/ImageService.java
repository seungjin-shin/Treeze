package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	Object uploadImage(MultipartFile multipartFile, String lectureName);
	Object getUploadedImage(Long id);

}
