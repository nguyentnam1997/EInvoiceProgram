package service;

import entities.Seller;
import entities.Account;
import entities.Staff;

import java.util.Map;
import java.util.Scanner;

public class StaffService {
    public void login(Scanner scanner, SellerService sellerService, Map<Integer, Seller> sellers, Map<String, Staff> staffs) {
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
                System.out.println("Tax code does not exist, choose options:");
                System.out.println("1. Re-enter tax code.");
                System.out.println("2. Register service.");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        Seller seller = sellerService.registerService(scanner, staffs);
                        sellers.put(taxCode, seller);
                    }
                }
            }
            else {
                int count = 0;
                do {
                    System.out.println("Enter your username:");
                    String username = scanner.nextLine();
                    if (!staffs.containsKey(username) || staffs.get(username).getSellerTaxCode() != taxCode) {
                        System.out.println("Staff does not exist with tax code " + taxCode + ", please try again!");
                        count++;
                        continue;
                    }
                    if (count == 2) {
                        System.out.println("1. Re-enter tax code.");
                        System.out.println("2. Register new company.");
                    }
                    if (staffs.containsKey(username) && staffs.get(username).getSellerTaxCode() != taxCode) {
                        System.out.println("1. Re-enter tax code.");
                        System.out.println("2. Register new company.");
                        int choose = Integer.parseInt(scanner.nextLine());
                        switch (choose) {
                            case 1 -> {
                                continue;
                            }
                            //case 2 -> sellerService.registerService(scanner);
                        }
                    }
                }
                while (true);  //staffs.get(username).getSellerTaxCode() == taxCode

                //break;
            }
        }
        while (true) ;
    }
}
