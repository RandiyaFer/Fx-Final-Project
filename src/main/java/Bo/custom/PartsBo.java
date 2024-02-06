package Bo.custom;

import Bo.SuperBo;
import dto.OrderDto;
import dto.PartsDto;

import java.sql.SQLException;
import java.util.List;

public interface PartsBo extends SuperBo {

    boolean saveOrder(PartsDto dto)throws SQLException, ClassNotFoundException;
    boolean updateOrder(PartsDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
    List<PartsDto> allOrders()throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;



}
