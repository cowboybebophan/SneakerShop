package repository;

import com.ascending.init.AppInitializer;
import com.ascending.model.Customer;
import com.ascending.repository.CustomerDao;
import com.ascending.repository.CustomerDaoImpl;
import org.hibernate.SessionFactory;
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
public class CustomerDaoTest {
    @Autowired private Logger logger;
    @Autowired private SessionFactory sessionFactory;
    private CustomerDao customerDao;
    private Customer customer;

    @Before
    public void init(){ customerDao = new CustomerDaoImpl(logger, sessionFactory); }

    @Test
    public void saveCustomer() {
        customer = new Customer();

        customer.setName("Ryo");
        customer.setEmail("ryo@gmail.com");
        customer.setPassword("ryo123");
        customer.setAddress("Falls Church");

        Customer result = customerDao.save(customer);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());

        customerDao.delete(customer.getName());
    }

    @Test
    public void deleteCustomer() {
        customer = new Customer();

        customer.setName("Ryo");
        customer.setEmail("ryo@gmail.com");
        customer.setPassword("ryo123");
        customer.setAddress("Falls Church");

        customerDao.save(customer);
        boolean isSuccess = customerDao.delete(customer.getName());

        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void updateCustomer() {
        String customerName = "Han";
        customer = customerDao.getCustomerByName(customerName);

        logger.debug("The customer before update:");
        logger.debug(customer.toString());

        customer.setName("Han Wang");
        boolean isSuccess = customerDao.update(customer);
        Assert.assertEquals(isSuccess, true);

        customer = customerDao.getCustomerByName(customer.getName());
        logger.debug("The customer after update: ");
        logger.debug(customer.toString());

        customer.setName("Han");
        customerDao.update(customer);
    }

    @Test
    public void getCustomers() {
        List<Customer> customers= customerDao.getCustomers();
        int expectedNumOfCustomer = 4;

        for (Customer customer : customers){
            logger.debug(customer.toString());
        }

        Assert.assertEquals(expectedNumOfCustomer, customers.size());
    }

    @Test
    public void getCustomerByName() {
        String customerName = "Han";
        customer = customerDao.getCustomerByName(customerName);
        logger.debug(customer.toString());

        Assert.assertEquals(customerName, customer.getName());
    }

    @Test
    public void getCustomerAndOrders(){
        String cusName = "Han";
        List<Customer> resultList = customerDao.getCustomerAndOrders(cusName);

        Assert.assertEquals(1, resultList.size());

    }
}
