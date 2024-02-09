package Bo.custom.impl;

import Bo.custom.CreateUserBo;
import Dao.DaoFactory;
import Dao.custom.CreateUserDao;
import Dao.util.DaoType;
import dto.CreateUserDto;
import dto.CustomerDto;
import entity.CreateUser;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateUserBoImpl implements CreateUserBo {


    private CreateUserDao createDao = DaoFactory.getInstance().getDao(DaoType.CREATE);
    @Override
    public boolean saveOrder(CreateUserDto dto) throws SQLException, ClassNotFoundException {
        return createDao.save(new CreateUser(
                dto.getEmail(),
                dto.getPassword()
        ));
    }

    @Override
    public List<CreateUserDto> allUsers() throws SQLException, ClassNotFoundException {
        List<CreateUser> entityList = createDao.getAll();
        List<CreateUserDto> list = new ArrayList<>();
        for (CreateUser createUser:entityList) {
            list.add(new CreateUserDto(
                    createUser.getEmail(),
                    createUser.getPassword()
            ));
        }
        return list;
    }
}
