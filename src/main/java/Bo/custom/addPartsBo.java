package Bo.custom;

import Bo.SuperBo;
import dto.OrderDto;
import dto.addPartsDto;

import java.sql.SQLException;
import java.util.List;

public interface addPartsBo extends SuperBo {

//    boolean saveOrder(addPartsDto dto)throws SQLException, ClassNotFoundException;
//    boolean updateOrder(addPartsDto dto)throws SQLException, ClassNotFoundException;
//    boolean deleteOrder(String code) throws SQLException, ClassNotFoundException;
    List<addPartsDto> allOrders()throws SQLException, ClassNotFoundException;
}
