package service.identity;

import entities.*;
import service.invoice.InvoiceService;
import service.invoice.InvoiceTemplateService;
import service.product.ProductService;
import view.Show;
import utils.Utils;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class SellerService {
    public void loginService(Scanner scanner, Menu menu, Map<String, User> users, Map<String, Product> products, Map<String, Customer> customers, Map<Integer, Invoice> invoices,
                             Map<String, InvoiceTemplate> invoiceTemplates, UserService userService, InvoiceService invoiceService,
                             IdentityInfoService identityInfoService, CustomerService customerService, ProductService productService, InvoiceTemplateService invoiceTemplateService) {
        System.out.println("\n" + "========== WELCOME TO INVOICE PROGRAM ===========");
        Seller seller = registerService(scanner, users, identityInfoService);
        while (true) {
            User user = userService.login(scanner, seller, users);
            handleAfterLogin(scanner, menu, user, seller, users, invoiceTemplates, products, customers, invoices,
                    identityInfoService, customerService, userService, invoiceService, productService, invoiceTemplateService);
        }

        //if (utils.wantContinue(scanner)) continue;
        //break;


    }

    public Seller registerService(Scanner scanner, Map<String, User> users, IdentityInfoService identityInfoService) {
        System.out.println("Please register for the service first!");
        IdentityInfo identity = identityInfoService.inputIdentityAsOrganization(scanner);
        System.out.println("-------- Enter admin account --------");
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            if (!Utils.isValidUsername(username)) {
                System.out.println("Invalid username, please try again!");
                continue;
            }
            while (true) {
                System.out.print("\n" + "Enter password: ");
                String password = scanner.nextLine();
                if (!Utils.isValidPassword(password)) {
                    System.out.println("Invalid password, please try again!");
                    continue;
                }
                while (true) {
                    System.out.print("\n" + "Enter email: ");
                    String accountEmail = scanner.nextLine();
                    if (!Utils.isValidEmail(accountEmail)) {
                        System.out.println("Invalid email, please try again!");
                        continue;
                    }
                    User user = new User(username, password, accountEmail, true);
                    users.put(username, user);
                    System.out.println("Register successful!!!");
                    return new Seller(identity.getTaxCode(), identity.getName(), identity.getAddress(),
                            identity.getEmail(), identity.getHotline(), identity.getBankAccount(), users);
                }
            }
        }

    }

    public void handleAfterLogin(Scanner scanner, Menu menu, User user, Seller seller, Map<String, User> users, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Product> products,
                                 Map<String, Customer> customers, Map<Integer, Invoice> invoices, IdentityInfoService identityInfoService, CustomerService customerService,
                                 UserService userService, InvoiceService invoiceService, ProductService productService, InvoiceTemplateService invoiceTemplateService) {
        while (true) {
            menu.menuOptionsAfterLogin();
            try {
                int chooseAfterLogin = Integer.parseInt(scanner.nextLine());
                switch (chooseAfterLogin) {
                    //Company management
                    case 1 -> handleManageCompany(scanner, menu, user, seller);
                    //User management
                    case 2 -> userService.handleManageUser(scanner, menu, user, users);
                    //Invoices management
                    case 3 ->
                            invoiceService.handleManageInvoice(scanner, menu, user, seller, invoiceTemplates, products, customers,
                                    invoices, identityInfoService, customerService, invoiceTemplateService);
                    //Products management
                    case 4 -> productService.handleManageProduct(scanner, menu, user, products);
                    //Customers management
                    case 5 -> customerService.handleManageCustomer(scanner, menu, user, customers, identityInfoService);
                    case 6 -> {
                        if (!Utils.stayMenu(scanner)) return;//chức năng Logout;
                    }
                    default -> {
                        System.out.println("Invalid input, please re-enter");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public void handleManageCompany(Scanner scanner, Menu menu, User user, Seller seller) {
        while (true) {
            menu.menuManageCompany();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> Show.showInfoSellerCompany(seller);   //view information
                    case 2 -> handleEditCompanyInformation(scanner, menu, user, seller);
                    case 3 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid input, please re-enter");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
            /*if (Utils.stayMenu(scanner)) continue;
            break;*/
        }
    }

    public void handleEditCompanyInformation(Scanner scanner, Menu menu, User user, Seller seller) {
        if (Utils.checkUserIsAdmin(user)) {
            while (true) {
                menu.menuEditCompanyInformation();
                try {
                    int chooseEdit = Integer.parseInt(scanner.nextLine());
                    switch (chooseEdit) {
                        case 1 -> editCompanyName(scanner, seller);
                        case 2 -> editCompanyAddress(scanner, seller);
                        case 3 -> editCompanyEmail(scanner, seller);
                        case 4 -> editCompanyHotline(scanner, seller);
                        case 5 -> editCompanyBankAccount(scanner, seller);
                        case 6 -> {
                            //handleEditCompanyInformation(scanner, menu, seller);
                            return;
                        }
                        default -> {
                            System.out.println("Invalid input, please re-enter");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Invalid value Integer, please try again!" + "\n");
                }
                //if (utils.wantContinue(scanner)) continue;
                //break;
            }
        }
    }

    public void editCompanyName(Scanner scanner, Seller seller) {
        System.out.println("Old company's name was " + "'" + seller.getName() + "'");
        while (true) {
            System.out.println("Enter the new company's name:");
            String newCompanyName = scanner.nextLine();
            if (seller.getName().equalsIgnoreCase(newCompanyName)) {
                System.out.println("New company's name can't be the same as old name, try again!");
                continue;
            } else {
                System.out.println("Company's name was successfully changed!");
                seller.setName(newCompanyName);
            }
            break;
        }
    }

    public void editCompanyAddress(Scanner scanner, Seller seller) {
        System.out.println("Old company's address was " + "'" + seller.getAddress() + "'");
        while (true) {
            System.out.println("Enter the new company's address:");
            String newCompanyAddress = scanner.nextLine();
            if (seller.getAddress().equalsIgnoreCase(newCompanyAddress)) {
                System.out.println("New company's address can't be the same as old address, try again!");
                continue;
            } else {
                System.out.println("Company's address was successfully changed!");
                seller.setAddress(newCompanyAddress);
            }
            break;
        }
    }

    public void editCompanyEmail(Scanner scanner, Seller seller) {
        System.out.println("Old company's email was " + "'" + seller.getEmail() + "'");
        while (true) {
            System.out.println("Enter the new company's email:");
            String newCompanyEmail = scanner.nextLine();
            if (seller.getEmail().equalsIgnoreCase(newCompanyEmail)) {
                System.out.println("New company's email can't be the same as old email, try again!");
                continue;
            } else {
                System.out.println("Company's email was successfully changed!");
                seller.setEmail(newCompanyEmail);
            }
            break;
        }
    }

    public void editCompanyHotline(Scanner scanner, Seller seller) {
        System.out.println("Old company's hotline was " + "'" + seller.getHotline() + "'");
        while (true) {
            System.out.println("Enter the new company's hotline:");
            try {
                int newCompanyHotline = Integer.parseInt(scanner.nextLine());
                if (seller.getHotline() == newCompanyHotline) {
                    System.out.println("New company's hotline can't be the same as old hotline, try again!");
                    continue;
                } else {
                    System.out.println("Company's hotline was successfully changed!");
                    seller.setHotline(newCompanyHotline);
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void editCompanyBankAccount(Scanner scanner, Seller seller) {
        System.out.println("Old company's bank account was " + "'" + seller.getBankAccount() + "'");
        System.out.println("Enter the new company's bank account:");
        String newCompanyBankAccount = scanner.nextLine();
        seller.setBankAccount(newCompanyBankAccount);
    }
}
