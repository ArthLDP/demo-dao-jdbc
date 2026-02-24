package program;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDaoJDBC sellerDaoJDBC = DaoFactory.createSellerDao();
        DepartmentDaoJDBC departmentDaoJDBC = DaoFactory.createDepartmentDao();

        Seller seller = sellerDaoJDBC.findById(5);
        Department department = departmentDaoJDBC.findById(2);

        List<Seller> sellers = sellerDaoJDBC.findByDepartment(department);

        System.out.println("Seller found:");
        System.out.println(seller + "\n");
        System.out.println("Department found:");
        System.out.println(department + "\n");

        if (!sellers.isEmpty()) {
            System.out.println("Sellers from " + sellers.getFirst().getDepartment().getName() + " department:");
            sellers.forEach(System.out::println);
        }
    }
}
