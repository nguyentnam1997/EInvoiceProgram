package service;

import entities.InvoiceTemplate;
import entities.User;
import utils.Utils;
import view.Menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InvoiceService {
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

    public void createInvoiceTemplate(Scanner scanner,User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        if (!Utils.checkUserIsAdmin(user)) {
            System.out.println("This user don't have permission to perform this function!");
        }
        else {
            System.out.println("----------- Create invoice template ------------");
            while (true) {
                System.out.println("Enter invoice template type: (1. VAT / 2. Sales)");
                try {
                    int templateType = Integer.parseInt(scanner.nextLine());
                    if (templateType > 2 || templateType <= 0) {
                        System.out.println("Invalid type, please try again!" + "\n");
                        continue;
                    }
                    while (true) {
                        System.out.println("\n" + "Enter invoice template series:");
                        String templateSeries = scanner.nextLine();
                        if (!Utils.isValidTemplateSeries(templateSeries)) {
                            System.out.println("Invalid template series, please try again!");
                            continue;
                        }
                        InvoiceTemplate invTemp = new InvoiceTemplate(templateSeries, templateType);
                        invoiceTemplates.put(invTemp.getTemplateSerial(), invTemp);
                        System.out.println("Create new invoice template successful!");
                        break;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid value Integer, please try again!");
                }
            }
        }
    }
    public void changeStatusTemplate(Scanner scanner, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        if (!Utils.checkUserIsAdmin(user)) {
            System.out.println("This user don't have permission to perform this function!");
        }
        else {
            while (true) {
                System.out.println("Enter invoice template serial want to change status: ");
                String templateSerial = scanner.nextLine();
                if (!invoiceTemplates.containsKey(templateSerial)) {
                    System.out.println("Invoice template with serial " + templateSerial + " doesn't exist.");
                    if (Utils.stayMenu(scanner)) continue;
                }
                else {
                    InvoiceTemplate invoiceTemplate = invoiceTemplates.get(templateSerial);
                    System.out.println("Invoice template "+ templateSerial + " with status as '" +
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
    public void createInvoice(Scanner scanner, Map<String, InvoiceTemplate> invoiceTemplates) {
        while (true) {
            System.out.println("Enter invoice template serial:");
            String invTempSerial = scanner.nextLine();
            if (!invoiceTemplates.containsKey(invTempSerial)) {
                System.out.println("Template with serial '" + invTempSerial + "' doesn't exist, try again!");
                continue;
            }
            System.out.println("--------- Enter information of customer ---------");
        }

    }
}
