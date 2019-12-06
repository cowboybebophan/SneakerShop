package com.ascending.repository;

import com.ascending.model.Order;

import java.util.List;

public interface OrderDao {
    boolean save(Order order);
    boolean update(Order order);
    boolean delete(int orderId);
    List<Order> getOrders();
    Order getOrderById(int orderId);
    /*List<Object[]> getOrderAndCustomer(String orderId);
    List<Object[]> getOrderAndCustomerAndProduct(String orderId);*/

}
