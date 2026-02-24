package program;

import model.dao.DaoFactory;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        System.out.println();

        System.out.println("Update department with id: " + insertDepartment.getId());
        insertDepartment.setName("Cars");
        departmentDaoJDBC.update(insertDepartment);
        System.out.println("All departments with updated department:");
        allDepartments = departmentDaoJDBC.findAll();
        allDepartments.forEach(System.out::println);

        System.out.println();

        System.out.println("Update seller with id: " + insertSeller.getId());
        insertSeller.setName("John Smith");
        insertSeller.setEmail("johnsmith@email.com");
        sellerDaoJDBC.update(insertSeller);
        System.out.println("All sellers with updated seller:");
        allSellers = sellerDaoJDBC.findAll();
        allSellers.forEach(System.out::println);

        System.out.println();

        System.out.print("Type seller id to delete: ");
        int sellerId = sc.nextInt();
        sellerDaoJDBC.deleteById(sellerId);
        System.out.println("All sellers with deleted seller:");
        allSellers = sellerDaoJDBC.findAll();
        allSellers.forEach(System.out::println);

        System.out.println();

        System.out.print("Type departmend id to delete: ");
        int departmentId = sc.nextInt();
        departmentDaoJDBC.deleteById(departmentId);
        System.out.println("All departments with deleted seller:");
        allDepartments = departmentDaoJDBC.findAll();
        allDepartments.forEach(System.out::println);

        sc.close();
    }
}
