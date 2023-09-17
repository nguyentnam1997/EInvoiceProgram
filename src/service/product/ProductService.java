package service.product;

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
                        if (products.isEmpty()) {
                            System.out.println("List of products is empty, please create first!");
                        }
                        else {
                            System.out.println(products);
                            //Select product
                            handleSelectProduct(scanner, menu, user, products);
                        }
                    }
                    //Create new product
                    case 2 -> {
                        createProduct(scanner, user, products);
                    }
                    //Back menu
                    case 3 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void createProduct(Scanner scanner, User user, Map<String, Product> products) {
        System.out.println("-------- 4.2. Create product --------");
        while (true) {
            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();
            if (Utils.checkValidStringIsNull(productCode)) continue;
            else if (products.containsKey(productCode)) {
                System.out.println("Product with code '" + productCode + "' already exist, please re-enter!");
                continue;
            }
            else {
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
                                    if (!Utils.checkValidPositiveNumber(input) || input > 3) {
                                        System.out.println("Invalid value, please re-enter!");
                                    }
                                    else {
                                        Product product = new Product(productCode, productName, unitPrice, input);
                                        products.put(productCode, product);
                                        System.out.println("Create product '" + productName + "' successfully!");
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid value Integer, please try again!" + "\n");
                                }
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

    public void handleSelectProduct(Scanner scanner, Menu menu, User user, Map<String, Product> products) {
        while (true) {
            menu.menuFunctionProduct();
            try {
                int chooseFunc = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(chooseFunc)) continue;
                switch (chooseFunc) {
                    case 1 -> {
                        if (Utils.checkUserIsAdmin(user)) {
                            Product product = selectProduct(scanner, products);
                            if (product != null) handleProductAfterSelect(scanner, menu, product, products);
                        }
                    }
                    case 2 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
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
            else {
                System.out.println("Product with code '" + productCode + "' doesn't exist");
                if (Utils.stayMenu(scanner)) continue;
                else return null;
            }
        }
    }

    public void handleProductAfterSelect(Scanner scanner, Menu menu, Product product, Map<String, Product> products) {
        while (true) {
            menu.menuHandleProduct(); //Handle product
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    //Edit information of product
                    case 1 -> {
                        editProduct(scanner, menu, product);
                        return;
                    }
                    //Delete product
                    case 2 -> {
                        deleteProduct(scanner, product, products);
                        return;
                    }
                    //Back menu
                    case 3 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
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
                            System.out.println("Change product's name successfully!");
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
                                System.out.println("Change product's unit price successfully!");
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
                                if (!Utils.checkValidPositiveNumber(chooseVAT) || chooseVAT > 3) {
                                    System.out.println("Invalid value, please re-enter!");
                                    continue;
                                }
                                product.setVATRate(product.getVATRate(chooseVAT));  //Set VAT rate
                                product.setVATRateString(product.getVATRateString(product.getVATRate()));  //Set VAT rate String
                                System.out.println("Change product's VAT rate successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid value Integer, please try again!" + "\n");
                            }
                        }
                    }
                    case 4 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }

            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }
    public void deleteProduct(Scanner scanner, Product product, Map<String, Product> products) {
        System.out.println("--------- Delete product ----------");
        System.out.println("Do you want to delete this product? (Y/N)");
        String choose = scanner.nextLine();
        if (choose.equalsIgnoreCase("Y")) {
            products.remove(product.getProductCode());
            System.out.println("Delete product with code '" + product.getProductCode() + "' successfully!");
        }
    }
}

