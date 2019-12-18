package com.ascending.repository;

import com.ascending.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer customer);
    boolean update(Customer customer);
    boolean delete(String customerName);
    List<Customer> getCustomers();
    Customer getCustomerByName(String customerName);
    List<Customer> getCustomerAndOrders(String cusName);
}
