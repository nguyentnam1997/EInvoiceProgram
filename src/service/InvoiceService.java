package service;

import entities.InvoiceTemplate;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class InvoiceService {
    public void handleManageInvoice(Scanner scanner, Menu menu) {
        while (true) {
            menu.menuManageInvoice();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {

                }
                case 2 -> {
                }
            }
        }
    }

    public void handleInvoiceTemplate(Scanner scanner, Menu menu, List<InvoiceTemplate> invoiceTemplates) {
        while (true) {
            menu.menuManageInvoiceTemplate();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    if (invoiceTemplates.isEmpty()) {
                        System.out.println("Invoice template list is empty, please create!");
                    }
                    else System.out.println(invoiceTemplates);  //in danh sách, XEM LẠI
                }
                case 2 -> {

                }
            }
        }
    }
    public void createInvoice(Scanner scanner) {

    }
}
