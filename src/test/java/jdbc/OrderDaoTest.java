package jdbc;

import com.ascending.jdbc.OrderDao;
import com.ascending.model.Order;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoTest {
    private OrderDao orderDao;
    public String p = "Ryo";

    @BeforeClass
    public static void classSetup(){

    }
    @AfterClass
    public static void classTeardown(){

    }

    // Setup and data creation.
    @Before
    public void init() {
        orderDao = new OrderDao();
        orderDao.insertOrder(p);
    }

    @After
    public  void tearDown(){
        orderDao.deleteOrder(p);
    }

    @Test
    public void getOrdersTest(){
        List<Order> orders = orderDao.getOrders();
        int expectationNumOfOrders = 10;

        for (Order order : orders){
            System.out.println(order);
        }

        Assert.assertEquals(expectationNumOfOrders, orders.size());
    }

    /*public void insertOrderTest(){
        int rowsInserted = orderDao.insertOrder();
        int insertedNumOfRows = 1;

        Assert.assertEquals(insertedNumOfRows, rowsInserted);

    }*/

    /*public void deleteOrderTest(){
        int rowsDeleted = orderDao.deleteOrder();
        int deletedNumOfRows = 1;

        Assert.assertEquals(deletedNumOfRows, rowsDeleted);
    }*/

    /*public void updateOrderTest(){
        int rowsUpdated = orderDao.updateOrder();
        int updatedNumOfRows = 1;

        Assert.assertEquals(updatedNumOfRows, rowsUpdated);
    }*/

}