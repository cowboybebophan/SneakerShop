package service;

import com.amazonaws.services.s3.AmazonS3;
import com.ascending.init.AppInitializer;
import com.ascending.service.FileService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class FileServiceTest {
    @Autowired
    private Logger logger;
    @Autowired
    private FileService fileService;
    @Autowired
    private AmazonS3 amazonS3;

    private String bucketName = "sneaker-shop.ascending.com";
    private String fileName = "test.txt";
    private MultipartFile multipartFile;
    private String path;

    @Before
    public void setUp() throws IOException {
        logger.info(">>>>>>>> Start testing...");

        File file = new File("/Users/jasper/IdeaProjects/SneakerShop/README.md");
        FileInputStream input = new FileInputStream(file);
        multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input);
        path = System.getProperty("user.dir") + File.separator + "temp";
    }

    @After
    public void tearDown(){
        logger.info(">>>>>> End testing...");
    }

    @Test
    public void createBucket(){
        fileService.createBucket(bucketName);
        Assert.assertEquals(true, amazonS3.doesBucketExistV2(bucketName));
        amazonS3.deleteBucket(bucketName);
    }

    @Test
    public void uploadFile() throws IOException {
        fileService.createBucket(bucketName);

        String fileUrl = fileService.uploadFile(bucketName, multipartFile);
        Assert.assertNotNull(fileUrl);

        // Tear down
        amazonS3.deleteObject(bucketName, multipartFile.getOriginalFilename());
        amazonS3.deleteBucket(bucketName);
    }

    /*@Test
    public void saveFile() throws IOException, FileNotFoundException {
        boolean isSuccess = fileService.saveFile(multipartFile, path);
        Assert.assertTrue(isSuccess);
    }*/
}
