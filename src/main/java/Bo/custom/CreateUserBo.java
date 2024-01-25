package Bo.custom;

import Bo.SuperBo;
import dto.CreateUserDto;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CreateUserBo extends SuperBo {

    boolean saveOrder(CreateUserDto dto)throws SQLException, ClassNotFoundException;
    List<CreateUserDto> allUsers()throws SQLException, ClassNotFoundException;
}
