package com.ascending.controller;

import com.ascending.model.Customer;
import com.ascending.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/customers", "/cus"})
public class CustomerController {
    //@Autowired
    private Logger logger;
    //@Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerController(Logger logger, CustomerService customerService ) {
        this.logger = logger;
        this.customerService = customerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    // @Cacheable(value = "customers")
    @RequestMapping(value = "/{cusName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomersByName(@PathVariable String cusName){
        Customer customer = customerService.getCustomerByName(cusName);
        return customer;
    }

    @RequestMapping(value = "/{cusName}/orders", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomerAndOrders(@PathVariable String cusName){
        List<Customer> customers = customerService.getCustomerAndOrders(cusName);
        return customers;
    }

    // @CachePut(value = "customers", key = "#customer.name", unless = "#customer.name == null")
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Customer createCustomer(@RequestBody Customer customer){
        logger.debug("Customer:" + customer.toString());
        Customer result = customerService.save(customer);
        return result;
    }

    @RequestMapping(value = "/{cusName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteCustomer(@PathVariable String cusName){
        logger.debug("Customer name:" + cusName);
        String msg = "The customer was deleted";
        boolean isSuccess = customerService.delete(cusName);

        if (!isSuccess) msg = "the customer was not deleted.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCustomer(@RequestBody Customer customer){
        logger.debug("Customer:" + customer.toString());
        String msg = "The customer was updated.";
        boolean isSuccess = customerService.update(customer);

        if (!isSuccess) msg = "The customer was not updated.";

        return msg;
    }
}
