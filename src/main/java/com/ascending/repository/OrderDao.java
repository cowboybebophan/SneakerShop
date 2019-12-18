package com.ascending.repository;

import com.ascending.model.Order;

import java.util.List;

public interface OrderDao {
    boolean save(Order order);
    boolean update(Order order);
    boolean delete(int orderId);
    List<Order> getOrders();
    Order getOrderById(int orderId);
    List<Order> getOrderByCustomer(String cusName);
    List<Order> getOrderByProduct(int prodId);
}
