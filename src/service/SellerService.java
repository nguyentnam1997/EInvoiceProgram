package service;

import entities.Invoice;
import entities.Seller;
import entities.Account;
import entities.Staff;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SellerService {

    public Seller registerService(Scanner scanner, Map<String, Staff> staffs) {
        System.out.println("====== REGISTER COMPANY ======");
        System.out.println("Enter your company's tax code:");
        int taxCode = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your company's name:");
        String name = scanner.nextLine();
        System.out.println("Enter your company's address:");
        String address = scanner.nextLine();
        System.out.println("Enter your company's email:");
        String email = scanner.nextLine();
        System.out.println("Enter your company's phone number:");
        int phoneNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your company's bank account:");
        String bankAccount = scanner.nextLine();
        System.out.println("Enter your company's quantity of invoice:");
        int invoiceQuantity = Integer.parseInt(scanner.nextLine());
        System.out.println("---- Enter admin account ----");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        Staff staff = new Staff(username, password, taxCode, true);
        staffs.put(username, staff);
        System.out.println("Register successful!");
        return new Seller(taxCode, name, address, email, phoneNumber, bankAccount, invoiceQuantity, staffs);
    }
}
