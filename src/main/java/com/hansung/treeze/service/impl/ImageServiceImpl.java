package com.hansung.treeze.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.UploadedFile;
import com.hansung.treeze.persistence.FileRepository;
import com.hansung.treeze.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Resource(name = "defaultProperties")
	protected Properties defaultProperties;
	
	@Autowired private FileRepository fileRepository;
	
	@Override
	public Map<String, String> uploadImage(MultipartFile multipartFile, String lectureName) {
		String uploadPath = defaultProperties.getProperty("file.img.path");
		Map<String, String> fileInfo = upload(multipartFile, uploadPath, lectureName);	

		String uploadedFileFullPath = fileInfo.remove("path");
		int dotIndex = uploadedFileFullPath.lastIndexOf(".");

		return fileInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public Object getUploadedImage(Long id) {
		return fileRepository.findOne(id);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	private Map<String, String> upload(MultipartFile multipartFile, String uploadPath, String lectureName) {
		String fileUniqueName = String.valueOf(System.nanoTime());

		String originFileName = multipartFile.getOriginalFilename();		
	    String uploadedFileFullPath = new StringBuffer(uploadPath).append(fileUniqueName).append("_").append(originFileName).toString();

		String uploadedPath = uploadedFileFullPath.substring(0, uploadedFileFullPath.lastIndexOf("/"));
    	File uploadedPathFile = new File(uploadedPath);
    	if(!uploadedPathFile.exists()){
    		uploadedPathFile.mkdirs();
    	}
	    
	    File uploadedFile = new File(uploadedFileFullPath);
	    try {
			FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(uploadedFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	    
	    UploadedFile uploadedFileBean = new UploadedFile();
	    uploadedFileBean.setLectureName(lectureName);
	    uploadedFileBean.setFileName(originFileName);
	    uploadedFileBean.setFilePath(uploadedFileFullPath);
	    uploadedFileBean.setFileSize(String.valueOf(multipartFile.getSize()));
	    fileRepository.save(uploadedFileBean);
	    fileRepository.findOne(uploadedFileBean.getId()).setFileDownloadUrl("http://113.198.84.74:8080/treeze/img/"+uploadedFileBean.getId()+"/");
	    fileRepository.save(uploadedFileBean);
	    
		Map<String, String> fileInfo = new HashMap<String, String>();
		fileInfo.put("name", originFileName);	
		fileInfo.put("path", uploadedFileFullPath);	
		fileInfo.put("id", String.valueOf(uploadedFileBean.getId()));

		return fileInfo;
	}
}
