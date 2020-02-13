package repository;

import com.ascending.init.AppInitializer;
import com.ascending.model.Customer;
import com.ascending.model.Order;
import com.ascending.model.Product;
import com.ascending.repository.OrderDao;
import com.ascending.repository.OrderDaoImpl;
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
public class OrderDaoTest {
    @Autowired private Logger logger;
    @Autowired private SessionFactory sessionFactory;
    private static OrderDao orderDao;
    private Order order;
    private Customer customer;
    private Product product;

    @Before
    public void init(){ orderDao = new OrderDaoImpl(logger, sessionFactory); }

    @Test
    public void saveOrder() {
        customer = new Customer(3);
        product = new Product(3);
        order = new Order(customer, product, "Stripe");

        boolean isSuccess = orderDao.save(order);

        Assert.assertEquals(isSuccess, true);

        orderDao.delete(order.getId());
    }

    @Test
    public void deleteOrder() {
        customer = new Customer(3);
        product = new Product(3);
        order = new Order(customer, product, "Stripe");

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
}
