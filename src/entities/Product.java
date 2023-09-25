package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Data
public class Product {
//    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
//    private static int autoId;
//
//    @Setter(AccessLevel.NONE)
//    private int productId;
    @Setter(AccessLevel.NONE)
    private String productCode;

    private String productName;
    private String description;
    private double unitPrice;
    private double VATRate;
    private String VATRateString;

    public Product(String productCode, String productName, double unitPrice, int input) {
        //this.productId = ++autoId;
        this.productCode = productCode;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.VATRate = getVATRate(input);
        this.VATRateString = getVATRateString(getVATRate());
    }

    public double getVATRate(int input) {
        switch (input) {
            case 1 -> {
                return 0;
            }
            case 2 -> {
                return 0.05;
            }
            case 3 -> {
                return 0.1;
            }
            default -> {
                System.out.println("Invalid input, please re-enter!");
                return 1;
            }
        }
    }
    public String getVATRateString(double VATRate) {
        return VATRate * 100 + "%";
    }
    public double getVATPrice(double unitPrice, double VATRate) {
        return unitPrice * VATRate;
    }
}
