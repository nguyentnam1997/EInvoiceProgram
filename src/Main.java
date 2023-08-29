import entities.Seller;
import entities.Staff;
import service.SellerService;
import service.StaffService;
import view.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Seller> sellers = new HashMap<>();
        Map<String, Staff> staffs = new HashMap<>();
        Menu menu = new Menu();
        StaffService staffService = new StaffService();
        SellerService sellerService = new SellerService();
        sellerService.loginOrRegisterService(scanner, menu, sellers, staffs, staffService);
    }
}