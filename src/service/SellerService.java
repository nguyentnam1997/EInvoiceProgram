package service;

import entities.Invoice;
import entities.Seller;
import entities.Account;
import entities.Staff;
import jdk.jshell.execution.Util;
import utils.Utils;
import view.Menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SellerService {
    public void loginOrRegisterService(Scanner scanner, Menu menu, Utils utils, Map<Integer, Seller> sellers, Map<String, Staff> staffs, StaffService staffService, SellerService sellerService) {
        do {
            menu.menuLoginRegister();
            int chooseLogin = Integer.parseInt(scanner.nextLine());
            switch (chooseLogin) {
                case 1 -> {
                    Staff staff = staffService.login(scanner, sellers, staffs, sellerService);
                    Seller seller = utils.getSellerFromStaff(staff, sellers);
                    handleAfterLogin(scanner, menu, utils, staff, seller, sellers);

                }
                case 2 -> registerService(scanner, staffs);
            }
            if (utils.wantContinue(scanner)) continue;
            break;
        }
        while (true);


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
        System.out.println("Enter your company's hotline:");
        int hotline = Integer.parseInt(scanner.nextLine());
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
        return new Seller(taxCode, companyName, companyAddress, companyEmail, hotline, bankAccount, invoiceQuantity, staffs);
    }
     public void handleAfterLogin(Scanner scanner, Menu menu, Utils utils, Staff staff, Seller seller, Map<Integer, Seller> sellers) {
         do {
             menu.menuOptionsAfterLogin();
             int chooseAfterLogin = Integer.parseInt(scanner.nextLine());
             switch (chooseAfterLogin) {
                 case 1:
                     handleCompanyInformation(scanner, menu, staff, seller, utils, sellers);
                 case 2:
                 case 3:
                 case 4:
                 case 5:
                 case 6:

             }
             break;
         }
         while (true);
     }
    public void handleCompanyInformation(Scanner scanner, Menu menu, Staff staff, Seller seller, Utils utils, Map<Integer, Seller> sellers) {
        do {
            menu.menuCompanyInformation();
            int chooseCase1 = Integer.parseInt(scanner.nextLine());
            switch (chooseCase1) {
                case 1 -> {
                    System.out.println(sellers.get(staff.getSellerTaxCode()));   //view information
                }
                case 2 -> {
                    handleEditCompanyInformation(scanner, menu, utils, seller);
                }
                case 3 -> {
                    break;
                }
            }
            if (utils.wantContinue(scanner)) continue;
            break;
        }
        while (true);

    }
    public void handleEditCompanyInformation(Scanner scanner, Menu menu, Utils utils, Seller seller) {
       do {
           menu.menuEditInformation();
           int chooseEdit = Integer.parseInt(scanner.nextLine());
           switch (chooseEdit) {
               case 1 -> editCompanyName(scanner, seller);
               case 2 -> editCompanyAddress(scanner, seller);
               case 3 -> editCompanyEmail(scanner, seller);
               case 4 -> editCompanyHotline(scanner, seller);
               case 5 -> editCompanyBankAccount(scanner, seller);
           }
           if (utils.wantContinue(scanner)) continue;
           break;
       }
       while (true);

    }
    public void editCompanyName(Scanner scanner, Seller seller) {
        System.out.println("Old company's name was " + "'" + seller.getName() + "'");
        System.out.println("Enter the new company's name:");
        String newCompanyName = scanner.nextLine();
        seller.setName(newCompanyName);
    }
    public void editCompanyAddress(Scanner scanner, Seller seller) {
        System.out.println("Old company's address was " + "'" + seller.getAddress() + "'");
        System.out.println("Enter the new company's address:");
        String newCompanyAddress = scanner.nextLine();
        seller.setAddress(newCompanyAddress);
    }
    public void editCompanyEmail(Scanner scanner, Seller seller) {
        System.out.println("Old company's email was " + "'" + seller.getEmail() + "'");
        System.out.println("Enter the new company's email:");
        String newCompanyEmail = scanner.nextLine();
        seller.setEmail(newCompanyEmail);
    }
    public void editCompanyHotline(Scanner scanner, Seller seller) {
        System.out.println("Old company's hotline was " + "'" + seller.getHotline() + "'");
        System.out.println("Enter the new company's hotline:");
        int newCompanyHotline = Integer.parseInt(scanner.nextLine());
        seller.setHotline(newCompanyHotline);
    }
    public void editCompanyBankAccount(Scanner scanner, Seller seller) {
        System.out.println("Old company's bank account was " + "'" + seller.getBankAccount() + "'");
        System.out.println("Enter the new company's bank account:");
        String newCompanyBankAccount = scanner.nextLine();
        seller.setBankAccount(newCompanyBankAccount);
    }
}
