package jdbc;

import com.ascending.jdbc.CustomerDao;
import com.ascending.jdbc.ProductDao;
import com.ascending.model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {
    private CustomerDao customerDao;
    private Customer customer;

    @Before
    public void init() { customerDao = new CustomerDao();}

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerDao.getCustomers();
        int exceptionNumOfCus = 4;

        for (Customer customer : customers){
            System.out.println(customer);
        }

        Assert.assertEquals(exceptionNumOfCus, customers.size());
    }

    @Test
    public void insetCustomerTest(){
        customer = new Customer();

        customer.setName("Ryo");
        customer.setEmail("ryo@gamil.com");
        customer.setPassword("ryo123456");
        customer.setAddress("Herdon, Virginia");

        int rowsInserted = customerDao.insertCustomer(customer);
        int insertedNumOfRows = 1;

        Assert.assertEquals(insertedNumOfRows, rowsInserted);

        //Reset the table by deleting the inserted record;
        String condition = "name = 'Ryo'";
        customerDao.deleteCustomer(condition);
    }

    @Test
    public void deleteCustomerTest(){
        customer = new Customer();

        customer.setName("Ryo");
        customer.setEmail("ryo@gamil.com");
        customer.setPassword("ryo123456");
        customer.setAddress("Herdon, Virginia");

        int rowsInserted = customerDao.insertCustomer(customer);
        int insertedNumOfRows = 1;

        String condition = "name = 'Ryo'";
        customerDao.deleteCustomer(condition);

        Assert.assertEquals(insertedNumOfRows, rowsInserted);

    }

    @Test
    public void updateCustomerTest(){
        String statement = " name = 'Ryo' ";
        String condition = " name = 'Hangbo' ";

        int rowsUpdated = customerDao.updateCustomer(statement, condition);
        int updatedNumOfRows = 1;

        Assert.assertEquals(updatedNumOfRows, rowsUpdated);

        // Update back
        statement = " name = 'Hangbo' ";
        condition = " name = 'Ryo' ";

        customerDao.updateCustomer(statement, condition);

    }

}
