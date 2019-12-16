package com.ascending.service;

import com.ascending.model.Customer;
import com.ascending.repository.CustomerDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CustomerService {
    /*@Autowired*/
    private Logger logger;

    /*@Autowired*/
    private CustomerDao customerDao;

    @Autowired
    public CustomerService(Logger logger, CustomerDao customerDao) {
        this.logger = logger;
        this.customerDao = customerDao;
    }

    public boolean save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean delete(String cusName){
        return customerDao.delete(cusName);
    }

    public boolean update(Customer customer){
        return customerDao.update(customer);
    }

    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    public Customer getCustomerByName(String cusName){
        return customerDao.getCustomerByName(cusName);
    }

    public List<Customer> getCustomerAndOrders(String cusName){
        return customerDao.getCustomerAndOrders(cusName);
    }

    public List<Customer> getCustomerAndOrdersAndProducts(String cusName){
        return customerDao.getCustomerAndOrdersAndProducts(cusName);
    }

}
