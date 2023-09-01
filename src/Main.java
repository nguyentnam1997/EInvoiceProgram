import entities.Seller;
import entities.User;
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
        Menu menu = new Menu();
        Utils utils = new Utils();
        UserService userService = new UserService();
        SellerService sellerService = new SellerService();
        sellerService.loginService(scanner, menu, utils, users, userService, sellerService);
    }
}