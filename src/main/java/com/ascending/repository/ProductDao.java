package com.ascending.repository;

import com.ascending.model.Product;

import java.util.List;

public interface ProductDao {
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(String productName);
    List<Product> getProducts();
    Product getProductByName(String prodName);
    List<Product> getProductAndOrder(String prodName);
}
