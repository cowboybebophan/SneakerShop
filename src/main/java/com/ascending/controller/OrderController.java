package com.ascending.controller;

import com.ascending.model.Order;
import com.ascending.service.CustomerService;
import com.ascending.service.OrderService;
import com.ascending.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/orders", "/ord"})
public class OrderController {
    //@Autowired
    private Logger logger;
    //@Autowired
    private OrderService orderService;
    //@Autowired
    private CustomerService customerService;
    //Autowired
    private ProductService productService;

    @Autowired
    public OrderController(Logger logger, OrderService orderService, CustomerService customerService, ProductService productService) {
        this.logger = logger;
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrders(){
        List<Order> orders = orderService.getOrders();
        return orders;
    }

    @RequestMapping(value = "/id/{orderId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Order getOrderById(@PathVariable int orderId){
        Order order = orderService.getOrderById(orderId);
        return order;
    }

    @RequestMapping(value = "/cus/{cusName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrderByCustomer(@PathVariable String cusName){
        List<Order> orders = orderService.getOrderByCustomer(cusName);
        return orders;
    }

    @RequestMapping(value = "/prod/{prodId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrderByProduct(@PathVariable int prodId){
        List<Order> orders = orderService.getOrderByProduct(prodId);
        return orders;
    }

    @RequestMapping(value = "cus/{cusName}/prod/{prodId}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody Order order, @PathVariable("cusName") String cusName, @PathVariable("prodId") int prodId){
        order.setCustomer(customerService.getCustomerByName(cusName));
        order.setProduct(productService.getProductById(prodId));
        logger.debug("Order: " + order.toString());
        String msg = "A new order was created.";
        boolean isSuccess = orderService.save(order);

        if (!isSuccess) msg = "The order was not created.";

        return msg;
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateOrder(@RequestBody Order order, @PathVariable int orderId){
        logger.debug("Order: " + order.toString());
        String msg = "The order was updated.";
        boolean isSuccess = orderService.update(order);

        if (!isSuccess) msg = "The order was not updated.";

        return msg;
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteOrder(@PathVariable int orderId){
        logger.debug("Order Id: " + orderId);
        String msg = "The order was deleted.";
        boolean isSuccess = orderService.delete(orderId);

        if (!isSuccess) msg = "The order was not deleted.";

        return msg;
    }






}
