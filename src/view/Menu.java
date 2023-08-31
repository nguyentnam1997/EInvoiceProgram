package view;

import entities.Staff;

public class Menu {
    public void menuLoginRegister() {
        System.out.println("======== Invoice Program ========");
        System.out.println("1. Login.");
        System.out.println("2. Register Service.");
        System.out.println("Choose options:");
    }
    public void menuOptionsAfterLogin() {
        System.out.println("------- Welcome -------");
        System.out.println("1. Company's information management.");
        System.out.println("2. Invoices management.");
        System.out.println("3. Products list management.");
        System.out.println("4. Customers list management.");
        System.out.println("5. Staffs list management.");
        System.out.println("6. Log out.");
        System.out.println("Choose options:");
    }
    public void menuCompanyInformation() {
        System.out.println("------- Company's information -------");
        System.out.println("1. Show company's information.");
        System.out.println("2. Edit information.");
        System.out.println("3. Back to main menu.");
        System.out.println("Choose options:");
    }
    public void menuEditInformation() {
        System.out.println("------- Edit  -------");
        System.out.println("1. Edit company's name.");
        System.out.println("2. Edit company's address.");
        System.out.println("3. Edit company's email.");
        System.out.println("4. Edit company's hotline.");
        System.out.println("5. Edit company's bank account.");
        System.out.println("6. Back to previous menu.");
        System.out.println("Choose options:");
    }
}
