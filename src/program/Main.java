package program;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Main {
    public static void main(String[] args) {
        SellerDaoJDBC sellerDaoJDBC = DaoFactory.createSellerDao();
        DepartmentDaoJDBC departmentDaoJDBC = DaoFactory.createDepartmentDao();

        Seller seller = sellerDaoJDBC.findById(5);
        Department department = departmentDaoJDBC.findById(2);

        System.out.println("Seller found:");
        System.out.println(seller);
        System.out.println("Department found:");
        System.out.println(department);
    }
}
