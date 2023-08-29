package service;

import entities.Seller;
import entities.Account;
import entities.Staff;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaffService {
    public void enterPassword(Scanner scanner, Staff staff, StaffService staffService, Map<Integer, Seller> sellers) {
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
                        staffService.forgotPassword(scanner, sellers);
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
