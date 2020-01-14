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
    @Autowired
    private Logger logger;

    @Autowired
    private CustomerDao customerDao;

    //@Autowired
    private CustomerService customerService;

    @Before
    public void init(){
        /*
            Demonstrate Constructor Injection is best way for DI, it ensure state safety of the object,
            in case there is no DI used in high-level modules,
            for example, here create the object DepartmentService by using new
        */
        customerService = new CustomerService(logger, customerDao);
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        int expectedNumOfCus = 9;

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
