package com.ascending.repository;

import com.ascending.model.Customer;
import com.ascending.model.Order;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    @Autowired private Logger logger;

    public boolean save(Order order){
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The order %s was inserted into the table.", order.toString()));

        return isSuccess;
    }

    public boolean delete(int orderId){
        String hql = "DELETE Order where id = :orderId";
        int deletedCount = 0;
        Transaction transaction = null;

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("orderId", orderId);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The order %s was deleted", orderId));

        return deletedCount >= 1 ? true : false;
    }

    public boolean update(Order order){
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();
        }
        catch (Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The order %s was updated.", order.toString()));

        return isSuccess;

    }

    public List<Order> getOrders(){
        String hql = "FROM Order";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Order> query= session.createQuery(hql);
            return query.list();
        }
    }

    public Order getOrderById(int orderId){
        if (orderId <= 0) return null;

        String hql = "FROM Order as ord where ord.id = :id";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Order> query = session.createQuery(hql);
            query.setParameter("id", orderId);
            return query.uniqueResult();
        }
    }

    public List<Order> getOrderByCustomer(String cusName){
        String hql = "FROM Order as ord where ord.customer.name = :cusName";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Order> query = session.createQuery(hql);
            query.setParameter("cusName", cusName);
            return query.list();
        }
    }

    public List<Order> getOrderByProduct(int prodId){
        String hql = "FROM Order as ord where ord.product.id = :prodId";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Order> query = session.createQuery(hql);
            query.setParameter("prodId", prodId);
            return query.list();
        }
    }
}
