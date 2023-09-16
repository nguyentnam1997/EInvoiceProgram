package service.identity;

import entities.Seller;
import entities.User;
import utils.Utils;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class UserService {
    public User login(Scanner scanner, Seller seller, Map<String, User> users, SellerService sellerService) {
        System.out.println("\n"+"========= LOGIN ==========");
        do {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            if (!seller.getUsers().containsKey(username))
            {
                System.out.println("User does not exist, please try again!" + "\n");
            }
            else if (!seller.getUsers().get(username).isActive()) {
                System.out.println("This user has been inactivated, please try again!" + "\n");
            }
            else {
                User user = users.get(username);
                enterPassword(scanner, user, seller);
                return user;
            }
        }
        while (true);
    }
    public void enterPassword(Scanner scanner, User user, Seller seller) {
        while (true){
            System.out.print("\n" + "Enter your password: ");
            String password = scanner.nextLine();
            if (!user.getPassword().equals(password)) {
                System.out.println("\n" + "Enter wrong password, choose the options:");
                System.out.println("1. Re-enter password.");
                System.out.println("2. Forgot password?");
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        continue;
                    }
                    case 2 -> {
                        forgotPassword(scanner, seller);
                    }
                    default -> {
                        System.out.println("Invalid input, please re-enter!");
                        continue;
                    }
                }
            }
            else {
                System.out.println("Login successful!");
            }
            break;
        }
    }
    public void forgotPassword(Scanner scanner, Seller seller) {
        do {
            System.out.println("\n" + "Enter your username:");
            String inputUsername = scanner.nextLine();
            if (!seller.getUsers().containsKey(inputUsername)) {
                System.out.println("Username " + inputUsername + " doesn't exist, please try again!");
                continue;
            }
            else {
                do {
                    System.out.println("\n" + "Enter your email:");
                    String inputEmail = scanner.nextLine();
                    if (!seller.getUsers().get(inputUsername).getEmail().equalsIgnoreCase(inputEmail)) {
                        System.out.println("Wrong email, please try again!");
                        continue;
                    }
                    else {
                        do {
                            System.out.println("\n" + "Enter your new password:");
                            String newPassword = scanner.nextLine();
                            if (seller.getUsers().get(inputUsername).getPassword().equalsIgnoreCase(newPassword)) {
                                System.out.println("New password can't be the same as old password, try again!");
                                continue;
                            }
                            seller.getUsers().get(inputUsername).setPassword(newPassword);
                            System.out.println("Created new password successful!");
                            break;
                        }
                        while (true);
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }
    public void handleManageUser(Scanner scanner, Menu menu, User user, Map<String, User> users) {
        do {
            menu.menuManageUser();
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    System.out.println(user);   //view information
                }
                case 2 -> {
                    editUserInformation(scanner, menu, user);
                }
                case 3 -> {
                    createUser(scanner, user, users);
                }
                case 4 -> {
                    changeStatusUser(scanner, user, users);
                }
                case 5 -> {
                    return;
                }
            }
            /*if (Utils.wantContinue(scanner)) continue;
            break;*/
        }
        while (true);
    }
    public void editUserInformation(Scanner scanner, Menu menu, User user) {
        do {
            menu.menuEditUserInformation();
            int chooseEdit = Integer.parseInt(scanner.nextLine());
            switch (chooseEdit) {
                case 1 -> changePassword(scanner, user);
                case 2 -> changeEmail(scanner, user);
                case 3 -> {
                    return;
                }
            }
            if (Utils.stayMenu(scanner)) continue;
            break;
        }
        while (true);
    }
    public void changePassword(Scanner scanner, User user) {
        while (true) {
            if (!checkCurrentUserPassword(scanner, user)) continue;
            else {
                while (true) {
                    if (!checkCurrentUserEmail(scanner, user)) continue;
                    else {
                        System.out.println("\n" + "Enter your new password");
                        String newPass = scanner.nextLine();
                        if (user.getPassword().equalsIgnoreCase(newPass)) {
                            System.out.println("New password can't be the same as old password, try again!");
                            continue;
                        }
                        else {
                            System.out.println("Your password was successfully changed!");
                            user.setPassword(newPass);
                        }
                    }
                    break;
                }
            }
            break;
        }
    }
    public void changeEmail(Scanner scanner, User user) {
        do {
            if (!checkCurrentUserEmail(scanner, user)) continue;
            else {
                do {
                    System.out.println("Enter your new email");
                    String newEmail = scanner.nextLine();
                    if (user.getEmail().equalsIgnoreCase(newEmail)) {
                        System.out.println("New email can't be the same as old email, try again!");
                        continue;
                    }
                    else {
                        System.out.println("Your email was successfully changed!");
                        user.setEmail(newEmail);
                    }
                    break;
                }
                while (true);
            }
            break;
        }
        while (true);
    }

    public void createUser(Scanner scanner, User user, Map<String, User> users) {
        if (Utils.checkUserIsAdmin(user)) {
            System.out.println("------- Create new User -------");
            while (true) {
                System.out.println("Enter your username:");
                String username = scanner.nextLine();
                if (!checkExistsUsername(username, users)) continue;
                else {
                    while (true) {
                        System.out.println("Enter your email:");
                        String email = scanner.nextLine();
                        if (checkExistsEmail(email, users)) continue;
                        else {
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            System.out.println("Permissions for this User? 1. Admin / 2. Member");
                            boolean isAdmin;
                            int choose = Integer.parseInt(scanner.nextLine());
                            if (choose == 1) isAdmin = true;
                            else isAdmin = false;
                            User newUser = new User(username, password, email, isAdmin);
                            users.put(username, newUser);
                            System.out.println("Create user " + username + " successful!");
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    public void changeStatusUser(Scanner scanner, User user, Map<String, User> users) {
        if (Utils.checkUserIsAdmin(user)) {
            while (true) {
                System.out.println("Enter username want to change status: ");
                String username = scanner.nextLine();
                if (!users.containsKey(username)) {
                    System.out.println("Username " + username + " doesn't exist.");
                    if (Utils.stayMenu(scanner)) continue;
                }
                else {
                    User thisUser = users.get(username);
                    System.out.println("User "+ username + "'s permission is " + thisUser.getPermission()
                            + " and status is " + thisUser.getActiveStatus() +", choose status want to change:");
                    System.out.println("1. Permission.");
                    System.out.println("2. Active Status.");
                    int choose = Integer.parseInt(scanner.nextLine());
                    switch (choose) {
                        case 1 -> {
                            thisUser.setAdmin(!thisUser.isAdmin());  //thay đổi ngược lại quyền hiện tại
                            System.out.println("Change permission successful");
                        }
                        case 2 -> {
                            thisUser.setActive(!thisUser.isActive());   //thay đổi ngược lại trạng thái hoạt động hiện tại
                            System.out.println("Change active status successful");
                        }
                    }
                }
                break;
            }
        }
    }
    public boolean checkExistsUsername(String username, Map<String, User> users) {
        if (users.containsKey(username)) {
                System.out.println("Username " + username + " already exists, please try again!");
                return false;
            }
            else return true;
    }
    public boolean checkExistsEmail(String email, Map<String, User> users) {
        int count = 0;
        for (Map.Entry<String, User> entry : users.entrySet()) {
            if (entry.getValue().getEmail().equalsIgnoreCase(email)) {
                System.out.println("User with email " + email + " already exists, please try again!");
                count++;
                break;
            }
        }
        return count != 0;
    }
    public boolean checkCurrentUserEmail(Scanner scanner, User user) {
        System.out.println("Enter your current email:");
        String email = scanner.nextLine();
        if (!user.getEmail().equalsIgnoreCase(email)) {
            System.out.println("Incorrect email, try again!");
            return false;
        }
        else return true;
    }
    public boolean checkCurrentUserPassword(Scanner scanner, User user) {
        System.out.println("Enter your current password:");
        String password = scanner.nextLine();
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password, try again!");
            return false;
        }
        else return true;
    }

}
