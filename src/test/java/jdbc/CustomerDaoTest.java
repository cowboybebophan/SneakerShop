package jdbc;

import com.ascending.jdbc.CustomerDao;
import com.ascending.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {
    private CustomerDao customerDao;

    @Before
    public void init() { customerDao = new CustomerDao();}

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerDao.getCustomers();
        int exceptionNumOfCus = 0;

        for (Customer customer : customers){
            System.out.println(customer);
        }

        Assert.assertEquals(exceptionNumOfCus, customers.size());
    }
}
