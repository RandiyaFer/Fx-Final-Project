package Bo.custom.impl;

import Bo.custom.CreateUserBo;
import Dao.DaoFactory;
import Dao.custom.CreateUserDao;
import Dao.util.DaoType;
import dto.CreateUserDto;
import entity.CreateUser;

import java.sql.SQLException;

public class CreateUserBoImpl implements CreateUserBo {


    private CreateUserDao createDao = DaoFactory.getInstance().getDao(DaoType.CREATE);
    @Override
    public boolean saveOrder(CreateUserDto dto) throws SQLException, ClassNotFoundException {
        return createDao.save(new CreateUser(
                dto.getEmail(),
                dto.getPassword(),
                dto.getConfirm()
        ));
    }
}
