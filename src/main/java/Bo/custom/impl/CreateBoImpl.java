package Bo.custom.impl;

import Bo.custom.CreateBo;
import Dao.DaoFactory;
import Dao.custom.CreateDao;
import Dao.util.DaoType;
import dto.CreateDto;
import entity.Create;

import java.sql.SQLException;

public class CreateBoImpl implements CreateBo {


    private CreateDao createDao = DaoFactory.getInstance().getDao(DaoType.CREATE);
    @Override
    public boolean saveOrder(CreateDto dto) throws SQLException, ClassNotFoundException {
        return createDao.save(new Create(
                dto.getEmail(),
                dto.getPassword(),
                dto.getConfirm()
        ));
    }
}
