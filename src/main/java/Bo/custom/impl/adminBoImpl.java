package Bo.custom.impl;

import Bo.custom.RegisterBo;
import Bo.custom.adminBo;
import Dao.DaoFactory;
import Dao.custom.RegisterDao;
import Dao.custom.adminDao;
import Dao.custom.impl.adminDaoImpl;
import Dao.util.DaoType;
import dto.AdminDto;
import dto.CustomerDto;
import dto.RegisterDto;
import entity.Admin;
import entity.Customer;
import entity.Register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class adminBoImpl implements adminBo {

    private adminDao AdminDao = DaoFactory.getInstance().getDao(DaoType.ADMIN);

    @Override
    public boolean saveOrder(AdminDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public List<AdminDto> allAdmin() throws SQLException, ClassNotFoundException {
        List<Admin> entityList = AdminDao.getAll();
        List<AdminDto> list = new ArrayList<>();
        for (Admin admin:entityList) {
            list.add(new AdminDto(
                    admin.getEmail(),
                    admin.getPassword()
            ));
        }
        return list;
    }

    @Override
    public boolean updateAdmin(AdminDto dto) throws SQLException, ClassNotFoundException {
        return AdminDao.update(new Admin(
                dto.getEmail(),
                dto.getPassword()
        ));
    }
}
