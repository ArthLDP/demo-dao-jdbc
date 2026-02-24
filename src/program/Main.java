package program;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerDaoJDBC sellerDaoJDBC = DaoFactory.createSellerDao();
        DepartmentDaoJDBC departmentDaoJDBC = DaoFactory.createDepartmentDao();

        Seller sellerFoundById = sellerDaoJDBC.findById(5);
        Department departmentFoundById = departmentDaoJDBC.findById(2);

        List<Seller> sellersFoundByDepartment = sellerDaoJDBC.findByDepartment(departmentFoundById);
        List<Seller> allSellers = sellerDaoJDBC.findAll();
        List<Department> allDepartments = departmentDaoJDBC.findAll();

        System.out.println("Seller found:");
        System.out.println(sellerFoundById + "\n");
        System.out.println("Department found:");
        System.out.println(departmentFoundById + "\n");

        if (!sellersFoundByDepartment.isEmpty()) {
            System.out.println("Sellers from " + sellersFoundByDepartment.getFirst().getDepartment().getName() +
                    " department:");
            sellersFoundByDepartment.forEach(System.out::println);
            System.out.println();
        }

        System.out.println("All sellers:");
        allSellers.forEach(System.out::println);
        System.out.println();

        System.out.println("All departments:");
        allDepartments.forEach(System.out::println);
    }
}
