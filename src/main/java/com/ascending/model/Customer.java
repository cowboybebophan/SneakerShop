package com.ascending.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    public class User{};
    public class Admin extends User{};

    public Customer() {

    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView({Customer.Admin.class, Order.Admin.class})
    private int id;

    @Column(name = "name")
    @JsonView({Customer.User.class, Order.User.class})
    private String name;

    @Column(name = "email")
    @JsonView({Customer.User.class, Order.Admin.class})
    private String email;

    @Column(name = "password")
    @JsonView({Customer.Admin.class, Order.Admin.class})
    private String password;

    @Column(name = "address")
    @JsonView({Customer.User.class, Order.Admin.class})
    private String address;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonView({Customer.Admin.class})
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        for (Order o : orders) {
            if (o.getCustomer() == null) o.setCustomer(this);
        }
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
