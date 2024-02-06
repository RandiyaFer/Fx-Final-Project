package Dao.custom.impl;

import Dao.custom.CustomerDao;
import Dao.util.HibernateUtil;
import dto.CustomerDto;
import dto.OrderDto;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    public CustomerDto searchCustomer(String id) {
        return null;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.find(Customer.class, entity.getCustomerID());
        customer.setCustomerID(entity.getCustomerID());
        customer.setName(entity.getName());
        customer.setContactNo(entity.getContactNo());
        customer.setEmail(entity.getEmail());
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Customer.class, value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Customer ");
        List<Customer> list = query.list();
        session.close();
        return list;
    }


    @Override
    public Customer get(String id) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        return session.find(Customer.class, id);
    }
}
