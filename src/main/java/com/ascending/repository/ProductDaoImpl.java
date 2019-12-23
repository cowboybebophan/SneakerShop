package com.ascending.repository;

import com.ascending.model.Product;
import com.ascending.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    //@Autowired
    private Logger logger;
    
    //@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(Logger logger, SessionFactory sessionFactory) {
        this.logger = logger;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProducts(){
        String hql = "FROM Product";
        //Try resource, resource will close automatically
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            return query.list();
        }
    }

    @Override
    public Product getProductByName(String prodName){
        if (prodName == null) return null;
        String hql = "FROM Product as prod where lower(prod.name) = :name";

        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            query.setParameter("name", prodName.toLowerCase());

            return query.uniqueResult();
        }
    }

    @Override
    public Product getProductById(int prodId){
        String hql = "FROM Product as prod where prod.id = :id";

        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            query.setParameter("id", prodId);

            return query.uniqueResult();
        }
    }

    @Override
    public List<Product> getProductAndOrder(String prodName){
        if (prodName == null) return null;
        String hql = "FROM Product as prod left join fetch prod.orders where lower(prod.name) = :name";

        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery(hql);
            query.setParameter("name", prodName.toLowerCase());

            return query.list();
        }
    }

    @Override
    public boolean save(Product product){
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The product %s was inserted into the table.", product.toString()));

        return isSuccess;
    }

    @Override
    public boolean delete(String productName){
        String hql = "DELETE Product where name = :productN";
        int deletedCount = 0;
        Transaction transaction = null;

        try{
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Query<Product> query = session.createQuery(hql);
            query.setParameter("productN", productName);
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The product %s was deleted", productName));

        return deletedCount >= 1 ? true : false;
    }

    @Override
    public boolean update(Product product){
        Transaction transaction = null;
        boolean isSuccess = true;

        try{
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(product);
            transaction.commit();
        }
        catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if (isSuccess) logger.debug(String.format("The product %s was updated.", product.toString()));

        return isSuccess;

    }
}
