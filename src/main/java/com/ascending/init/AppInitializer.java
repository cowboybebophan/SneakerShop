package com.ascending.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"com.ascending.filter"})
@SpringBootApplication(scanBasePackages = {"com.ascending"})
public class AppInitializer {
    public static void main(String[] args) throws NullPointerException{
//        if (HibernateUtil.getSessionFactory() == null) {
//            throw new NullPointerException("The Hibernate session factory is NULL!");
//        }
        SpringApplication.run(AppInitializer.class, args);
    }
}

