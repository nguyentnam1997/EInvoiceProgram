package service;

import entities.*;
import utils.Utils;
import view.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InvoiceService extends IdentityInfoService {
    public void handleManageInvoice(Scanner scanner, Menu menu, User user, Seller seller, Map<String, InvoiceTemplate> invoiceTemplates) {
        while (true) {
            menu.menuManageInvoice();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    handleInvoiceTemplate(scanner, menu, user, invoiceTemplates);
                }
                case 2 -> {
                    //createInvoice
                }
                case 3 -> {

                }
                case 4 -> {
                    return;
                }
            }
        }
    }

    public void handleInvoiceTemplate(Scanner scanner, Menu menu, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        while (true) {
            menu.menuManageInvoiceTemplate();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    if (invoiceTemplates.isEmpty()) {
                        System.out.println("Invoice template list is empty, please create!");
                    } else System.out.println(invoiceTemplates);  //in danh sách, XEM LẠI
                }
                case 2 -> {
                    createInvoiceTemplate(scanner, user, invoiceTemplates);
                }
                case 3 -> {
                    changeStatusTemplate(scanner, user, invoiceTemplates);
                }
                case 4 -> {
                    return;
                }
            }
        }
    }

    public void createInvoiceTemplate(Scanner scanner, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        if (Utils.checkUserIsAdmin(user)) {
            System.out.println("----------- Create invoice template ------------");
            while (true) {
                System.out.println("\n" + "Enter invoice template series:");
                String templateSeries = scanner.nextLine();
                if (!Utils.isValidTemplateSeries(templateSeries)) {
                    System.out.println("Invalid template series, please try again!");
                    continue;
                }
                InvoiceTemplate invTemp = new InvoiceTemplate(templateSeries);
                invoiceTemplates.put(invTemp.getTemplateSerial(), invTemp);
                System.out.println("Create new invoice template successful!");
                break;
            }
        }
    }

    public void changeStatusTemplate(Scanner scanner, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        if (Utils.checkUserIsAdmin(user)) {
            while (true) {
                System.out.println("Enter invoice template serial want to change status: ");
                String templateSerial = scanner.nextLine();
                if (!invoiceTemplates.containsKey(templateSerial)) {
                    System.out.println("Invoice template with serial " + templateSerial + " doesn't exist.");
                    if (Utils.stayMenu(scanner)) continue;
                } else {
                    InvoiceTemplate invoiceTemplate = invoiceTemplates.get(templateSerial);
                    System.out.println("Invoice template " + templateSerial + " with status as '" +
                            invoiceTemplate.getActiveStatus() + "', do you want to change? (Y/N)");
                    String choose = scanner.nextLine();
                    if (choose.equalsIgnoreCase("Y")) {
                        invoiceTemplate.setActive(!invoiceTemplate.isActive());   //thay đổi ngược lại trạng thái hoạt động hiện tại
                        System.out.println("Change active status successful!");
                    }
                }
                break;
            }
        }
    }

    public void createInvoice(Scanner scanner, User user, Seller seller, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        while (true) {
            System.out.println("\n" + "Enter invoice template serial:");
            String invTempSerial = scanner.nextLine();
            if (!invoiceTemplates.containsKey(invTempSerial)) {
                System.out.println("Template with serial '" + invTempSerial + "' doesn't exist, try again!");
                continue;
            }
            LocalDateTime invoiceDate = LocalDateTime.now();
            System.out.println("\n" + "Enter description of invoice:");
            String description = scanner.nextLine();
            Customer customer = inputCustomerInInvoice(scanner, customers, identityInfoService, customerService);  //nhập thông tin customer
            int paymentMethod = 0;
            while (true) {
                System.out.println("\n" + "Enter payment method of invoice: (1. TM / 2. CK / 3. TM/CK) ");
                try {
                    paymentMethod = Integer.parseInt(scanner.nextLine());
                    if (paymentMethod <= 0 || paymentMethod > 3) {
                        System.out.println("Wrong value entered, please re-enter!");
                        continue;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid value Integer, please try again!");
                }
                break;
            }
        }
    }

    public Customer inputCustomerInInvoice(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        System.out.println("----------  Information of customer: ---------");
        while (true) {
            System.out.println("Do you want to enter existing customer information? (Y/N)");
            String chooseAutoEnter = scanner.nextLine();
            if (chooseAutoEnter.equalsIgnoreCase("Y")) {
                while (true) {
                    System.out.println("\n" + "Enter customer code:");
                    String customerCode = scanner.nextLine();
                    if (!customers.containsKey(customerCode)) {
                        System.out.println("Customer with id = '" + customerCode + "' doesn't exist, re-enter? (Y/N)");
                        String chooseReEnter = scanner.nextLine();
                        if (chooseReEnter.equalsIgnoreCase("Y")) continue;
                        else break;
                    } else {
                        return customers.get(customerCode);
                    }
                }
            }
            else {
                return customerService.inputCustomer(scanner, customers, identityInfoService);
            }
        }
    }

    public List<ProductInvoiceDetail> inputProductListToInvoice(Scanner scanner, Map<String, Product> products, IdentityInfoService identityInfoService) {
        System.out.println("\n" + "----------  Information of products / services: ---------");
        while (true) {
            try {
                System.out.println("Enter the number of products / services line:");
                int countProduct = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(countProduct)) continue;
                else {
                    for (int i = 0; i < countProduct; i++) {
                        System.out.println("\n" + (i + 1) + ". Product line " + (i + 1) + ":");

                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
            break;
        }
        return null;  //fix lại
    }

    public ProductInvoiceDetail inputProductInvoiceDetail(Scanner scanner, Map<String, Product> products, Invoice invoice, IdentityInfoService identityInfoService) {
        while (true) {
            System.out.println("Enter quantity of this product / service:");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(quantity)) continue;
                else {
                    while (true) {
                        try {
                            System.out.println("Enter discount rate of this product / service:");
                            double discountRate = Double.parseDouble(scanner.nextLine());
                            if (!Utils.checkValidPositiveNumber(discountRate)) continue;
                            else {
                                return new ProductInvoiceDetail(invoice, product, quantity, discountRate);
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid value Integer, please try again!" + "\n");
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
            break;
        }
    }
}
