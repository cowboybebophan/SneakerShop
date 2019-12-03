package repository;

import com.ascending.model.Order;
import com.ascending.repository.OrderDao;
import com.ascending.repository.OrderDaoImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderDaoTest {
    private static OrderDao orderDao;
    private Order order;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init(){ orderDao = new OrderDaoImpl(); }

    @Test
    public void getOrders() {
        List<Order> orders= orderDao.getOrders();
        int expectedNumOfOrder = 8;

        for (Order order : orders){
            logger.debug(order.toString());
        }

        Assert.assertEquals(expectedNumOfOrder, orders.size());
    }

    @Test
    public void getOrderById() {
        int orderId = 1;
        order = orderDao.getOrderById(orderId);
        logger.debug(order.toString());

        Assert.assertEquals(orderId, order.getId());
    }

    @Test
    public void saveOrder() {
        order = new Order();

        order.setCustomer_id(3);
        order.setProduct_id(3);
        order.setPayment("Stripe");

        boolean isSuccess = orderDao.save(order);

        Assert.assertEquals(isSuccess, true);

        orderDao.delete(order.getId());
    }

    @Test
    public void deleteOrder() {
        order = new Order();

        order.setCustomer_id(3);
        order.setProduct_id(3);
        order.setPayment("Stripe");

        orderDao.save(order);
        boolean isSuccess = orderDao.delete(order.getId());

        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void updateOrder() {
        int orderId = 1;
        order = orderDao.getOrderById(orderId);

        logger.debug("The order before update:");
        logger.debug(order.toString());

        order.setPayment("test payment");
        boolean isSuccess = orderDao.update(order);
        Assert.assertEquals(isSuccess, true);

        order = orderDao.getOrderById(order.getId());
        logger.debug("The order after update: ");
        logger.debug(order.toString());

        order.setPayment("Venmo");
        orderDao.update(order);
    }
}
