package com.ascending.service;

import com.ascending.model.Order;
import com.ascending.repository.OrderDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OrderService {
    private Logger logger;
    private OrderDao orderDao;

    /* Constructor Injection */
    @Autowired
    public OrderService(Logger logger, OrderDao orderDao) {
        this.logger = logger;
        this.orderDao = orderDao;
    }

    public boolean save(Order order){
        return orderDao.save(order);
    }

    public boolean delete(int orderId){
        return orderDao.delete(orderId);
    }

    public boolean update(Order order){
        return orderDao.update(order);
    }

    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    public Order getOrderById(int orderId){
        return orderDao.getOrderById(orderId);
    }
}
