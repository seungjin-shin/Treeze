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

import com.hansung.treeze.model.MindmapFile;
import com.hansung.treeze.persistence.MindmapRepository;
import com.hansung.treeze.service.MindmapService;

@Service
public class MindmapServiceImpl implements MindmapService{

	@Resource(name = "defaultProperties")
	protected Properties defaultProperties;
	
	@Autowired private MindmapRepository mindmapRepository;
	
	@Override
	public Map<String, String> uploadMindmapXML(MultipartFile multipartFile, String lectureName) {
		String uploadPath = defaultProperties.getProperty("file.img.path");
		Map<String, String> fileInfo = upload(multipartFile, uploadPath, lectureName);	

		String uploadedFileFullPath = fileInfo.remove("path");
		int dotIndex = uploadedFileFullPath.lastIndexOf(".");

		return fileInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public Object getUploadedMindmapXML(Long ptId) {
		return mindmapRepository.findOne(ptId);
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
	    
	    MindmapFile mindmapFileBean = new MindmapFile();
	    mindmapFileBean.setLectureName(lectureName);
	    mindmapFileBean.setFileName(originFileName);
	    mindmapFileBean.setFilePath(uploadedFileFullPath);
	    mindmapFileBean.setFileSize(String.valueOf(multipartFile.getSize()));
	    mindmapRepository.save(mindmapFileBean);
	    mindmapRepository.findOne(mindmapFileBean.getId()).setFileDownloadUrl("http://113.198.84.74:8080/treeze/img/"+mindmapFileBean.getId()+"/");
	    mindmapRepository.save(mindmapFileBean);
	    
		Map<String, String> fileInfo = new HashMap<String, String>();
		fileInfo.put("name", originFileName);	
		fileInfo.put("path", uploadedFileFullPath);	
		fileInfo.put("id", String.valueOf(mindmapFileBean.getId()));

		return fileInfo;
	}
}