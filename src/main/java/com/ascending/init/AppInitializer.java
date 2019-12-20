package com.ascending.init;

import com.ascending.util.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ascending"})
public class AppInitializer {
    public static void main(String[] args) throws NullPointerException{
        if (HibernateUtil.getSessionFactory() == null) {
            throw new NullPointerException("The Hibernate session factory is NULL!");
        }
        SpringApplication.run(AppInitializer.class, args);
    }
}

