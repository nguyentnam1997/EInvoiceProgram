package service.product;

import entities.Customer;
import entities.Invoice;
import entities.Product;
import entities.ProductInvoiceDetail;
import utils.Utils;

import java.util.Map;
import java.util.Scanner;

public class ProductInvoiceDetailService {
    public ProductInvoiceDetail inputProductInvoiceDetail(Scanner scanner, Product product) {
        //Invoice invoice = new Invoice();
        while (true) {
            System.out.print("Enter quantity of this product / service: ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(quantity)) continue;
                while (true) {
                    try {
                        System.out.print("\n" + "Enter discount rate of this product / service: ");
                        double discountRate = Double.parseDouble(scanner.nextLine());
                        if (discountRate < 0 || discountRate > 100) {
                            System.out.println("Number entered is outside the valid range, please re-enter!");
                        }
                        else {
                            return new ProductInvoiceDetail(product, quantity, discountRate);
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }
    public void createProductInvoiceDetail(Scanner scanner, Product product, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        ProductInvoiceDetail productInvoiceDetail = inputProductInvoiceDetail(scanner, product);
        productInvoiceDetails.put(productInvoiceDetail.getProductInvoiceId(), productInvoiceDetail);
    }

}
