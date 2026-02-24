package program;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;
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

        Department insertDepartment = new Department(null, "Games");
        Seller insertSeller = new Seller(null, "Arthur", "arthur@email.com", LocalDateTime.now(),
                5000.0, insertDepartment);


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

        System.out.println();

        System.out.print("Insert department");
        departmentDaoJDBC.insert(insertDepartment);
        System.out.println(" with id: " + insertDepartment.getId());
        System.out.println("All departments with inserted department");
        allDepartments = departmentDaoJDBC.findAll();
        allDepartments.forEach(System.out::println);

        System.out.println();

        System.out.print("Insert seller");
        sellerDaoJDBC.insert(insertSeller);
        System.out.println(" with id: " + insertSeller.getId());
        System.out.println("All sellers with inserted seller:");
        allSellers = sellerDaoJDBC.findAll();
        allSellers.forEach(System.out::println);

    }
}
