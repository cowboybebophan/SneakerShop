package service;

import com.ascending.init.AppInitializer;
import com.ascending.model.Customer;
import com.ascending.model.Order;
import com.ascending.model.Product;
import com.ascending.service.OrderService;
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
public class OrderServiceTest {
    @Autowired private Logger logger;
    @Autowired private OrderService orderService;
    private Order order;
    private Customer customer;
    private Product product;
    
    @Before
    public void init(){}

    @Test
    public void saveOrder() {
        customer = new Customer(3);
        product = new Product(3);
        order = new Order(customer, product, "Stripe");

        boolean isSuccess = orderService.save(order);

        Assert.assertEquals(isSuccess, true);

        orderService.delete(order.getId());
    }

    @Test
    public void deleteOrder() {
        customer = new Customer(3);
        product = new Product(3);
        order = new Order(customer, product, "Stripe");

        orderService.save(order);

        boolean isSuccess = orderService.delete(order.getId());

        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void save(){
        int orderId = 1;
        order = orderService.getOrderById(orderId);

        logger.debug("The order before update:");
        logger.debug(order.toString());

        order.setPayment("test payment");
        boolean isSuccess = orderService.update(order);
        Assert.assertEquals(isSuccess, true);

        order = orderService.getOrderById(order.getId());
        logger.debug("The order after update: ");
        logger.debug(order.toString());

        order.setPayment("Venmo");
        orderService.update(order);

    }

    @Test
    public void getOrders(){
        List<Order> orders = orderService.getOrders();
        int expectedNumOfOrders = 7;

        for (Order order : orders){
            logger.debug(order.toString());
        }

        Assert.assertEquals(expectedNumOfOrders, orders.size());
    }

    @Test
    public void getOrdersById(){
        int orderId = 1;
        order = orderService.getOrderById(orderId);
        logger.debug(order.toString());

        Assert.assertEquals(orderId, order.getId());
    }

    @Test
    public void getOrderByCustomer(){
        String cusName = "Han";
        List<Order> orders = orderService.getOrderByCustomer(cusName);
        int expectedNumOfOrders = 2;

        for (Order order : orders){
            logger.debug(order.toString());
        }

        Assert.assertEquals(expectedNumOfOrders, orders.size());
    }

    @Test
    public void getOrderByProduct(){
        int prodId = 1;
        List<Order> orders = orderService.getOrderByProduct(prodId);
        int expectedNumOfOrders = 1;

        for (Order order : orders){
            logger.debug(order.toString());
        }

        Assert.assertEquals(expectedNumOfOrders, orders.size());
    }
}
