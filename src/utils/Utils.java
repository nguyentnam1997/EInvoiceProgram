package utils;

import entities.Seller;
import entities.Staff;

import java.util.Map;
import java.util.Scanner;

public class Utils {
    public boolean wantContinue(Scanner scanner) {
        System.out.println("Do you want to continue? (Y/N)");
        String chooseContinue = scanner.nextLine();
        if (chooseContinue.equalsIgnoreCase("Y")) return true;
        else return false;
    }
    public Seller getSellerFromStaff(Staff staff, Map<Integer, Seller> sellers) {
        return sellers.get(staff.getSellerTaxCode());
    }
}
