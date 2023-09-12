package service.identity;

import entities.Customer;
import utils.Utils;

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
                        if (Utils.checkValidPositiveNumber(choose) && choose > 2) continue;
                        switch (choose) {
                            case 1 -> {
                                System.out.println("Enter buyer name:");
                                String buyerName = scanner.nextLine();
                                return new Customer(identityInfoService.inputIdentityAsOrganization(scanner), true, buyerName);
                            }
                            case 2 -> {
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
                            default -> {
                                System.out.println("Number entered is outside the valid range, please re-enter!");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!" + "\n");
                    }
                }
            }
        }
    }
    public void createCustomer(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService) {
        Customer customer = inputCustomer(scanner, customers, identityInfoService);
        customers.put(customer.getCustomerCode(), customer);
    }
}
