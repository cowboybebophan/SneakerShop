package com.ascending.controller;

import com.ascending.model.Product;
import com.ascending.service.ProductService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/products", "/prod"})
public class ProductController {
    //@Autowired
    private Logger logger;
    //@Autowired
    private ProductService productService;

    @Autowired
    public ProductController(Logger logger, ProductService productService) {
        this.logger = logger;
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getProducts(){
        List<Product> products = productService.getProducts();
        return products;
    }

    @RequestMapping(value = "/{prodName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Product getProductByName(@PathVariable String prodName){
        Product product = productService.getProductByName(prodName);
        return product;
    }

    @RequestMapping(value = "/order/{prodName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> getProductAndOrder(@PathVariable String prodName){
        List<Product> products = productService.getProductAndOrder(prodName);
        return products;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createProduct(@RequestBody Product product){
        logger.debug("Product: " + product.toString());
        String msg = "The product was created.";
        boolean isSuccess = productService.save(product);

        if (!isSuccess) msg = "The product was not created.";

        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateProduct(@RequestBody Product product){
        logger.debug("Product: " + product.toString());
        String msg = "The product was updated.";
        boolean isSuccess = productService.update(product);

        if (!isSuccess) msg = "The product was not updated.";

        return msg;
    }

    @RequestMapping(value = "/{productName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteProduct(@PathVariable String productName){
        logger.debug("Product Name: " + productName);
        String msg = "The product was deleted.";
        boolean isSuccess = productService.delete(productName);

        if (!isSuccess) msg = "The product was not deleted.";

        return msg;
    }
}
