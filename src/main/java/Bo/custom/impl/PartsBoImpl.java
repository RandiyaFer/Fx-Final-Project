package Bo.custom.impl;

import Bo.custom.OrderBo;
import Bo.custom.PartsBo;
import Dao.DaoFactory;
import Dao.custom.CustomerDao;
import Dao.custom.OrderDao;
import Dao.custom.PartsDao;
import Dao.util.DaoType;
import dto.OrderDto;
import dto.PartsDto;
import entity.Orders;
import entity.Parts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartsBoImpl implements PartsBo {


    private PartsDao partsDao = DaoFactory.getInstance().getDao(DaoType.PARTS);
    private CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveOrder(PartsDto dto) throws SQLException, ClassNotFoundException {

        System.out.println(dto);
        return partsDao.save(new Parts(
                dto.getId(),
                dto.getDate(),
                customerDao.get(dto.getCustomerId()),
                dto.getSubCategory(),
                dto.getStatus(),
                dto.getPart(),
                dto.getTotal()
        ));
    }

    public boolean updateOrder(PartsDto dto) throws SQLException, ClassNotFoundException {
        return partsDao.update(new Parts(
                dto.getId(),
                dto.getDate(),
                customerDao.get(dto.getCustomerId()),
                dto.getSubCategory(),
                dto.getStatus(),
                dto.getPart(),
                dto.getTotal()
        ));
    }

    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return partsDao.delete(code);
    }

    public List<PartsDto> allOrders() throws SQLException, ClassNotFoundException {
        List<Parts> parts = partsDao.getAll();

        List<PartsDto> list = new ArrayList<>();
        for (Parts part:parts) {
            list.add(new PartsDto(
                    part.getId(),
                    part.getDate(),
                    part.getCustomer().getCustomerID(),
                    part.getSubCategory(),
                    part.getStatus(),
                    part.getPart(),
                    part.getTotal()
            ));
        }
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            PartsDto dto = partsDao.getLastOrder();
            if (dto!=null){
                String id = String.valueOf(dto.getId());
                int num = Integer.parseInt(id.split("[D]")[1]);
                num++;
                return String.format("D%03d",num);
            }else{
                return "D001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
