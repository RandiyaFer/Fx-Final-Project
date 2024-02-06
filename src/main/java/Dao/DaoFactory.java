package Dao;

import Dao.custom.CustomerDao;
import Dao.custom.impl.*;
import Dao.util.DaoType;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory!=null? daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case REGISTER: return(T) new RegisterDaoImpl();
            case CREATE: return(T) new CreateUserDaoImpl();
            case ITEM: return(T) new ItemDaoImpl();
            case CUSTOMER: return(T) new CustomerDaoImpl();
            case ORDER: return(T) new OrderDaoImpl();
            case PLACEORD:return(T) new placeOrdDaoImpl();
            case PARTS:return(T) new PartsDaoImpl();
        }
        return null;
    }
}
