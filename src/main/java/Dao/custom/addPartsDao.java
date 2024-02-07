package Dao.custom;

import Dao.CrudDao;
import dto.PartsDto;
import entity.Customer;
import entity.Parts;
import entity.addParts;

import java.sql.SQLException;

public interface addPartsDao extends CrudDao<addParts> {
    addParts get(Double total) throws SQLException, ClassNotFoundException;
}
