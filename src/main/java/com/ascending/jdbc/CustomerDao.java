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
                customer.setAddress(address);
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

    public int insertCustomer(Customer customer){
        logger.info("Enter the method insertCustomer...");
        Connection conn = null;
        Statement stmt = null;
        int rowsInserted = 0;

        try {
            //Step 2 : open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String insert_sql = "INSERT INTO customer (name, email, password, address) " +
                    "values ('" + customer.getName() + "', '" + customer.getEmail() + "','"
                    + customer.getPassword() + "', '" + customer.getAddress() + "');";
            rowsInserted = stmt.executeUpdate(insert_sql);

            if ( rowsInserted == 1){
                System.out.println("A new customer has been inserted successfully!");
            } else{
                System.out.println("Insertion not completed.");
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            // finally block is used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("Exit the method insertCustomer...");
        return rowsInserted;
    }

    public int deleteCustomer(String condition){
        logger.info("Enter the method deleteCustomer...");
        Connection conn = null;
        Statement stmt = null;
        int rowsDeleted = 0;

        try {
            //Step 2 : open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String delete_sql = "DELETE FROM customer WHERE " + condition + ";";
            rowsDeleted = stmt.executeUpdate(delete_sql);

            if ( rowsDeleted == 1){
                System.out.println("A customer has been deleted successfully!");
            } else{
                System.out.println("Deletion not completed.");
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            // finally block is used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("Exit the method deleteCustomer...");
        return rowsDeleted;
    }

    public int updateCustomer(String statement, String condition){
        logger.info("Enter the method updateCustomer...");
        Connection conn = null;
        Statement stmt = null;
        int rowsUpdated = 0;

        try {
            //Step 2 : open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String update_sql = "UPDATE customer SET " + statement + " WHERE " + condition;
            rowsUpdated = stmt.executeUpdate(update_sql);

            if ( rowsUpdated == 1){
                System.out.println("An existing customer has been updated successfully!");
            } else{
                System.out.println("Update not completed.");
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        } finally {
            // finally block is used to close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("Exit the method updateCustomer...");
        return rowsUpdated;
    }


}
