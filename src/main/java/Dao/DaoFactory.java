package Dao;

import Dao.custom.impl.CreateDaoImpl;
import Dao.custom.impl.RegisterDaoImpl;
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
            case CREATE: return(T) new CreateDaoImpl();

        }
        return null;
    }
}
