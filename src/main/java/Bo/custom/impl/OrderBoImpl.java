package Bo.custom.impl;

import Bo.custom.OrderBo;
import Dao.DaoFactory;
import Dao.custom.OrderDao;
import Dao.util.DaoType;
import dto.OrderDto;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {


    private OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
//        return orderDao.save(new Orders(
//                dto.getOrderId(),
//                dto.getDate(),
//                dto.getCustomerID(),
//                dto.getSubCategory(),
//                dto.getStatus()
//        ));
    }

    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.update(new Orders(
                dto.getOrderId(),
                dto.getDate(),
                dto.getSubCategory(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean deleteOrder(String code) throws SQLException, ClassNotFoundException {
        return orderDao.delete(code);
    }

    public List<OrderDto> allOrders() throws SQLException, ClassNotFoundException {
        List<Orders> orders = orderDao.getAll();
        List<OrderDto> list = new ArrayList<>();
        for (Orders order:orders) {
            list.add(new OrderDto(
                    order.getOrderId(),
                    order.getDate().toString(),
                    order.getCustomer().getCustomerID(),
                    order.getSubCategory(),
                    order.getStatus()
            ));
        }
        return list;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        try {
            OrderDto dto = orderDao.getLastOrder();
            if (dto!=null){
                String id = dto.getOrderId();
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
