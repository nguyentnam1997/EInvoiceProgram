package view;

import entities.*;
import utils.TableList;

import java.util.List;
import java.util.Map;

public class Show {
    public static void showInfoSellerCompany(Seller seller) {
        TableList tableList = new TableList(6, "Tax code", "Seller name", "Address", "Email", "Hotline", "Bank account").sortBy(0).withUnicode(true);
        tableList.addRow(String.valueOf(seller.getTaxCode()), seller.getName(), seller.getAddress(), seller.getEmail(),  String.valueOf(seller.getHotline()), seller.getBankAccount());
        tableList.print();
    }
    public static void showInfoUser(User user) {
        TableList tableList = new TableList(2, "User name", "Email").sortBy(0).withUnicode(true);
        tableList.addRow(user.getUsername(), user.getEmail());
        tableList.print();
    }
    public static void showInfoProducts(Map<String, Product> products) {
        TableList tableList = new TableList(4, "Product code", "Product name", "Unit price", "VAT rate").sortBy(0).withUnicode(true);
        products.forEach((key, value) -> tableList.addRow(value.getProductCode(), value.getProductName(), String.valueOf(value.getUnitPrice()), value.getVATRateString()));
        tableList.print();
    }
    public static void showInfoCustomers(Map<String, Customer> customers) {
        TableList tableList = new TableList(9, "Customer code", "Tax code", "Customer name", "Buyer name", "Address",
                "Email", "Hotline/Phone number", "Bank account", "Is organization?").sortBy(0).withUnicode(true);
        customers.forEach((key, value) -> tableList.addRow(value.getCustomerCode(), String.valueOf(value.getTaxCode()), value.getName(), value.getBuyerName(), value.getAddress(),
                value.getEmail(), String.valueOf(value.getHotline()), value.getBankAccount(), String.valueOf(value.isOrganization())));
        tableList.print();
    }
    public static void showInfoInvoiceTemplates(Map<String, InvoiceTemplate> invoiceTemplates) {
        TableList tableList = new TableList(4, "Template serial", "Template name", "Is active?").sortBy(0).withUnicode(true);
        invoiceTemplates.forEach((key, value) -> tableList.addRow(value.getTemplateSerial(), value.getTemplateName(), String.valueOf(value.isActive())));
        tableList.print();
    }
    public static void showInfoInvoices(Map<Integer, Invoice> invoices) {
        TableList tableList = new TableList(11, "Inv ID", "Inv No.", "Inv date", "Inv Template", "Customer name", "Buyer name", "Total VAT price", "Total price",
                "Is published?", "Is deleted?", "User").sortBy(0).withUnicode(true);
        invoices.forEach((key, value) -> tableList.addRow(String.valueOf(value.getInvoiceId()), String.valueOf(value.getInvoiceNo()), String.valueOf(value.getInvoiceDate()),
                value.getInvoiceTemplate().getTemplateSerial(), value.getCustomer().getName(), value.getCustomer().getBuyerName(), String.valueOf(value.getTotalVATPrice()),
                String.valueOf(value.getTotalPrice()), String.valueOf(value.isInvoicePublished()), String.valueOf(value.isInvoiceDeleted()), value.getUser().getUsername()));
        tableList.print();
    }
    public static void showInfoProductInvoiceDetails(Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        TableList tableList = new TableList(9, "Product invoice ID", "Product code", "Product name", "Quantity", "Unit price", "Discount rate (%)", "VAT rate",
                "VAT price", "Total price").sortBy(0).withUnicode(true);
        productInvoiceDetails.forEach((key, value) -> tableList.addRow(String.valueOf(value.getProductInvoiceId()), value.getProduct().getProductCode(),
                value.getProduct().getProductName(), String.valueOf(value.getQuantity()), String.valueOf(value.getProduct().getUnitPrice()), String.valueOf(value.getDiscountRate()),
                value.getProduct().getVATRateString(), String.valueOf(value.getVATPrice()), String.valueOf(value.getTotalPrice())));
        tableList.print();
    }
}
