package Dao.custom.impl;

import Dao.custom.PartsDao;
import Dao.custom.addPartsDao;
import Dao.util.HibernateUtil;
import db.DBConnection;
import dto.ItemDto;
import dto.PartsDto;
import entity.Customer;
import entity.Parts;
import entity.addParts;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class addPartsDaoImpl implements addPartsDao {
    @Override
    public boolean save(addParts entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }
    @Override
    public boolean update(addParts entity) throws SQLException, ClassNotFoundException {

//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        Parts parts = session.find(addParts.class, entity.getId());
//        parts.setId(entity.getId());
//        parts.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        parts.setCustomer(entity.getCustomer());
//        parts.setSubCategory(entity.getSubCategory());
//        parts.setStatus(entity.getStatus());
//        parts.setPart(entity.getPart());
//        parts.setTotal(entity.getTotal());
//        session.save(parts);
//        transaction.commit();
//        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return true;
    }

    @Override
    public List<addParts> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM addParts");
        List<addParts> orderList = query.list();
        session.close();
        return orderList;
    }

    @Override
    public addParts get(Double total) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        return session.find(addParts.class, total);
    }
}
