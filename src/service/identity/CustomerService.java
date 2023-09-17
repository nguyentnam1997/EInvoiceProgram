package service.identity;

import entities.Customer;
import entities.IdentityInfo;
import entities.Product;
import entities.User;
import utils.Utils;
import view.Menu;

import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    public Customer inputCustomer(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        while (true) {
            System.out.println("\n" + "Enter customer code:");
            String customerCode = scanner.nextLine();
            if (customerCode.trim().isEmpty()) {
                System.out.println("Value can't null, please try again!");
            } else if (customers.containsKey(customerCode)) {
                System.out.println("Customer with code '" + customerCode + "' already exist, please re-enter!");
            } else {
                while (true) {
                    System.out.println("This customer is organization or personal? (1. Organization / 2. Personal)");
                    try {
                        int choose = Integer.parseInt(scanner.nextLine());
                        if (choose == 1) {
                            System.out.println("Enter buyer name:");
                            String buyerName = scanner.nextLine();
                            return new Customer(identityInfoService.inputIdentityAsOrganization(scanner), true, buyerName);
                        }
                        else if (choose == 2) {
                            while (true) {
                                System.out.println("Enter buyer name:");
                                String buyerName = scanner.nextLine();
                                if (buyerName.trim().isEmpty()) {
                                    System.out.println("Buyer name can't be null, please re-enter!");
                                    continue;
                                }
                                return new Customer(identityInfoService.inputIdentityAsPersonal(scanner), false, buyerName);
                            }
                        }
                        else System.out.println("Invalid value, please re-enter!");
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!" + "\n");
                    }
                }
            }
        }
    }

    public void createCustomer(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        System.out.println("-------- 5.2. Create new customer --------");
        Customer customer = inputCustomer(scanner, customers, identityInfoService);
        customers.put(customer.getCustomerCode(), customer);
        System.out.println("Create new customer successfully!");
    }

    public void handleManageCustomer(Scanner scanner, Menu menu, User user, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        while (true) {
            menu.menuManageCustomer();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    //Show customer list information
                    case 1 -> {
                        System.out.println(customers);
                        //Select product
                        handleSelectCustomer(scanner, menu, user, customers);
                    }
                    //Create new product
                    case 2 -> {
                        createCustomer(scanner, customers, identityInfoService);
                    }
                    //Back menu
                    case 3 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void handleSelectCustomer(Scanner scanner, Menu menu, User user, Map<String, Customer> customers) {
        while (true) {
            menu.menuFunctionCustomer();
            try {
                int chooseFunc = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(chooseFunc)) continue;
                switch (chooseFunc) {
                    case 1 -> {
                        if (Utils.checkUserIsAdmin(user)) {
                            handleCustomerAfterSelect(scanner, menu, selectCustomer(scanner, customers), customers);
                        }
                    }
                    case 2 -> {
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

    public Customer selectCustomer(Scanner scanner, Map<String, Customer> customers) {
        while (true) {
            System.out.println("Enter customer code:");
            String customerCode = scanner.nextLine();
            if (customers.containsKey(customerCode)) {
                return customers.get(customerCode);
            } else {
                System.out.println("Customer with code '" + customerCode + "' doesn't exist, please re-enter!");
            }
        }
    }

    public void handleCustomerAfterSelect(Scanner scanner, Menu menu, Customer customer, Map<String, Customer> customers) {
        while (true) {
            menu.menuHandleCustomer(); //Handle product
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    //Edit information of product
                    case 1 -> {
                        editCustomer(scanner, menu, customer);
                    }
                    //Delete product
                    case 2 -> {
                        deleteCustomer(scanner, customer, customers);
                    }
                    //Back menu
                    case 3 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void editCustomer(Scanner scanner, Menu menu, Customer customer) {
        if (customer.isOrganization()) {
            editCustomerIsOrganization(scanner, menu, customer);
        } else {
            editCustomerIsPersonal(scanner, menu, customer);
        }
    }

    public void editCustomerIsOrganization(Scanner scanner, Menu menu, Customer customer) {
        while (true) {
            menu.menuEditCustomerIsOrganization();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    case 1 -> {
                        //Edit organization's name
                        System.out.println("5.1.1.1.1. Edit organization's name:");
                        while (true) {
                            System.out.println("Enter new organization's name:");
                            String newOrgName = scanner.nextLine();
                            if (Utils.checkValidStringIsNull(newOrgName)) continue;
                            customer.setName(newOrgName);
                            System.out.println("Change organization's name successfully!");
                            break;
                        }
                    }
                    case 2 -> {
                        //Edit organization's address
                        System.out.println("5.1.1.1.2. Edit organization's address:");
                        while (true) {
                            System.out.println("Enter new organization's address:");
                            String newOrgAddress = scanner.nextLine();
                            if (Utils.checkValidStringIsNull(newOrgAddress)) continue;
                            customer.setAddress(newOrgAddress);
                            System.out.println("Change organization's address successfully!");
                            break;
                        }
                    }
                    case 3 -> {
                        //Edit organization's email
                        System.out.println("5.1.1.1.3. Edit organization's email:");
                        while (true) {
                            System.out.println("Enter new organization's email:");
                            String newOrgEmail = scanner.nextLine();
                            if (!Utils.isValidEmail(newOrgEmail)) {
                                System.out.println("Invalid email, please re-enter!");
                                continue;
                            }
                            customer.setEmail(newOrgEmail);
                            System.out.println("Change organization's email successfully!");
                            break;
                        }
                    }
                    case 4 -> {
                        //Edit organization's hotline
                        System.out.println("5.1.1.1.4. Edit organization's hotline:");
                        while (true) {
                            System.out.println("Enter new organization's hotline:");
                            try {
                                int newOrgHotline = Integer.parseInt(scanner.nextLine());
                                if (String.valueOf(newOrgHotline).length() != 10) {
                                    System.out.println("Hotline must have 10 numbers, please re-enter!");
                                    continue;
                                }
                                customer.setHotline(newOrgHotline);
                                System.out.println("Change organization's hotline successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid value Integer, please try again!");
                            }
                        }
                    }
                    case 5 -> {
                        //Edit organization's bank account
                        System.out.println("5.1.1.1.5. Edit organization's bank account:");
                        System.out.println("Enter new organization's bank account:");
                        String newOrgBankAccount = scanner.nextLine();
                        customer.setBankAccount(newOrgBankAccount);
                        System.out.println("Change organization's bank account successfully!");
                    }
                    case 6 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void editCustomerIsPersonal(Scanner scanner, Menu menu, Customer customer) {
        while (true) {
            menu.menuEditCustomerIsPersonal();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    case 1 -> {
                        //Edit personal's name
                        System.out.println("5.1.1.1.1. Edit personal's name:");
                        while (true) {
                            System.out.println("Enter new personal's name:");
                            String newBuyerName = scanner.nextLine();
                            if (Utils.checkValidStringIsNull(newBuyerName)) continue;
                            customer.setBuyerName(newBuyerName);
                            System.out.println("Change personal's name successfully!");
                            break;
                        }
                    }
                    case 2 -> {
                        //Edit personal's address
                        System.out.println("5.1.1.1.2. Edit personal's address:");
                        while (true) {
                            System.out.println("Enter new personal's address:");
                            String newPsAddress = scanner.nextLine();
                            if (Utils.checkValidStringIsNull(newPsAddress)) continue;
                            customer.setAddress(newPsAddress);
                            System.out.println("Change personal's address successfully!");
                            break;
                        }
                    }
                    case 3 -> {
                        //Edit personal's email
                        System.out.println("5.1.1.1.3. Edit personal's email:");
                        while (true) {
                            System.out.println("Enter new personal's email:");
                            String newPsEmail = scanner.nextLine();
                            if (!Utils.isValidEmail(newPsEmail)) {
                                System.out.println("Invalid email, please re-enter!");
                                continue;
                            }
                            customer.setEmail(newPsEmail);
                            System.out.println("Change personal's email successfully!");
                            break;
                        }
                    }
                    case 4 -> {
                        //Edit personal's phone number
                        System.out.println("5.1.1.1.4. Edit personal's phone number:");
                        while (true) {
                            System.out.println("Enter new personal's phone number:");
                            try {
                                int newPsPhone = Integer.parseInt(scanner.nextLine());
                                if (String.valueOf(newPsPhone).length() != 10) {
                                    System.out.println("Phone number must have 10 numbers, please re-enter!");
                                    continue;
                                }
                                customer.setHotline(newPsPhone);
                                System.out.println("Change personal's phone number successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Invalid value Integer, please try again!");
                            }
                        }
                    }
                    case 5 -> {
                        //Edit personal's bank account
                        System.out.println("5.1.1.1.5. Edit personal's bank account:");
                        System.out.println("Enter new personal's bank account:");
                        String newPsBankAccount = scanner.nextLine();
                        customer.setBankAccount(newPsBankAccount);
                        System.out.println("Change personal's bank account successfully!");
                    }
                    case 6 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid value, please re-enter!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public void deleteCustomer(Scanner scanner, Customer customer, Map<String, Customer> customers) {
        System.out.println("--------- Delete customer ----------");
        System.out.println("Do you want to delete this customer? (Y/N)");
        String choose = scanner.nextLine();
        if (choose.equalsIgnoreCase("Y")) {
            System.out.println("Delete customer with code '" + customer.getCustomerCode() + "' successfully!");
            customers.remove(customer.getCustomerCode());
        }
    }
}
