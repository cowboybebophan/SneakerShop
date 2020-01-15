package com.ascending.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "sneaker_order")
public class Order extends Model{
    public Order() {}

    public Order(Customer customer, Product product, String payment) {
        this.customer = customer;
        this.product = product;
        this.payment = payment;
    }

    @Column(name = "payment")
    private String payment;

    @Column(name = "order_date")
    private LocalDate order_date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

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
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;
        return id == that.id &&
                payment.equals(that.payment) &&
                order_date.equals(that.order_date) &&
                customer.equals(that.customer) &&
                product.equals(that.product);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, payment, order_date, customer, product);
    }
}
