package program;

import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Department department = new Department(1, "Books");
        Seller seller = new Seller(1, "Bob", "bob@gmail.com", LocalDateTime.now(), 5000.5, department);

        System.out.println(seller);
    }
}
