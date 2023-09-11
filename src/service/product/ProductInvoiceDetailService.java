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
            System.out.println("Enter quantity of this product / service:");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(quantity)) continue;
                while (true) {
                    try {
                        System.out.println("Enter discount rate of this product / service:");
                        double discountRate = Double.parseDouble(scanner.nextLine());
                        if (!Utils.checkValidPositiveNumber(discountRate)) continue;
                        else {
                            return new ProductInvoiceDetail(product, quantity, discountRate);
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!" + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }
    public void createProductInvoiceDetail(Scanner scanner, Product product, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        ProductInvoiceDetail productInvoiceDetail = inputProductInvoiceDetail(scanner, product);
        productInvoiceDetails.put(productInvoiceDetail.getProductInvoiceId(), productInvoiceDetail);
    }

}
