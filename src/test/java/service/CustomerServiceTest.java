package service;

import com.ascending.init.AppInitializer;
import com.ascending.model.Customer;
import com.ascending.repository.CustomerDao;
import com.ascending.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CustomerServiceTest {
    @Autowired private Logger logger;
    @Autowired private CustomerService customerService;

    @Before
    public void init(){
        //You can not use new to creat the customerService object directly,
        //as the DI is used to inject logger in CustomerService, otherwise,
        //logger can not be injected, then it will throw NullPointException
        //So you have to use @Autowired inject the object customerService.
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        int expectedNumOfCus = 4;

        customers.forEach(customer -> logger.info(customer.toString()));
        Assert.assertEquals(expectedNumOfCus, customers.size());
    }

    @Test
    public void getCustomerByName(){
        String cusName = "Han";
        Customer customer = customerService.getCustomerByName(cusName);

        logger.info(customer.toString());

        Assert.assertEquals(cusName, customer.getName());

    }

    @Test
    public void getCustomerAndOrders(){
        String cusName = "Han";
        List<Customer> customers = customerService.getCustomerAndOrders(cusName);

        Assert.assertEquals(1, customers.size());
    }

}
