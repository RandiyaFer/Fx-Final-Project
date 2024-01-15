package Dao.custom.impl;

import Dao.custom.ItemDao;
import Dao.util.HibernateUtil;
import dto.ItemDto;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
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
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();

        Transaction transaction = session.beginTransaction();
        Item item = session.find(Item.class, entity.getItemCode());
        item.setItemCode(entity.getItemCode());
        item.setCategory(entity.getCategory());
        item.setSubCategory(entity.getSubCategory());
        item.setDescription(entity.getDescription());
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String value) throws SQLException, ClassNotFoundException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Item.class, value));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("FROM Item ");
        List<Item> list = query.list();
        session.close();
        return list;
    }
}
