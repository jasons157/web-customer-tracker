package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //Need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;//This injects the session factory bean from the config

    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //create a query
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName",
                                                        Customer.class);

        //execute query and get result list
        List<Customer> customerList = theQuery.getResultList();

        //return list of customers
        return customerList;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();

        //saveOrUpdate() will INSERT if it's a new customer, or UPDATE if existing
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, theId);
    }

    @Override
    public void deleteCustomer(int theId) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, theId);

        session.delete(customer);
    }
}
