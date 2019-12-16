package com.ascending.controller;

import com.ascending.model.Customer;
import com.ascending.service.CustomerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    //@Autowired
    private Logger logger;
    //@Autowired
    private CustomerService customerService;

    public CustomerController(Logger logger, CustomerService customerService ) {
        this.logger = logger;
        this.customerService = customerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomersByName(@PathVariable String customerName){
        Customer customer = customerService.getCustomerByName(customerName);
        return customer;
    }

    @RequestMapping(value = "/with-orders", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomerAndOrders(@RequestBody String customerName){
        List<Customer> customers = customerService.getCustomerAndOrders(customerName);
        return customers;
    }

    @RequestMapping(value = "/with-orders-and-products", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomerAndOrdersAndProducts(@RequestBody String customerName){
        List<Customer> customers = customerService.getCustomerAndOrdersAndProducts(customerName);
        return customers;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createCustomer(@RequestBody Customer customer){
        logger.debug("Customer:" + customer.toString());
        String msg = "A new customer was created successfully!";
        boolean isSuccess = customerService.save(customer);

        if (!isSuccess) msg = "The customer was not created.";

        return msg;
    }

    @RequestMapping(value = "/{customerName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteCustomer(@PathVariable String customerName){
        logger.debug("Customer name:" + customerName);
        String msg = "The customer was deleted";
        boolean isSuccess = customerService.delete(customerName);

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
