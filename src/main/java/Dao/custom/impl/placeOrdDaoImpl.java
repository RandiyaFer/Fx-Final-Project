package Dao.custom.impl;

import Dao.custom.placeOrdDao;
import Dao.util.HibernateUtil;
import dto.placeOrdDto;
import entity.placeOrder;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class placeOrdDaoImpl implements placeOrdDao {
//    @Override
//    public boolean save(placeOrder entity) throws SQLException, ClassNotFoundException {
//        return false;
//    }

    @Override
    public boolean update(placeOrder entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<placeOrder> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public placeOrdDto getLastOrder() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public boolean save(placeOrder entity) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }
//
//    public ItemDto searchItem(String id) {
//        return null;
//    }
//
//    @Override
//    public boolean update(placeOrder entity) throws SQLException, ClassNotFoundException {
//
//        Session session = HibernateUtil.getSession();
//
//        Transaction transaction = session.beginTransaction();
//        placeOrder PlaceOrder = session.find(placeOrder.class, entity.getOrderId());
//        PlaceOrder.setOrderId(entity.getOrderId());
//        PlaceOrder.setCustomerID(entity.getCustomerID());
//        PlaceOrder.setItemCode(entity.getItemCode());
//        PlaceOrder.setAdvance(entity.getAdvance());
//        session.save(PlaceOrder);
//        transaction.commit();
//        session.close();
//        return true;
//    }

//    @Override
//    public boolean delete(String value) throws SQLException, ClassNotFoundException {
//
//        Session session = HibernateUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        session.delete(session.find(placeOrder.class, value));
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public List<placeOrder> getAll() throws SQLException, ClassNotFoundException {
//        Session session = HibernateUtil.getSession();
//        Query query = session.createQuery("FROM placeOrder ");
//        List<placeOrder> list = query.list();
//        session.close();
//        return list;
//    }
//
//    @Override
//    public placeOrdDto getLastOrder() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM placeOrder ORDER BY OrderId DESC LIMIT 1";
//        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();
//        if (resultSet.next()){
//            return new placeOrdDto(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5),
//                    null
//            );
//        }
//        return null;
//    }

//}
}

