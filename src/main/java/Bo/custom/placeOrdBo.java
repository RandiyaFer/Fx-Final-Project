package Bo.custom;

import Bo.SuperBo;
import dto.RegisterDto;
import dto.placeOrdDto;

import java.sql.SQLException;
import java.util.List;

public interface placeOrdBo extends SuperBo {
    boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    List<placeOrdDto> allOrders() throws SQLException, ClassNotFoundException;

    boolean saveOrder(placeOrdDto dto) throws SQLException, ClassNotFoundException;

    String generateId() throws SQLException, ClassNotFoundException;
}
