package com.ascending.service;

import com.ascending.model.Product;
import com.ascending.repository.ProductDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductService {
    private Logger logger;
    private ProductDao productDao;

    @Autowired
    public ProductService(Logger logger, ProductDao productDao) {
        this.logger = logger;
        this.productDao = productDao;
    }

    public boolean save(Product product){
        return productDao.save(product);
    }

    public boolean delete(String productName){
        return productDao.delete(productName);
    }

    public boolean update(Product product){
        return productDao.update(product);
    }

    public List<Product> getProducts(){
        return productDao.getProducts();
    }

    public Product getProductByName(String prodName){
        return productDao.getProductByName(prodName);
    }

    public List<Product> getProductAndOrder(String prodName){
        return productDao.getProductAndOrder(prodName);
    }
}
