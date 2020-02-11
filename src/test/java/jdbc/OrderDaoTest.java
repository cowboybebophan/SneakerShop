package jdbc;

import com.ascending.jdbc.OrderDao;
import com.ascending.model.Customer;
import com.ascending.model.Order;
import com.ascending.model.Product;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private OrderDao orderDao;
    private Order order;

    // Setup and data creation.
    @Before
    public void init() { orderDao = new OrderDao(); }

    @Test
    public void getOrdersTest(){
        List<Order> orders = orderDao.getOrders();
        int expectationNumOfOrders = 8;

        for (Order order : orders){
            logger.debug(order.toString());
        }

        Assert.assertEquals(expectationNumOfOrders, orders.size());
    }

    @Test
    public void insertOrderTest(){
        Customer customer = new Customer(3);
        Product product = new Product(3);
        order = new Order(customer, product, "BoA");

        int rowsInserted = orderDao.insertOrder(order);
        int insertedNumOfRows = 1;

        //Reset the table by deleting the inserted record;
        String condition = "customer_id = 3 AND product_id = 3 AND payment = 'BoA'";
        orderDao.deleteOrder(condition);

        Assert.assertEquals(insertedNumOfRows, rowsInserted);
    }

    @Test
    public void deleteOrderTest(){
        Customer customer = new Customer(4);
        Product product = new Product(4);
        order = new Order(customer, product, "Test");
        orderDao.insertOrder(order);

        String condition = "customer_id = 4 AND product_id = 4 AND payment = 'Test'";

        int rowsDeleted = orderDao.deleteOrder(condition);
        int deletedNumOfRows = 1;

        Assert.assertEquals(deletedNumOfRows, rowsDeleted);
    }

    @Test
    public void updateOrderTest(){
        String statement = "payment = 'Test'";
        String condition = "customer_id = 3 AND product_id = 2 AND payment = 'Credit Card'";
        int rowsUpdated = orderDao.updateOrder(statement, condition);
        int updatedNumOfRows = 1;

        Assert.assertEquals(updatedNumOfRows, rowsUpdated);

        // Update back
        statement = "payment = 'Credit Card'";
        condition = "customer_id = 3 AND product_id = 2 AND payment = 'Test'";

        orderDao.updateOrder(statement, condition);
    }
}
