package com.revature;

public class UzerDaoFactory {

    private static UzerDao dao;

    private UzerDaoFactory(){

    }

public static UzerDao getUzerDao(){
        if(dao==null){
            dao = new UzerDaoImp();
        }
        return dao;
}

}
