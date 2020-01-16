package service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.ascending.init.AppInitializer;
import com.ascending.service.MessageService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class MessageServiceTest {
    @Autowired
    private Logger logger;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS amazonSQS;

    private String queueName = "sneaker_shop_ascending";
    private String msg = "This is a message for test.";
    private List<Message> messages;

    @Before
    public void setUp() throws IOException {
        logger.info(">>>>>> Start testing...");

    }

    @After
    public void tearDown(){
        logger.info(">>>>>> End testing...");
    }

    @Test
    public void createQueue() {
        messageService.createQueue(queueName);
        Assert.assertEquals(1, amazonSQS.listQueues().getQueueUrls().size());
    }

    /*@Test
    public void getMessages() {

    }*/


}
