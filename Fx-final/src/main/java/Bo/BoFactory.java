package Bo;

import Bo.custom.impl.RegisterBoImpl;

import Dao.util.BoType;

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
//            case CUSTOMER: return (T) new CustomerBoImpl();
//            case ITEM: return (T) new ItemBoImpl();
//            case ORDER: return (T) new OrderBoImpl();
//            case ORDER_DETAIL: return (T) new OrderDetailBoImpl();

        }
        return null;
    }
}
