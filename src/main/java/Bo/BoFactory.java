package Bo;

import Bo.custom.impl.CreateUserBoImpl;
import Bo.custom.impl.ItemBoImpl;
import Bo.custom.impl.RegisterBoImpl;

import Dao.util.BoType;

import static Dao.util.DaoType.ITEM;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory!=null? boFactory:(boFactory=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case REGISTER: return (T) new RegisterBoImpl();
            case CREATE: return (T) new CreateUserBoImpl();
            case ITEM: return (T) new ItemBoImpl();
//            case ORDER: return (T) new OrderBoImpl();
//            case ORDER_DETAIL: return (T) new OrderDetailBoImpl();

        }
        return null;
    }
}
