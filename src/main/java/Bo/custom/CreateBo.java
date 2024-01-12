package Bo.custom;

import Bo.SuperBo;
import dto.CreateDto;

import java.sql.SQLException;

public interface CreateBo extends SuperBo {

    boolean saveOrder(CreateDto dto)throws SQLException, ClassNotFoundException;
}
