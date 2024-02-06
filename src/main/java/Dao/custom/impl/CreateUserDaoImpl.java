package Dao.custom.impl;

import Dao.custom.CreateUserDao;
import Dao.util.HibernateUtil;
import entity.CreateUser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class CreateUserDaoImpl implements CreateUserDao {
    @Override
    public boolean save(CreateUser entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CreateUser entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<CreateUser> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM CreateUser");
        List<CreateUser> list = query.list();
        session.close();
        return list;
    }
}
