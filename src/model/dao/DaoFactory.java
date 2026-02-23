package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDaoJDBC createSellerDao() {
        return new SellerDaoJDBC();
    }

    public static DepartmentDaoJDBC createDepartmentDao() {
        return new DepartmentDaoJDBC();
    }
}
