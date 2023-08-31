package service;

import entities.Seller;
import entities.Account;
import entities.Staff;
import view.Menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaffService {
    public Staff login(Scanner scanner, Map<Integer, Seller> sellers, Map<String, Staff> staffs, SellerService sellerService) {
        do {
            System.out.println("========= LOGIN ==========");
            System.out.println("Enter your company's tax code:");
            int taxCode = Integer.parseInt(scanner.nextLine());
            if (sellers.isEmpty()) {
                System.out.println("There are currently no sellers, please register for the service first!");
                Seller seller = sellerService.registerService(scanner, staffs);
                sellers.put(taxCode, seller);
            }
            else if (!sellers.containsKey(taxCode)) {
                reEnterTaxCodeOrRegister(scanner, taxCode, staffs, sellers, sellerService);
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
                        enterPassword(scanner, staff, sellers);
                        return staff;
                    }
                }
                while (true);
            }
        }
        while (true);
    }
    public void reEnterTaxCodeOrRegister(Scanner scanner, int taxCode, Map<String, Staff> staffs, Map<Integer, Seller> sellers, SellerService sellerService) {
        System.out.println("Tax code doesn't exist, choose options:");
        System.out.println("1. Re-enter tax code.");
        System.out.println("2. Register service.");
        int choose = Integer.parseInt(scanner.nextLine());
        switch (choose) {
            case 1 -> {
                login(scanner, sellers, staffs, sellerService);
            }
            case 2 -> {
                Seller seller = sellerService.registerService(scanner, staffs);
                sellers.put(taxCode, seller);
            }
        }
    }
    public void enterPassword(Scanner scanner, Staff staff,  Map<Integer, Seller> sellers) {
        do {
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (!staff.getPassword().equals(password)) {
                System.out.println("Enter wrong password, choose the options:");
                System.out.println("1. Re-enter password.");
                System.out.println("2. Forgot password?");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        forgotPassword(scanner, sellers);
                    }
                }
            }
            else {
                System.out.println("Login successful!");
            }
            break;
        }
        while (true);
    }
    public void forgotPassword(Scanner scanner, Map<Integer, Seller> sellers) {
        do {
            System.out.println("Please re-enter your company's tax code:");
            int inputTaxCode = Integer.parseInt(scanner.nextLine());
            if (!sellers.containsKey(inputTaxCode)) {
                System.out.println("Tax code " + inputTaxCode + " doesn't exist, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("Please enter your username:");
                    String inputUsername = scanner.nextLine();
                    if (!sellers.get(inputTaxCode).getStaffs().containsKey(inputUsername)) {
                        System.out.println("Username " + inputUsername + " doesn't exist, please try again!");
                        continue;
                    }
                    else {
                        do {
                            System.out.println("Please enter your email:");
                            String inputEmail = scanner.nextLine();
                            if (!sellers.get(inputTaxCode).getStaffs().get(inputUsername).getEmail().equalsIgnoreCase(inputEmail)) {
                                System.out.println("Wrong email, please try again!");
                                continue;
                            }
                            else {
                                System.out.println("Enter your new password:");
                                String newPassword = scanner.nextLine();
                                sellers.get(inputTaxCode).getStaffs().get(inputUsername).setPassword(newPassword);
                                System.out.println("Created new password successful!");
                            }
                            break;
                        }
                        while (true);
                    }
                    break;
                }
                while (true);

            }

            break;
        }
        while (true);
    }
}
