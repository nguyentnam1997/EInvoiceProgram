import entities.InvoiceTemplate;
import entities.Seller;
import entities.User;
import service.InvoiceService;
import service.SellerService;
import service.UserService;
import utils.Utils;
import view.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Seller> sellers = new HashMap<>();
        Map<String, User> users = new HashMap<>();
        Map<String, InvoiceTemplate> invoiceTemplates = new HashMap<>();
        Menu menu = new Menu();
        //Utils utils = new Utils();
        UserService userService = new UserService();
        SellerService sellerService = new SellerService();
        InvoiceService invoiceService = new InvoiceService();
        sellerService.loginService(scanner, menu, users,invoiceTemplates, userService, sellerService, invoiceService);
    }
}