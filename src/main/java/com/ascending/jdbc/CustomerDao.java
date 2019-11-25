package com.ascending.jdbc;

import com.ascending.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Dao: Data Access Object
public class CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 1. Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/sneaker_db";
    private static final String USER = "admin";
    private static final String PASS = "1234";

    public List<Customer> getCustomers(){
        logger.info("Enter the method getCustomers...");
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 2. Open a connection
            logger.info("Connecting to sneaker_db...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 3. Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM customer";
            rs = stmt.executeQuery(sql);

            // 4. Extract data from result set
            while (rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String address = rs.getString("address");

                //Fill the object
                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setEmail(email);
                customer.setPassword(password);
                customers.add(customer);
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            // finally block is used to close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("Exit the method getCustomers...");
        return customers;
    }
}
