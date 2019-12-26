package com.ascending.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    public class User{};
    public class Admin extends User{};

    //Constructors
    public Product() {

    }

    public Product(int id) {
        this.id = id;
    }

    public Product(String name, String description, BigDecimal price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({Product.Admin.class, Order.Admin.class})
    private int id;

    @Column(name = "name")
    @JsonView({Product.User.class, Order.User.class})
    private String name;

    @Column(name = "description")
    @JsonView({Product.User.class, Order.Admin.class})
    private String description;

    @Column(name = "price")
    @JsonView({Product.User.class, Order.User.class})
    private BigDecimal price;

    @Column(name = "stock")
    @JsonView({Product.Admin.class, Order.Admin.class})
    private int stock;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonView({Product.User.class})
    private Set<Order> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Set<Order> getOrders() {
        try {
            int size = orders.size();
        }
        catch (Exception e) {
            return null;
        }
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        for (Order ord : orders){
            if (ord.getProduct() == null) ord.setProduct(this);
        }
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
