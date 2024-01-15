package Bo.custom.impl;

import Bo.custom.ItemBo;
import Dao.DaoFactory;
import Dao.custom.ItemDao;
import Dao.util.DaoType;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {


    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.save(new Item(
                dto.getItemCode(),
                dto.getCategory(),
                dto.getSubCategory(),
                dto.getDescription()
        ));
    }

    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDao.update(new Item(
                dto.getItemCode(),
                dto.getCategory(),
                dto.getSubCategory(),
                dto.getDescription()
        ));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

    public List<ItemDto> allItems() throws SQLException, ClassNotFoundException {
        List<Item> entityList = itemDao.getAll();
        List<ItemDto> list = new ArrayList<>();
        for (Item item:entityList) {
            list.add(new ItemDto(
                    item.getItemCode(),
                    item.getCategory(),
                    item.getSubCategory(),
                    item.getDescription()
            ));
        }
        return list;
    }
}
