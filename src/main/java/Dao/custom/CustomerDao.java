package Dao.custom;

import Bo.custom.CustomerBo;
import Dao.CrudDao;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;

public interface CustomerDao extends CrudDao<Customer> {
    Customer get(String id) throws SQLException, ClassNotFoundException;
}
