import entities.*;
import service.identity.CustomerService;
import service.identity.IdentityInfoService;
import service.identity.SellerService;
import service.identity.UserService;
import service.invoice.InvoiceService;
import service.invoice.InvoiceTemplateService;
import service.product.ProductService;
import view.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Map<Integer, Seller> sellers = new HashMap<>();
        Map<String, User> users = new HashMap<>();
        Map<String, InvoiceTemplate> invoiceTemplates = new HashMap<>();
        Map<String, Product > products = new HashMap<>();
        Map<Integer, ProductInvoiceDetail> productInvoiceDetails = new HashMap<>();
        Map<String, Customer > customers = new HashMap<>();
        Map<Integer, Invoice> invoices = new HashMap<>();
        IdentityInfoService identityInfoService = new IdentityInfoService();
        UserService userService = new UserService();
        SellerService sellerService = new SellerService();
        InvoiceService invoiceService = new InvoiceService();
        CustomerService customerService = new CustomerService();
        ProductService productService = new ProductService();
        InvoiceTemplateService invoiceTemplateService = new InvoiceTemplateService();
        sellerService.loginService(scanner,  menu,  users, products, customers, invoices, invoiceTemplates,
                userService, invoiceService, identityInfoService, customerService, productService, invoiceTemplateService);
    }
}