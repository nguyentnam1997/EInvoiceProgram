package service;

import entities.*;
import utils.Utils;
import view.Menu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InvoiceService extends IdentityInfoService {
    public void handleManageInvoice(Scanner scanner, Menu menu, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
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
        if (!Utils.checkUserIsAdmin(user)) {
            System.out.println("This user don't have permission to perform this function!");
        } else {
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
        if (!Utils.checkUserIsAdmin(user)) {
            System.out.println("This user don't have permission to perform this function!");
        } else {
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

    public void createInvoice(Scanner scanner, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        while (true) {
            System.out.println("Enter invoice template serial:");
            String invTempSerial = scanner.nextLine();
            if (!invoiceTemplates.containsKey(invTempSerial)) {
                System.out.println("Template with serial '" + invTempSerial + "' doesn't exist, try again!");
                continue;
            }
            LocalDateTime invoiceDate = LocalDateTime.now();
            System.out.println("Enter description of invoice:");
            String description = scanner.nextLine();


        }

    }

    public Customer inputCustomerInInvoice(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        System.out.println("----------  information of customer: ---------");
        while (true) {
            System.out.println("Do you want to enter existing customer information? (Y/N)");
            String chooseAutoEnter = scanner.nextLine();
            if (chooseAutoEnter.equalsIgnoreCase("Y")) {
                while (true) {
                    System.out.println("\n" + "Enter customer ID:");
                    String customerId = scanner.nextLine();
                    if (customerId == null) {
                        System.out.println("Value can't null, please try again!");
                    }
                    else if (!customers.containsKey(customerId)) {
                        System.out.println("Customer with id = '" + customerId + "' doesn't exist, re-enter? (Y/N)");
                        String chooseReEnter = scanner.nextLine();
                        if (chooseReEnter.equalsIgnoreCase("Y")) continue;
                        else break;
                    }
                    else {
                        return customers.get(customerId);
                    }
                }
            }
            else {
                System.out.println("This customer is organization or personal? (1. Organization / 2. Personal)");
                boolean isOrganization;
                int choose = Integer.parseInt(scanner.nextLine());
                if (choose == 1) {
                    isOrganization = true;
                    System.out.println("Enter buyer name:");
                    String buyerName = scanner.nextLine();
                    return new Customer(identityInfoService.inputIdentityAsOrganization(scanner), null, true, buyerName);
                }
                else if (choose == 2) {
                    isOrganization = false;
                    while (true) {
                        System.out.println("Enter buyer name:");
                        String buyerName = scanner.nextLine();
                        if (buyerName.trim().isEmpty()) {
                            System.out.println("Buyer name can't be null, please re-enter!");
                            continue;
                        }
                        return new Customer(identityInfoService.inputIdentityAsPersonal(scanner), null, false, buyerName);
                    }

                } else {
                    System.out.println("Invalid input value, try again!");
                    continue;
                }
            }
        }
    }
}
