package Bo.custom;

import Bo.SuperBo;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {

    boolean saveOrder(OrderDto dto)throws SQLException, ClassNotFoundException;
    boolean updateOrder(OrderDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
    List<OrderDto> allOrders()throws SQLException, ClassNotFoundException;
    String generateId() throws SQLException, ClassNotFoundException;



}
