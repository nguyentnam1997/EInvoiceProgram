package service;

import entities.Invoice;
import entities.Seller;
import entities.Account;
import entities.Staff;
import view.Menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SellerService {
    public void loginOrRegisterService(Scanner scanner, Menu menu, Map<Integer, Seller> sellers, Map<String, Staff> staffs, StaffService staffService) {
        menu.menuLoginRegister();
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1:
                login(scanner, sellers, staffs, staffService);
            case 2:
                registerService(scanner, staffs);
        }
    }
    public Seller registerService(Scanner scanner, Map<String, Staff> staffs) {
        System.out.println("====== REGISTER COMPANY ======");
        System.out.println("Enter your company's tax code:");
        int taxCode = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your company's name:");
        String companyName = scanner.nextLine();
        System.out.println("Enter your company's address:");
        String companyAddress = scanner.nextLine();
        System.out.println("Enter your company's email:");
        String companyEmail = scanner.nextLine();
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
        System.out.println("Enter email:");
        String accountEmail = scanner.nextLine();
        Staff staff = new Staff(username, password, accountEmail, taxCode, true);
        staffs.put(username, staff);
        System.out.println("Register successful!");
        return new Seller(taxCode, companyName, companyAddress, companyEmail, phoneNumber, bankAccount, invoiceQuantity, staffs);
    }
    public void login(Scanner scanner, Map<Integer, Seller> sellers, Map<String, Staff> staffs, StaffService staffService) {
        System.out.println("========= LOGIN ==========");
        System.out.println("Enter your company's tax code:");
        int taxCode = Integer.parseInt(scanner.nextLine());
        if (sellers.isEmpty()) {
            System.out.println("There are currently no sellers, please register for the service first!");
            Seller seller = registerService(scanner, staffs);
            sellers.put(taxCode, seller);
        }
        else if (!sellers.containsKey(taxCode)) {
            reEnterTaxCodeOrRegister(scanner, taxCode, staffs, sellers, staffService);
        }
        else {
            Seller seller = sellers.get(taxCode);
            do {
                System.out.println("Enter your username:");
                String username = scanner.nextLine();
                if (!seller.getStaffs().containsKey(username)) {
                    System.out.println("Staff does not exist with tax code " + taxCode + ", please try again!");
                    continue;
                }
                else {
                    Staff staff = staffs.get(username);
                    staffService.enterPassword(scanner, staff, staffService, sellers);
                }
                break;
            }
            while (true);
        }
    }
    public void reEnterTaxCodeOrRegister(Scanner scanner, int taxCode, Map<String, Staff> staffs, Map<Integer, Seller> sellers, StaffService staffService) {
        System.out.println("Tax code doesn't exist, choose options:");
        System.out.println("1. Re-enter tax code.");
        System.out.println("2. Register service.");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1 -> {
                login(scanner, sellers, staffs, staffService);
            }
            case 2 -> {
                Seller seller = registerService(scanner, staffs);
                sellers.put(taxCode, seller);
            }
        }
    }

}
