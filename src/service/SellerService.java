package service;

import entities.Seller;
import entities.User;
import utils.Utils;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class SellerService {
    public void loginService(Scanner scanner, Menu menu, Utils utils, Map<String, User> users, UserService userService, SellerService sellerService) {
        System.out.println("======= WELCOME TO INVOICE PROGRAM =======");
        Seller seller = registerService(scanner, users);
        User user = userService.login(scanner, seller, users, sellerService);
        handleAfterLogin(scanner, menu, utils, user, seller, users, userService);
        //if (utils.wantContinue(scanner)) continue;
        //break;


    }
    public Seller registerService(Scanner scanner, Map<String, User> users) {
        System.out.println("There are currently no seller, please register for the service first!");
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
        System.out.println("---- Enter admin account ----");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String accountEmail = scanner.nextLine();
        User user = new User(username, password, accountEmail, true, taxCode);
        users.put(username, user);
        System.out.println("Register successful!");
        return new Seller(taxCode, companyName, companyAddress, companyEmail, hotline, bankAccount, users);
    }
     public void handleAfterLogin(Scanner scanner, Menu menu, Utils utils, User user, Seller seller, Map<String, User> users, UserService userService) {
         do {
             menu.menuOptionsAfterLogin();
             int chooseAfterLogin = Integer.parseInt(scanner.nextLine());
             switch (chooseAfterLogin) {
                 case 1:
                     handleManageCompany(scanner, menu, seller, utils);
                 case 2:
                     userService.handleManageUser(scanner, menu, utils, user, seller, users);
                 case 3:
                 case 4:
                 case 5:

                 case 6:

             }
             break;
         }
         while (true);
     }
    public void handleManageCompany(Scanner scanner, Menu menu, Seller seller, Utils utils) {
        do {
            menu.menuManageCompany();
            int chooseCase1 = Integer.parseInt(scanner.nextLine());
            switch (chooseCase1) {
                case 1 -> {
                    System.out.println(seller);   //view information
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
           menu.menuEditCompanyInformation();
           int chooseEdit = Integer.parseInt(scanner.nextLine());
           switch (chooseEdit) {
               case 1 -> editCompanyName(scanner, seller);
               case 2 -> editCompanyAddress(scanner, seller);
               case 3 -> editCompanyEmail(scanner, seller);
               case 4 -> editCompanyHotline(scanner, seller);
               case 5 -> editCompanyBankAccount(scanner, seller);
               case 6 -> handleEditCompanyInformation(scanner, menu, utils, seller);
           }
           //if (utils.wantContinue(scanner)) continue;
           break;
       }
       while (true);
    }
    public void editCompanyName(Scanner scanner, Seller seller) {
        System.out.println("Old company's name was " + "'" + seller.getName() + "'");
        do {
            System.out.println("Enter the new company's name:");
            String newCompanyName = scanner.nextLine();
            if (seller.getName().equalsIgnoreCase(newCompanyName)) {
                System.out.println("New company's name can't be the same as old name, try again!");
                continue;
            }
            else {
                System.out.println("Company's name was successfully changed!");
                seller.setName(newCompanyName);
            }
            break;
        }
        while (true);

    }
    public void editCompanyAddress(Scanner scanner, Seller seller) {
        System.out.println("Old company's address was " + "'" + seller.getAddress() + "'");
        do {
            System.out.println("Enter the new company's address:");
            String newCompanyAddress = scanner.nextLine();
            if (seller.getAddress().equalsIgnoreCase(newCompanyAddress)) {
                System.out.println("New company's address can't be the same as old address, try again!");
                continue;
            }
            else {
                System.out.println("Company's address was successfully changed!");
                seller.setAddress(newCompanyAddress);
            }
            break;
        }
        while (true);
    }
    public void editCompanyEmail(Scanner scanner, Seller seller) {
        System.out.println("Old company's email was " + "'" + seller.getEmail() + "'");
        do {
            System.out.println("Enter the new company's email:");
            String newCompanyEmail = scanner.nextLine();
            if (seller.getEmail().equalsIgnoreCase(newCompanyEmail)) {
                System.out.println("New company's email can't be the same as old email, try again!");
                continue;
            }
            else {
                System.out.println("Company's email was successfully changed!");
                seller.setEmail(newCompanyEmail);
            }
            break;
        }
        while (true);
    }
    public void editCompanyHotline(Scanner scanner, Seller seller) {
        System.out.println("Old company's hotline was " + "'" + seller.getHotline() + "'");
        do {
            System.out.println("Enter the new company's hotline:");
            int newCompanyHotline = Integer.parseInt(scanner.nextLine());
            if (seller.getHotline() == newCompanyHotline) {
                System.out.println("New company's hotline can't be the same as old hotline, try again!");
                continue;
            }
            else {
                System.out.println("Company's hotline was successfully changed!");
                seller.setHotline(newCompanyHotline);
            }
            break;
        }
        while (true);
    }
    public void editCompanyBankAccount(Scanner scanner, Seller seller) {
        System.out.println("Old company's bank account was " + "'" + seller.getBankAccount() + "'");
        System.out.println("Enter the new company's bank account:");
        String newCompanyBankAccount = scanner.nextLine();
        seller.setBankAccount(newCompanyBankAccount);
    }
}
