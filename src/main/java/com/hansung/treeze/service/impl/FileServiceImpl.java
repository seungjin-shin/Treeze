package com.hansung.treeze.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.UploadedFile;
import com.hansung.treeze.persistence.FileRepository;
import com.hansung.treeze.persistence.FileSpecifications;
import com.hansung.treeze.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Resource(name = "defaultProperties")
	protected Properties defaultProperties;
	
	@Autowired private FileRepository fileRepository;
	
	@Override
	public Map<String, String> uploadFile(MultipartFile multipartFile, String version,String userType) {
		String uploadPath = defaultProperties.getProperty("file.upload.path");
		Map<String, String> fileInfo = upload(multipartFile, uploadPath, version,userType);	

		String uploadedFileFullPath = fileInfo.remove("path");
		int dotIndex = uploadedFileFullPath.lastIndexOf(".");

		return fileInfo;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Object getUploadedFile(Long id) {
		return fileRepository.findOne(id);
	}

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	private Map<String, String> upload(MultipartFile multipartFile, String uploadPath, String version,String userType) {
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
	    uploadedFileBean.setVersion(version);
	    uploadedFileBean.setUserType(userType);
	    uploadedFileBean.setFileName(originFileName);
	    uploadedFileBean.setFilePath(uploadedFileFullPath);
	    uploadedFileBean.setFileSize(String.valueOf(multipartFile.getSize()));
	    fileRepository.save(uploadedFileBean);
	    fileRepository.findOne(uploadedFileBean.getId()).setFileDownloadUrl("http://14.63.215.88:8080/treeze/img/"+uploadedFileBean.getId()+"/");
	    fileRepository.save(uploadedFileBean);
	    
		Map<String, String> fileInfo = new HashMap<String, String>();
		fileInfo.put("name", originFileName);	
		fileInfo.put("path", uploadedFileFullPath);	
		fileInfo.put("id", String.valueOf(uploadedFileBean.getId()));

		return fileInfo;
	}

	@Override
	public Object findByversion(String version,String userType){
		// TODO Auto-generated method stub
	
		return fileRepository.findAll(Specifications.where(FileSpecifications.isVersion(version,userType)));
	}
}
