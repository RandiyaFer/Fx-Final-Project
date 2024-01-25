package Dao.custom;

import Dao.CrudDao;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<Orders> {
    OrderDto getLastOrder() throws SQLException, ClassNotFoundException;

}
