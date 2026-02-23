package program;

import model.dao.DaoFactory;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Seller;

public class Main {
    public static void main(String[] args) {
        SellerDaoJDBC sellerDaoJDBC = DaoFactory.createSellerDao();
        Seller seller = sellerDaoJDBC.findById(5);
        System.out.println(seller);
    }
}
