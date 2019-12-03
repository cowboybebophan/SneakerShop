package com.ascending.jdbc;

import com.ascending.model.Order;
import com.ascending.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Data Access Object
public class ProductDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Step 1: Database information
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/sneaker_db";
    private static final String USER = "admin";
    private static final String PASS = "1234";

    public List<Product> getProducts(){
        logger.info("Enter the method getProducts...");
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //Step 2 : open a connection
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step 3: Execute a query
            logger.info("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM product";
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");

                //Fill the object
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(BigDecimal.valueOf(price));
                product.setStock(stock);
                products.add(product);
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
        logger.info("Exiting the method getProducts...");
        return products;
    }

    public int insertProduct(Product product){
        logger.info("Enter the method insertProduct...");
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
            String insert_sql = "INSERT INTO product (name, description, price, stock) " +
                    "values ('" + product.getName() + "', '" + product.getDescription() + "','"
                    + product.getPrice() + "', '" + product.getStock() + "');";
            rowsInserted = stmt.executeUpdate(insert_sql);

            if ( rowsInserted == 1){
                System.out.println("A new product has been inserted successfully!");
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
        logger.info("Exit the method insertProduct...");
        return rowsInserted;

    }

    public int deleteProduct(String condition){
        logger.info("Enter the method deleteProduct...");
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
            String delete_sql = "DELETE FROM product WHERE " + condition + ";";
            rowsDeleted = stmt.executeUpdate(delete_sql);

            if ( rowsDeleted == 1){
                System.out.println("A product has been deleted successfully!");
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
        logger.info("Exit the method deleteProduct...");
        return rowsDeleted;
    }

    public int updateProduct(String statement, String condition){
        logger.info("Enter the method updateProduct...");
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
            String update_sql = "UPDATE product SET " + statement + " WHERE " + condition;
            rowsUpdated = stmt.executeUpdate(update_sql);

            if ( rowsUpdated == 1){
                System.out.println("An existing product has been updated successfully!");
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
        logger.info("Exit the method updateProduct...");
        return rowsUpdated;
    }

}
