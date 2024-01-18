package Bo.custom;

import Bo.SuperBo;
import dto.CustomerDto;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {

    boolean saveCustomer(CustomerDto dto)throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto)throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String Id) throws SQLException, ClassNotFoundException;
    List<CustomerDto> allCustomers()throws SQLException, ClassNotFoundException;


}
