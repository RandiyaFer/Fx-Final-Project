package Dao.custom.impl;

import Dao.custom.adminDao;
import Dao.util.HibernateUtil;
import entity.Admin;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class adminDaoImpl implements adminDao {
    @Override
    public boolean save(Admin entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Admin entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Admin admin = session.find(Admin.class, entity.getEmail());
        admin.setEmail(entity.getEmail());
        admin.setPassword(entity.getPassword());
        session.save(admin);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Admin> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Admin ");
        List<Admin> list = query.list();
        session.close();
        return list;
    }

}
