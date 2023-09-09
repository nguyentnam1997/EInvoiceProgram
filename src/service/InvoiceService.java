package service;

import entities.*;
import utils.Utils;
import view.Menu;

import java.time.LocalDateTime;
import java.util.HashMap;
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
                    //show list invoice
                }
                case 3 -> {
                    //createInvoice
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

    public Invoice createInvoice(Scanner scanner, User user, Seller seller, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        Map<Integer,ProductInvoiceDetail> productInvoiceDetails = new HashMap<>();
        while (true) {
            System.out.println("\n" + "Enter invoice template serial:");
            String invTempSerial = scanner.nextLine();
            if (!invoiceTemplates.containsKey(invTempSerial)) {
                System.out.println("Template with serial '" + invTempSerial + "' doesn't exist, try again!");
                continue;
            }
            LocalDateTime invoiceDate = LocalDateTime.now();
            Customer customer = inputCustomerIntoInvoice(scanner, customers, identityInfoService, customerService);  //nhập thông tin customer
            handleProductInvoiceDetail(scanner, invoice, products, productInvoiceDetails, productInvoiceDetailService);
        }
    }

    public Customer inputCustomerIntoInvoice(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
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
                        if (chooseReEnter.equalsIgnoreCase("N")) break;
                    } else {
                        return customers.get(customerCode);
                    }
                }
            } else {
                return customerService.inputCustomer(scanner, customers, identityInfoService);
            }
        }
    }

    public void handleProductInvoiceDetail(Scanner scanner,  Invoice invoice, Map<Integer, Product> products, Map<Integer,ProductInvoiceDetail> productInvoiceDetails, ProductInvoiceDetailService productInvoiceDetailService) {
        System.out.println("\n" + "----------  Information of products / services: ---------");
        while (true) {
            try {
                System.out.println("Enter the number of products / services lines:");
                int countProduct = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(countProduct)) continue;
                else {
                    for (int i = 0; i < countProduct; i++) {
                        System.out.println("----------- List of products -----------");
                        System.out.println(products);       //hiển thị danh sách product
                        System.out.println("\n" + (i + 1) + ". Product line " + (i + 1) + ":");
                        while (true) {
                            System.out.println("Please select product by productID:");
                            int selectProductId = Integer.parseInt(scanner.nextLine());
                            if (!products.containsKey(selectProductId)) {
                                System.out.println("Product with id = '" + selectProductId + "' doesn't exist, please try again!");
                            } else {
                                Product product = products.get(selectProductId);
                                productInvoiceDetailService.createProductInvoiceDetail(scanner, product, invoice, productInvoiceDetails);
                                System.out.println("Line " + i + " successful!");
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
            break;
        }
    }


}
