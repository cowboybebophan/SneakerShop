package com.ascending.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sneaker_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "payment")
    private String payment;

    @Column(name = "order_date")
    private LocalDate order_date;

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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                ", payment='" + payment + '\'' +
                ", order_date='" + order_date + '\'' +
                '}';
    }
}
