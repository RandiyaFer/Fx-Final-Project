package Dao.custom;

import Dao.CrudDao;
import dto.OrderDto;
import dto.PartsDto;
import entity.Orders;
import entity.Parts;

import java.sql.SQLException;

public interface PartsDao extends CrudDao<Parts> {
    PartsDto getLastOrder() throws SQLException, ClassNotFoundException;

}
