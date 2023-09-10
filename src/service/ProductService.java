package service;

import entities.Product;
import entities.User;
import utils.Utils;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class ProductService {
    public void handleManageProduct(Scanner scanner, Menu menu, User user, Map<String, Product> products) {
        while (true) {
            menu.menuManageProduct();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    //Show product list information
                    case 1 -> {
                        System.out.println(products);
                        //Select product


                    }
                    //Create new product
                    case 2 -> {
                        createProduct(scanner, user, products);
                    }
                    //Back menu
                    case 3 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void createProduct(Scanner scanner, User user, Map<String, Product> products) {

        while (true) {
            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();
            if (Utils.checkValidStringIsNull(productCode)) continue;
            if (products.containsKey(productCode)) {
                System.out.println("Product with code = '" + productCode + "' already exist, please re-enter!");
            } else {
                while (true) {
                    System.out.println("Enter product name:");
                    String productName = scanner.nextLine();
                    if (Utils.checkValidStringIsNull(productName)) continue;
                    while (true) {
                        System.out.println("Enter unit price of product:");
                        try {
                            double unitPrice = Double.parseDouble(scanner.nextLine());
                            if (!Utils.checkValidPositiveNumber(unitPrice)) continue;
                            while (true) {
                                System.out.println("Enter VAT rate of this product: (1. 0% / 2. 5% / 3. 10%)");
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
    public void handleSelectProduct(Scanner scanner, Menu menu, Map<String, Product> products) {
        while (true) {
            menu.menuFunctionProduct();
            try {
                int chooseFunc = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(chooseFunc)) continue;
                switch (chooseFunc) {
                    case 1 -> {
                        handleProductAfterSelect(scanner, menu, selectProduct(scanner, products));
                    }
                    case 2 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }
    public Product selectProduct(Scanner scanner, Map<String, Product> products) {
        while (true) {
            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();
            if (products.containsKey(productCode)) {
                return products.get(productCode);
            }
        }
    }
    public void handleProductAfterSelect(Scanner scanner, Menu menu, Product product) {
        while (true) {
            menu.menuHandleProduct(); //Handle product
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    //Edit information of product
                    case 1 -> {
                        editProduct(scanner, menu, product);
                    }
                    //Delete product
                    case 2 -> {

                    }
                    //Back menu
                    case 3 -> {return;}
                }
            }
            catch (Exception e) {

            }
        }
    }
    public void editProduct(Scanner scanner, Menu menu, Product product) {
        while (true) {
            menu.menuEditProduct();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    case 1 -> {
                        //Edit product name
                        while (true) {
                            System.out.println("Enter new name of this product:");
                            String newProductName = scanner.nextLine();
                            if (Utils.checkValidStringIsNull(newProductName)) continue;
                            product.setProductName(newProductName);
                            break;
                        }
                    }
                    case 2 -> {
                        //Edit product unit price
                        while (true) {
                            System.out.println("Enter new unit price of this product:");
                            try {
                                double newUnitPrice = Double.parseDouble(scanner.nextLine());
                                if (!Utils.checkValidPositiveNumber(newUnitPrice)) continue;
                                product.setUnitPrice(newUnitPrice);
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid value Integer, please try again!" + "\n");
                            }
                        }
                    }
                    case 3 -> {
                        //Edit product VAT rate
                        while (true) {
                            System.out.println("Enter new VAT rate of this product: (1. 0% / 2. 5% / 3. 10%)");
                            try {
                                int chooseVAT = Integer.parseInt(scanner.nextLine());
                                if (!Utils.checkValidPositiveNumber(chooseVAT) || chooseVAT > 3) continue;
                                product.setVATRate(product.getVATRate(chooseVAT));  //Set VAT rate
                                product.setVATRateString(product.getVATRateString(product.getVATRate()));  //Set VAT rate String
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid value Integer, please try again!" + "\n");
                            }
                        }
                    }
                    case 4 -> {
                        return;
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }
    public void deleteProduct() {

    }
}

