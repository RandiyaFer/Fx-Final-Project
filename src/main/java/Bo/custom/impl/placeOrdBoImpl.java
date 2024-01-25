package Bo.custom.impl;

import Bo.custom.ItemBo;
import Bo.custom.placeOrdBo;
import Dao.DaoFactory;
import Dao.custom.ItemDao;
import Dao.custom.placeOrdDao;
import Dao.util.DaoType;
import dto.ItemDto;
import dto.placeOrdDto;
import entity.Item;
import entity.Orders;
import entity.placeOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class placeOrdBoImpl implements placeOrdBo {


    private placeOrdDao PlaceOrdDao = DaoFactory.getInstance().getDao(DaoType.PLACEORD);

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<placeOrdDto> allOrders() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrder(placeOrdDto dto) throws SQLException, ClassNotFoundException {
        return PlaceOrdDao.save(new placeOrder(
                dto.getOrderId(),
                dto.getCustomerID(),
                dto.getItemCode(),
                dto.getUserId(),
                dto.getAdvance()
        ));
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    //@Override
//    public boolean saveOrder(placeOrdDto dto) throws SQLException, ClassNotFoundException {
//        return PlaceOrdDao.save(new placeOrder(
//                dto.getOrderId(),
//                dto.getCustomerID(),
//                dto.getItemCode(),
//                dto.getUserId(),
//                dto.getAdvance()
//        ));
//    }

//    @Override
//    public String generateId() throws SQLException, ClassNotFoundException {
//        try {
//            placeOrdDto dto = placeOrdDao.getLastOrder();
//            if (dto!=null){
//                String id = dto.getOrderId();
//                int num = Integer.parseInt(id.split("[D]")[1]);
//                num++;
//                return String.format("D%03d",num);
//            }else{
//                return "D001";
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public boolean updateOrder(placeOrdDto dto) throws SQLException, ClassNotFoundException {
//        return PlaceOrdDao.update(new placeOrder(
//                dto.getOrderId(),
//                dto.getCustomerID(),
//                dto.getItemCode(),
//                dto.getUserId(),
//                dto.getAdvance()
//        ));
//    }

//    @Override
//    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
//        return PlaceOrdDao.delete(id);
//    }

//    public List<placeOrdDto> allOrders() throws SQLException, ClassNotFoundException {
//        List<placeOrder> entityList = PlaceOrdDao.getAll();
//        List<placeOrdDto> list = new ArrayList<>();
//        for (placeOrder PlaceOrder:entityList) {
//            list.add(new placeOrdDto(
//                    PlaceOrder.getOrderId(),
//                    PlaceOrder.getCustomerID(),
//                    PlaceOrder.getItemCode(),
//                    PlaceOrder.getUserId(),
//                    PlaceOrder.getAdvance()
//            ));
//        }
//        return list;
//    }
}
