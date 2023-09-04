package service;

import entities.IdentityInfo;
import utils.Utils;

import java.util.Scanner;

public class IdentityInfoService {
    public IdentityInfo inputIdentityInfo(Scanner scanner) {
        while (true) {
            System.out.println("\n" + "Enter your company's tax code:");
            try {
                int taxCode = Integer.parseInt(scanner.nextLine());
                if (!Utils.isValidTaxCode(taxCode)) {
                    System.out.println("Tax code length is required 10 characters, please try again!");
                    continue;
                }
                System.out.println("\n" + "Enter your company's name:");
                String companyName = scanner.nextLine();
                System.out.println("\n" + "Enter your company's address:");
                String companyAddress = scanner.nextLine();
                while (true) {
                    System.out.println("\n" + "Enter your company's email:");
                    String companyEmail = scanner.nextLine();
                    if (!Utils.isValidEmail(companyEmail)) {
                        System.out.println("Invalid email, please try again!");
                        continue;
                    }
                    while (true) {
                        System.out.println("\n" + "Enter your company's hotline:");
                        try {
                            int hotline = Integer.parseInt(scanner.nextLine());
                            System.out.println("\n" + "Enter your company's bank account:");
                            String bankAccount = scanner.nextLine();
                            return new IdentityInfo(taxCode, companyName, companyAddress, companyEmail, hotline, bankAccount);
                        } catch (Exception e) {
                            System.out.println("Invalid value Integer, please try again!");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }

    }
}
