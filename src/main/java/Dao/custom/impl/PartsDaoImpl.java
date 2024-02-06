package Dao.custom.impl;

import Dao.custom.PartsDao;
import Dao.util.HibernateUtil;
import db.DBConnection;
import dto.ItemDto;
import dto.PartsDto;
import entity.Parts;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PartsDaoImpl implements PartsDao {
    @Override
    public boolean save(Parts entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    public ItemDto searchItem(String id) {
        return null;
    }

    @Override
    public boolean update(Parts entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Parts parts = session.find(Parts.class, entity.getId());
        parts.setId(entity.getId());
        parts.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        parts.setCustomer(entity.getCustomer());
        parts.setSubCategory(entity.getSubCategory());
        parts.setStatus(entity.getStatus());
        parts.setPart(entity.getPart());
        parts.setTotal(entity.getTotal());
        session.save(parts);
        transaction.commit();
        session.close();
        return true;
    }


    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Parts.class, value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Parts> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Parts");
        List<Parts> orderList = query.list();
        session.close();
        return orderList;
    }

    @Override
    public PartsDto getLastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Parts ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new PartsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    Double.parseDouble(resultSet.getString(7))
            );
        }
        return null;
    }
}
