package Bo.custom.impl;

import Bo.custom.OrderBo;
import Bo.custom.addPartsBo;
import Dao.DaoFactory;
import Dao.custom.CreateUserDao;
import Dao.custom.CustomerDao;
import Dao.custom.OrderDao;
import Dao.custom.addPartsDao;
import Dao.util.DaoType;
import dto.OrderDto;
import dto.addPartsDto;
import entity.Orders;
import entity.addParts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class addPartsBoImpl implements addPartsBo {

    private addPartsDao AddPartsDao = DaoFactory.getInstance().getDao(DaoType.ADDS);
//    @Override
//    public boolean saveOrder(Dto dto) throws SQLException, ClassNotFoundException {
//
//        System.out.println(dto);
//        return orderDao.save(new Orders(
//                dto.getId(),
//                dto.getDate(),
//                customerDao.get(dto.getCustomerId()),
//                dto.getSubCategory(),
//                dto.getStatus()
//        ));
//    }

//    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
//        return orderDao.update(new Orders(
//                dto.getId(),
//                dto.getDate(),
//                customerDao.get(dto.getCustomerId()),
//                dto.getSubCategory(),
//                dto.getStatus()
//        ));
//    }

//    @Override
//    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
//        return orderDao.delete(code);
//    }

    public List<addPartsDto> allOrders() throws SQLException, ClassNotFoundException {
        List<addParts> prts = AddPartsDao.getAll();

        List<addPartsDto> list = new ArrayList<>();
        for (addParts parts:prts) {
            list.add(new addPartsDto(
                    parts.getName(),
                    parts.getPrice()
            ));
        }
        return list;
    }
}
