package jdbc;

import com.ascending.jdbc.OrderDao;
import com.ascending.model.Order;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoTest {
    private OrderDao orderDao;
    private Order order;

    // Setup and data creation.
    @Before
    public void init() { orderDao = new OrderDao(); }

    @Test
    public void getOrdersTest(){
        List<Order> orders = orderDao.getOrders();
        int expectationNumOfOrders = 9;

        for (Order order : orders){
            System.out.println(order);
        }

        Assert.assertEquals(expectationNumOfOrders, orders.size());
    }

    @Test
    public void insertOrderTest(){
        order = new Order();

        order.setCustomer_id(3);
        order.setProduct_id(4);
        order.setPayment("BoA");
        order.setOrder_date(LocalDate.now());

        int rowsInserted = orderDao.insertOrder(order);
        int insertedNumOfRows = 1;

        //Reset the table by deleting the inserted record;
        String condition = "payment = 'BoA'";
        orderDao.deleteOrder(condition);

        Assert.assertEquals(insertedNumOfRows, rowsInserted);
    }

    @Test
    public void deleteOrderTest(){
        String condition = "customer_id = 2 AND product_id = 4 AND payment = 'Venmo'";
        int rowsDeleted = orderDao.deleteOrder(condition);
        int deletedNumOfRows = 1;

        Assert.assertEquals(deletedNumOfRows, rowsDeleted);

        // Add the record back;
        order = new Order();
        order.setOrder_date(LocalDate.now());
        order.setCustomer_id(2);
        order.setProduct_id(4);
        order.setPayment("Venmo");

        orderDao.insertOrder(order);

    }

    @Test
    public void updateOrderTest(){
        String statement = "payment = 'BoA'";
        String condition = "customer_id = 2 AND product_id = 4 AND payment = 'Venmo'";
        int rowsUpdated = orderDao.updateOrder(statement, condition);
        int updatedNumOfRows = 1;

        Assert.assertEquals(updatedNumOfRows, rowsUpdated);

        // Update back
        statement = "payment = 'Venmo'";
        condition = "customer_id = 2 AND product_id = 4 AND payment = 'BoA'";

        orderDao.updateOrder(statement, condition);

    }

}