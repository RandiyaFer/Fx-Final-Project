package Dao.custom.impl;

import Dao.custom.CreateDao;
import Dao.util.HibernateUtil;
import entity.Create;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class CreateDaoImpl implements CreateDao {
    @Override
    public boolean save(Create entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Create entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Create> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
