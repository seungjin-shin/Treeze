package com.hansung.treeze.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	Object uploadFile(MultipartFile multipartFile, Long versionId);
	Object getUploadedFile(Long id);
	Object findByVersionId(Long versionId);
}
