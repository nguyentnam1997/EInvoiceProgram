package view;

public class Menu {
    /*public void menuLoginRegister() {
        System.out.println("======== Invoice Program ========");
        System.out.println("1. Login.");
        System.out.println("2. Register Service.");
        System.out.println("Choose options:");
    }*/
    public void menuOptionsAfterLogin() {
        System.out.println("\n" + "------------- Welcome ---------------");
        System.out.println("1. Company management.");
        System.out.println("2. User management.");
        System.out.println("3. Invoices management.");
        System.out.println("4. Products management.");
        System.out.println("5. Customers management.");
        System.out.println("6. Log out.");
        System.out.println("Choose options:");
    }
    public void menuManageCompany() {
        System.out.println("\n" + "---------- 1. Company management ----------");
        System.out.println("1.1. Show company's information.");
        System.out.println("1.2. Edit company's information.");
        System.out.println("1.3. Back to main menu.");
        System.out.println("Choose options:");
    }
    public void menuEditCompanyInformation() {
        System.out.println("\n" + "---------- 1.2. Edit company's information ----------");
        System.out.println("1.2.1. Edit company's name.");
        System.out.println("1.2.2. Edit company's address.");
        System.out.println("1.2.3. Edit company's email.");
        System.out.println("1.2.4. Edit company's hotline.");
        System.out.println("1.2.5. Edit company's bank account.");
        System.out.println("1.2.6. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuManageUser() {
        System.out.println("\n" + "---------- 2. User management ----------");
        System.out.println("2.1. Show this user's information.");
        System.out.println("2.2. Edit user's information.");
        System.out.println("2.3. Create new user.");
        System.out.println("2.4. Change user status.");
        System.out.println("2.5. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuEditUserInformation() {
        System.out.println("\n" + "---------- 2.2. Edit user's information ----------");
        System.out.println("2.2.1. Change this user's password.");
        System.out.println("2.2.2. Change this user's email.");
        System.out.println("2.2.3. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuManageInvoice() {
        System.out.println("\n" + "---------- 3. Invoices management ----------");
        System.out.println("3.1. Invoice templates management.");
        System.out.println("3.2. Show list and handle invoices.");
        System.out.println("3.3. Create new invoice.");
        //System.out.println("3.4. Invoice report.");
        System.out.println("Choose options:");
    }
    public void menuManageInvoiceTemplate() {
        System.out.println("\n" + "---------- 3.1. Invoice templates management ----------");
        System.out.println("3.1.1. Show list of invoice template.");
        System.out.println("3.1.2. Create invoice template.");
        System.out.println("3.1.3. Change active status.");
        System.out.println("3.1.4. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuFunctionInvoice() {
        System.out.println("\n" + "-------------------------------------");
        System.out.println("3.2.1. Select invoice you want to work with.");
        System.out.println("3.2.2. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuHandleInvoice() {
        System.out.println("\n" + "------------- 3.2.1. Handle Invoice --------------");
        System.out.println("3.2.1.1. Edit information of invoice.");
        System.out.println("3.2.1.2. Publish invoice.");
        System.out.println("3.2.1.3. Delete invoice.");
        System.out.println("3.2.1.4. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuEditInvoice() {
        System.out.println("\n" + "------------- 3.2.1.1 Edit information of invoice --------------");
        System.out.println("3.2.1.1.1. Edit invoice's template.");
        System.out.println("3.2.1.1.2. Edit invoice's customer.");
        System.out.println("3.2.1.1.3. Edit invoice's products.");
        System.out.println("3.2.1.1.4. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuManageProduct() {
        System.out.println("\n" + "---------- 5. Products management ----------");
        System.out.println("5.1. Show product list information.");
        System.out.println("5.2. Create new product.");
        System.out.println("5.3. Back to main menu.");
        System.out.println("Choose options:");
    }
    public void menuFunctionProduct() {
        System.out.println("\n" + "----------------------------------");
        System.out.println("5.1.1. Select product you want to work with.");
        System.out.println("5.1.2. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuHandleProduct() {
        System.out.println("\n" + "------------- 5.1.1. Handle product --------------");
        System.out.println("5.1.1.1. Edit information of product.");
        System.out.println("5.1.1.2. Delete product.");
        System.out.println("5.1.1.3. Back to previous menu.");
        System.out.println("Choose options:");
    }
    public void menuEditProduct() {
        System.out.println("\n" + "------------- 5.1.1.1 Edit information of product --------------");
        System.out.println("5.1.1.1.1. Edit product's name.");
        System.out.println("5.1.1.1.2. Edit product's unit price.");
        System.out.println("5.1.1.1.3. Edit product's VAT rate.");
        System.out.println("5.1.1.1.4. Back to previous menu.");
        System.out.println("Choose options:");
    }
}
