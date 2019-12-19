package com.ascending.controller;

import com.ascending.model.Order;
import com.ascending.service.OrderService;
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

    @Autowired
    public OrderController(Logger logger, OrderService orderService) {
        this.logger = logger;
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrders(){
        List<Order> orders = orderService.getOrders();
        return orders;
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Order getOrderById(@PathVariable int orderId){
        Order order = orderService.getOrderById(orderId);
        return order;
    }

    @RequestMapping(value = "/customer/{cusName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrderByCustomer(@PathVariable String cusName){
        List<Order> orders = orderService.getOrderByCustomer(cusName);
        return orders;
    }

    @RequestMapping(value = "/product/{prodId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrderByProduct(@PathVariable int prodId){
        List<Order> orders = orderService.getOrderByProduct(prodId);
        return orders;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createOrder(@RequestBody Order order){
        logger.debug("Order: " + order.toString());
        String msg = "The order was created.";
        boolean isSuccess = orderService.save(order);

        if (!isSuccess) msg = "The order was not created.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateOrder(@RequestBody Order order){
        logger.debug("Order: " + order.toString());
        String msg = "The order was updated.";
        boolean isSuccess = orderService.update(order);

        if (!isSuccess) msg = "The order was not updated.";

        return msg;
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteOrder(@RequestBody int orderId){
        logger.debug("Order Id: " + orderId);
        String msg = "The order was deleted.";
        boolean isSuccess = orderService.delete(orderId);

        if (!isSuccess) msg = "The order was not deleted.";

        return msg;
    }






}
