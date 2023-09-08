package service;

import entities.Product;
import utils.Utils;

import java.util.Map;
import java.util.Scanner;

public class ProductService {
    public void createProduct(Scanner scanner, Map<String, Product> products) {
        while (true) {
            System.out.println("Enter product / service code:");
            String productCode = scanner.nextLine();
            if (Utils.checkValidStringIsNull(productCode)) continue;
            if (products.containsKey(productCode)) {
                System.out.println("Product with code = '" + productCode + "' already exist, please re-enter!");
            } else {
                while (true) {
                    System.out.println("Enter product / service name:");
                    String productName = scanner.nextLine();
                    if (Utils.checkValidStringIsNull(productName)) continue;
                    while (true) {
                        System.out.println("Enter unit price of product / service:");
                        try {
                            double unitPrice = Double.parseDouble(scanner.nextLine());
                            if (!Utils.checkValidPositiveNumber(unitPrice)) continue;
                            while (true) {
                                System.out.println("Enter VAT rate of this product / service: (1. 0% / 2. 5% / 3. 10%)");
                                try {
                                    int input = Integer.parseInt(scanner.nextLine());
                                    if (!Utils.checkValidPositiveNumber(input) || input > 3) continue;
                                    else {
                                        Product product = new Product(productCode, productName, unitPrice, input);
                                        products.put(productCode, product);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid value Integer, please try again!" + "\n");
                                }
                                break;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid value Integer, please try again!" + "\n");
                        }
                    }
                    break;
                }
            }
            break;
        }
    }
}

