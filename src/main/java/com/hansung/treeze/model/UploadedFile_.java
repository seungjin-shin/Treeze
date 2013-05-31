package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(UploadedFile.class)
public class UploadedFile_ {
    
    public static volatile SingularAttribute<UploadedFile, Integer> classId;
    public static volatile SingularAttribute<UploadedFile, String> fileName;
    public static volatile SingularAttribute<UploadedFile, String> filePath;
    public static volatile SingularAttribute<UploadedFile, String> fileSize;
    public static volatile SingularAttribute<UploadedFile, String> fileDownloadUrl;
}
