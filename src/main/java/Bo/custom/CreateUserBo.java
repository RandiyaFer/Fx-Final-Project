package Bo.custom;

import Bo.SuperBo;
import dto.CreateUserDto;

import java.sql.SQLException;

public interface CreateUserBo extends SuperBo {

    boolean saveOrder(CreateUserDto dto)throws SQLException, ClassNotFoundException;
}
