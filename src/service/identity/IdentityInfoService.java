package service.identity;

import entities.IdentityInfo;
import utils.Utils;

import java.util.Scanner;

public class IdentityInfoService {
    public IdentityInfo inputIdentityAsOrganization(Scanner scanner) {
        while (true) {
            System.out.print("\n" + "Enter the company's tax code: ");
            try {
                int taxCode = Integer.parseInt(scanner.nextLine());
                if (!Utils.isValidTaxCode(taxCode)) {
                    System.out.println("Tax code length is required 10 characters, please re-enter!");
                    continue;
                }
                while (true) {
                    System.out.println("\n" + "Enter the company's name:");
                    String companyName = scanner.nextLine();
                    if (Utils.checkValidStringIsNull(companyName)) continue;
                    while (true) {
                        System.out.println("\n" + "Enter the company's address:");
                        String companyAddress = scanner.nextLine();
                        if (Utils.checkValidStringIsNull(companyAddress)) continue;
                        while (true) {
                            System.out.println("\n" + "Enter the company's email:");
                            String companyEmail = scanner.nextLine();
                            if (!Utils.isValidEmail(companyEmail)) {
                                System.out.println("Invalid email, please re-enter!");
                                continue;
                            }
                            while (true) {
                                System.out.println("\n" + "Enter the company's hotline:");
                                try {
                                    int hotline = Integer.parseInt(scanner.nextLine());
                                    if (String.valueOf(hotline).length() != 10) {
                                        System.out.println("Hotline must have 10 numbers, please re-enter!");
                                        continue;
                                    }
                                    System.out.println("\n" + "Enter your company's bank account:");
                                    String bankAccount = scanner.nextLine();
                                    return new IdentityInfo(taxCode, companyName, companyAddress, companyEmail, hotline, bankAccount);
                                } catch (Exception e) {
                                    System.out.println("Invalid value Integer, please try again!");
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public IdentityInfo inputIdentityAsPersonal(Scanner scanner) {
        while (true) {
            System.out.println("\n" + "Enter the personal's address:");
            String personalAddress = scanner.nextLine();
            if (Utils.checkValidStringIsNull(personalAddress)) continue;
            while (true) {
                System.out.println("\n" + "Enter the personal's email:");
                String personalEmail = scanner.nextLine();
                if (!Utils.isValidEmail(personalEmail)) {
                    System.out.println("Invalid email, please re-enter!");
                    continue;
                }
                while (true) {
                    System.out.println("\n" + "Enter the personal's phone number:");
                    try {
                        int phoneNumber = Integer.parseInt(scanner.nextLine());
                        if (String.valueOf(phoneNumber).length() != 10) {
                            System.out.println("Phone number must have 10 numbers, please re-enter!");
                            continue;
                        }
                        System.out.println("\n" + "Enter the personal's bank account:");
                        String bankAccount = scanner.nextLine();
                        return new IdentityInfo(personalAddress, personalEmail, phoneNumber, bankAccount);
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!");
                    }
                }
            }
        }
    }
}
