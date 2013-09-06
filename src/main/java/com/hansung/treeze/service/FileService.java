package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	Object uploadFile(MultipartFile multipartFile, String version,String userType);
	Object getUploadedFile(Long id);
	Object findByversion(String version,String userType);
}
