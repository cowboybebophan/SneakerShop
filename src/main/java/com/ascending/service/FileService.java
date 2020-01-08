package com.ascending.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(String bucketName, MultipartFile file) throws IOException;
    String getFileUrl(String bucketName, String fileName);
    void createBucket(String bucketName);
    public boolean saveFile(MultipartFile multipartFile, String filePath);
}
