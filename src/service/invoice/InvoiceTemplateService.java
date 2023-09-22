package service.invoice;

import entities.InvoiceTemplate;
import entities.User;
import utils.Utils;
import view.Menu;
import view.Show;

import java.util.Map;
import java.util.Scanner;

public class InvoiceTemplateService {
    public void handleInvoiceTemplate(Scanner scanner, Menu menu, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        while (true) {
            menu.menuManageInvoiceTemplate();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        if (!Utils.checkInvTemplatesIsEmpty(invoiceTemplates)) {
                            Show.showInfoInvoiceTemplates(invoiceTemplates);
                        }  //in danh sách, XEM LẠI
                    }
                    case 2 -> {
                        createInvoiceTemplate(scanner, user, invoiceTemplates);
                    }
                    case 3 -> {
                        if (!Utils.checkInvTemplatesIsEmpty(invoiceTemplates)) {
                            changeStatusTemplate(scanner, user, invoiceTemplates);
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
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public void createInvoiceTemplate(Scanner scanner, User user, Map<String, InvoiceTemplate> invoiceTemplates) {
        if (Utils.checkUserIsAdmin(user)) {
            System.out.println("----------- Create invoice template ------------");
            while (true) {
                System.out.print("Enter invoice template series: 1C23T");
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
            if (!Utils.checkInvTemplatesIsEmpty(invoiceTemplates)) {
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
    }
}
