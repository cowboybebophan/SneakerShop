package com.ascending.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileServiceImpl implements FileService{
    //@Autowired
    private Logger logger;
    //@Autowired
    private AmazonS3 amazonS3;

    /* Constructor injection*/
    @Autowired
    public FileServiceImpl(Logger logger, AmazonS3 amazonS3) {
        this.logger = logger;
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadFile(String bucketName, MultipartFile file) throws IOException {
        try {
            if (amazonS3.doesObjectExist(bucketName, file.getOriginalFilename())) {
                logger.info(String.format("The file '%s' exists in the bucket %s", file.getOriginalFilename(), bucketName));
                return null;
            }

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            amazonS3.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s, size=%d was uploaded to bucket %s", file.getOriginalFilename(), file.getSize(), bucketName));
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return getFileUrl(bucketName, file.getOriginalFilename());
    }

    @Override
    public String getFileUrl(String bucketName, String fileName) {
        LocalDateTime expiration = LocalDateTime.now().plusDays(1);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
        generatePresignedUrlRequest.withMethod(HttpMethod.GET);
        generatePresignedUrlRequest.withExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)));

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    @Override
    public void createBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) amazonS3.createBucket(bucketName);
    }

    @Override
    public boolean saveFile(MultipartFile multipartFile, String filePath) {
        boolean isSuccess = false;

        try {
            File directory = new File(filePath);
            if (!directory.exists()) directory.mkdir();
            Path path = Paths.get(filePath, multipartFile.getOriginalFilename());
            multipartFile.transferTo(path);
            isSuccess = true;
            logger.info(String.format("The file %s is saved in the folder %s", multipartFile.getOriginalFilename(), filePath));
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        }

        return isSuccess;
    }

}
