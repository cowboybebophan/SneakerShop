package com.ascending.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sneaker_order")
public class Order {
    public class User{};
    public class Admin extends User{};

    public Order() {}

    public Order(Customer customer, Product product, String payment) {
        this.customer = customer;
        this.product = product;
        this.payment = payment;
    }

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({Order.Admin.class})*/
    private int id;

    @Column(name = "payment")
    @JsonView({Order.User.class})
    private String payment;

    @Column(name = "order_date")
    @JsonView({Order.User.class})
    private LocalDate order_date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonView({Order.User.class})
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonView({Order.User.class})
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrder_date() {
        return this.order_date;
    }

    public void setOrder_date(LocalDate date) {
        this.order_date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_id=" + customer.getId() +
                ", product_id=" + product.getId() +
                ", payment='" + payment + '\'' +
                ", order_date='" + order_date + '\'' +
                '}';
    }
}
