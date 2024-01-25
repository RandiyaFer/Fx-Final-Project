package Dao.custom;

import Dao.CrudDao;
import dto.placeOrdDto;
import entity.Item;
import entity.placeOrder;

import java.sql.SQLException;

public interface placeOrdDao extends CrudDao<placeOrder> {
    placeOrdDto getLastOrder() throws SQLException, ClassNotFoundException;
}
