package Bo.custom;

import Bo.SuperBo;
import dto.AdminDto;
import dto.CustomerDto;
import dto.ItemDto;
import dto.RegisterDto;

import java.sql.SQLException;
import java.util.List;

public interface adminBo extends SuperBo {
    boolean saveOrder(AdminDto dto)throws SQLException, ClassNotFoundException;
    List<AdminDto> allAdmin()throws SQLException, ClassNotFoundException;
    boolean updateAdmin(AdminDto dto)throws SQLException, ClassNotFoundException;
}
