package view;

public class Menu {
    /*public void menuLoginRegister() {
        System.out.println("======== Invoice Program ========");
        System.out.println("1. Login.");
        System.out.println("2. Register Service.");
        System.out.println("Choose options:");
    }*/
    public void menuOptionsAfterLogin() {
        System.out.println("------- Welcome -------");
        System.out.println("1. Company management.");
        System.out.println("2. User management.");
        System.out.println("3. Invoices management.");
        System.out.println("4. Product list management.");
        System.out.println("5. Customer list management.");
        System.out.println("6. Log out.");
        System.out.println("Choose options:");
    }
    public void menuManageCompany() {
        System.out.println("------- 1. Company management -------");
        System.out.println("1.1. Show company's information.");
        System.out.println("1.2. Edit company's information.");
        System.out.println("1.3. Back to main menu.");
        System.out.println("Choose options:");
    }
    public void menuEditCompanyInformation() {
        System.out.println("------- 1.2. Edit company's information -------");
        System.out.println("1.2.1 Edit company's name.");
        System.out.println("1.2.2 Edit company's address.");
        System.out.println("1.2.3. Edit company's email.");
        System.out.println("1.2.4. Edit company's hotline.");
        System.out.println("1.2.5. Edit company's bank account.");
        System.out.println("1.2.6. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuManageUser() {
        System.out.println("------- 2. User management -------");
        System.out.println("2.1. Show this user's information.");
        System.out.println("2.2. Edit user's information.");
        System.out.println("2.3. Create new user.");
        System.out.println("2.4. Change user status.");
        System.out.println("2.5. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuEditUserInformation() {
        System.out.println("------- 2.2. Edit user's information -------");
        System.out.println("2.2.1. Change this user's password.");
        System.out.println("2.2.2. Change this user's email.");
        System.out.println("2.2.3. Back to previous menu.");
        System.out.println("Choose options:");
    }
}
