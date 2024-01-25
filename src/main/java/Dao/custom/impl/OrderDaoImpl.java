package Dao.custom.impl;

import Dao.custom.OrderDao;
import Dao.util.HibernateUtil;
import db.DBConnection;
import dto.ItemDto;
import dto.OrderDto;
import entity.Customer;
import entity.Item;
import entity.OrderDetailsKey;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        Orders order = new Orders(
//                dto.getOrderId(),
//                dto.getDate(),
//                dto.getSubCategory(),
//                dto.getStatus()
//        );
//        order.setCustomer(session.find(Customer.class,dto.getCustomerID()));
//        session.save(order);
//
//        transaction.commit();
//        session.close();
        return true;
    }

    public ItemDto searchItem(String id) {
        return null;
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Orders order = session.find(Orders.class, entity.getOrderId());
        order.setOrderId(entity.getOrderId());
        order.setDate(entity.getDate());
        order.setCustomer(entity.getCustomer());
        order.setSubCategory(entity.getSubCategory());
        order.setStatus(entity.getStatus());
        session.save(order);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Orders.class, value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Orders");
        List<Orders> orderList = query.list();
        session.close();
        return orderList;
    }

    @Override
    public OrderDto getLastOrder() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }
}
