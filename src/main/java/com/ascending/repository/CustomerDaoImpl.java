package com.ascending.repository;

import com.ascending.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerDaoImpl implements CustomerDao{
    //@Autowired
    private Logger logger;

    //@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public CustomerDaoImpl(Logger logger, SessionFactory sessionFactory) {
        this.logger = logger;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer save(Customer customer){
        Transaction transaction = null;
        Customer result = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            result = customer;
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

//        if (isSuccess) logger.debug(String.format("The customer %s was inserted into the table.", customer.toString()));

        return result;
    }

    @Override
    public boolean delete(String customerName){
        String hql = "DELETE Customer where name = :customerN";
        int deletedCount = 0;
        Transaction transaction = null;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerN", customerName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The customer %s was deleted", customerName));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public boolean update(Customer customer){
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The customer %s was updated.", customer.toString()));

        return isSuccess;
    }

    @Override
    public List<Customer> getCustomers(){
        String hql = "FROM Customer";
        try(Session session = sessionFactory.openSession()){
            Query<Customer> query= session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Customer getCustomerByName(String customerName){
        if (customerName == null) return null;

        String hql = "FROM Customer as cus where lower(cus.name) = :name";

        try(Session session = sessionFactory.openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name", customerName.toLowerCase());

            return query.uniqueResult();
        }
    }

    @Override
    public List<Customer> getCustomerAndOrders(String cusName){
        if (cusName == null) return null;

        String hql = "FROM Customer as cus left join fetch cus.orders where lower(cus.name) = :name";

        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery(hql);
            query.setParameter("name", cusName.toLowerCase());

            List<Customer> resultList = query.list();

            resultList = resultList.stream().distinct().collect(Collectors.toList());

            for (Customer obj : resultList) {
                logger.debug(obj.getOrders().toString());
            }

            return resultList;
        }
    }
}
