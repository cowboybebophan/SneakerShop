package com.ascending.jdbc;

import com.ascending.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Data Access Object
public class OrderDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Step 1: Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/sneaker_db";
    private static final String USER = "admin";
    private static final String PASS = "1234";

    public List<Order> getOrders(){
        logger.info("Enter the method getOrders...");
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step 2 : open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM sneaker_order";
            rs = stmt.executeQuery(sql);

            //Step 4: Extract data from result set
            while (rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                int customer_id = rs.getInt("customer_id");
                int product_id = rs.getInt("product_id");
                String payment = rs.getString("payment");
                LocalDate order_date = rs.getObject("order_date", LocalDate.class);

                //Fill the object
                Order order = new Order();
                order.setId(id);
                order.setCustomer_id(customer_id);
                order.setProduct_id(product_id);
                order.setPayment(payment);
                order.setOrder_date(order_date);
                orders.add(order);
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
        logger.info("Exit the method getOrders...");
        return orders;
    }

    public int insertOrder(Order order){
        logger.info("Enter the method insertOrder...");
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
            String insert_sql = "INSERT INTO sneaker_order (customer_id, product_id, payment) " +
                    "values ('" + order.getCustomer_id() + "', '" + order.getProduct_id() + "','" + order.getPayment() + "');";
            rowsInserted = stmt.executeUpdate(insert_sql);

            if ( rowsInserted == 1){
                System.out.println("A new order has been inserted successfully!");
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
        logger.info("Exit the method insertOrder...");
        return rowsInserted;
    }

    public int deleteOrder(String condition){
        logger.info("Enter the method deleteOrder...");
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
            String delete_sql = "DELETE FROM sneaker_order WHERE " + condition + ";";
            rowsDeleted = stmt.executeUpdate(delete_sql);

            if ( rowsDeleted == 1){
                System.out.println("An order has been deleted successfully!");
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
        logger.info("Exit the method deleteOrder...");
        return rowsDeleted;
    }

    public int updateOrder(String statement, String condition){
        logger.info("Enter the method updateOrder...");
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
            String update_sql = "UPDATE sneaker_order SET " + statement + " WHERE " + condition;
            rowsUpdated = stmt.executeUpdate(update_sql);

            if ( rowsUpdated == 1){
                System.out.println("An existing order has been updated successfully!");
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
        logger.info("Exit the method updateOrders...");
        return rowsUpdated;
    }
}
