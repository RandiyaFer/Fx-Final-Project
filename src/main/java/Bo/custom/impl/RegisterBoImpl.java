package Bo.custom.impl;

import Bo.custom.RegisterBo;
import Dao.DaoFactory;
import Dao.custom.RegisterDao;
import Dao.custom.impl.RegisterDaoImpl;
import Dao.util.DaoType;
import dto.RegisterDto;
import entity.Register;

import java.sql.SQLException;

public class RegisterBoImpl implements RegisterBo {

//    private RegisterDao registerDao = new RegisterDaoImpl();

    private RegisterDao registerDao = DaoFactory.getInstance().getDao(DaoType.REGISTER);
    @Override
    public boolean saveOrder(RegisterDto dto) throws SQLException, ClassNotFoundException {
        return registerDao.save(new Register(
                dto.getName(),
                dto.getEmail(),
                dto.getJobRole(),
                dto.getConNo()
        ));
    }
}
