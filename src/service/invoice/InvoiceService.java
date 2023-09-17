package service.invoice;

import entities.*;
import service.identity.CustomerService;
import service.identity.IdentityInfoService;
import utils.Utils;
import view.Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InvoiceService extends IdentityInfoService {
    public void handleManageInvoice(Scanner scanner, Menu menu, User user, Seller seller, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Product> products,
                                    Map<String, Customer> customers, Map<Integer, Invoice> invoices, IdentityInfoService identityInfoService, CustomerService customerService,
                                    InvoiceTemplateService invoiceTemplateService) {
        //3. Invoices management
        while (true) {
            menu.menuManageInvoice();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        //3.1. Invoice templates management.
                        invoiceTemplateService.handleInvoiceTemplate(scanner, menu, user, invoiceTemplates);
                    }
                    case 2 -> {
                        //3.2. Show list of invoices.
                        if (!Utils.checkInvoicesIsEmpty(invoices)) {
                            System.out.println(invoices);
                            handleSelectInvoice(scanner, menu, user, invoiceTemplates, products, customers, invoices, identityInfoService, customerService);
                        }
                    }
                    case 3 -> {
                        //3.3. Create new invoice.
                        Invoice invoice = createInvoice(scanner, user, seller, products, invoiceTemplates, customers, identityInfoService, customerService);
                        if (invoice != null) {
                            invoices.put(invoice.getInvoiceId(), invoice);
                        }
                    }
                    case 4 -> {
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

    public Invoice createInvoice(Scanner scanner, User user, Seller seller, Map<String, Product> products, Map<String, InvoiceTemplate> invoiceTemplates,
                                 Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        if (Utils.checkInvTemplatesIsEmpty(invoiceTemplates)) return null;
        else if (Utils.checkProductsIsEmpty(products)) return null;
        else {
            while (true) {
                System.out.println("\n" + "Enter invoice template serial:");
                String invTempSerial = scanner.nextLine();
                if (!invoiceTemplates.containsKey(invTempSerial.toUpperCase())) {
                    System.out.println("Template with serial '" + invTempSerial.toUpperCase() + "' doesn't exist, try again!");
                    continue;
                }
                InvoiceTemplate invoiceTemplate = invoiceTemplates.get(invTempSerial);
                LocalDate invoiceDate = LocalDate.now();
                System.out.println("\n" + "Enter description of invoice:");
                String description = scanner.nextLine();
                Customer customer = inputCustomerInInvoice(scanner, customers, identityInfoService, customerService);  //nhập thông tin customer
                customers.put(customer.getCustomerCode(), customer);  //tự động thêm customer vào list customers
                //int paymentMethod = 0;
                while (true) {
                    System.out.println("\n" + "Enter payment method of invoice: (1. TM / 2. CK / 3. TM/CK) ");
                    try {
                        int paymentMethod = Integer.parseInt(scanner.nextLine());
                        if (!Utils.checkValidPositiveNumber(paymentMethod) || paymentMethod > 3) {
                            System.out.println("Invalid value, please re-enter!");
                            continue;
                        }
                        if (products.isEmpty()) {
                            System.out.println("\n" + "----------  Information of products: ---------");
                            System.out.println("Product list is empty, please create first!");
                            return null;
                        } else {
                            Map<Integer, ProductInvoiceDetail> productInvoiceDetails = inputProductListToInvoice(scanner, products);
                            System.out.println("Create invoice successful!");
                            return new Invoice(invoiceTemplate, invoiceDate, description, seller, customer, user, paymentMethod, productInvoiceDetails);
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!");
                    }
                }
            }
        }
    }

    public Customer inputCustomerInInvoice(Scanner scanner, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        System.out.println("----------  Information of customer: ---------");
        while (true) {
            System.out.println("Do you want to select existing customer? (Y/N)");
            String chooseAutoEnter = scanner.nextLine();
            if (chooseAutoEnter.equalsIgnoreCase("Y")) {
                if (!Utils.checkCustomersIsEmpty(customers)) {
                    System.out.println(customers);  //Show list customers
                    while (true) {
                        System.out.println("\n" + "Enter customer code:");
                        String customerCode = scanner.nextLine();
                        if (!customers.containsKey(customerCode)) {
                            System.out.println("Customer with code '" + customerCode + "' doesn't exist, re-enter? (Y/N)");
                            String chooseReEnter = scanner.nextLine();
                            if (chooseReEnter.equalsIgnoreCase("Y")) continue;
                            else break;
                        } else {
                            return customers.get(customerCode);
                        }
                    }
                }
            } else {
                return customerService.inputCustomer(scanner, customers, identityInfoService);
            }
        }
    }

    public Map<Integer, ProductInvoiceDetail> inputProductListToInvoice(Scanner scanner, Map<String, Product> products) {
        Map<Integer, ProductInvoiceDetail> productInvoiceDetails = new HashMap<>();
        System.out.println("\n" + "----------  Information of products: ---------");
        while (true) {
            try {
                System.out.println("Enter the number of product lines:");
                int countProduct = Integer.parseInt(scanner.nextLine());
                if (Utils.checkValidPositiveNumber(countProduct)) {
                    for (int i = 0; i < countProduct; i++) {
                        ProductInvoiceDetail productInvoiceDetail = inputProductInvoiceDetail(scanner, i, products, productInvoiceDetails);
                        productInvoiceDetails.put(productInvoiceDetail.getProductInvoiceId(), productInvoiceDetail);
                    }
                    return productInvoiceDetails;
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!" + "\n");
            }
        }
    }

    public ProductInvoiceDetail inputProductInvoiceDetail(Scanner scanner, int index, Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        System.out.println("\n" + (index + 1) + ". Product line " + (index + 1) + ":");
        while (true) {
            System.out.println("Enter product code:");
            String productCode = scanner.nextLine();
            var a = productInvoiceDetails.entrySet().stream().filter(e -> e.getValue().getProduct().getProductCode().equalsIgnoreCase(productCode));
            if (!products.containsKey(productCode)) {
                System.out.println("Product with code = '" + productCode + "' doesn't exist, please re-enter!");
            } else if (a.findAny().isPresent()) {
                System.out.println("Product with code = '" + productCode + "' has been added, please re-enter!");
            } else {
                Product product = products.get(productCode);
                while (true) {
                    System.out.println("Enter quantity of this product / service:");
                    try {
                        int quantity = Integer.parseInt(scanner.nextLine());
                        if (Utils.checkValidPositiveNumber(quantity)) {
                            while (true) {
                                try {
                                    System.out.println("Enter discount rate (%) of this product:");
                                    double discountRate = Double.parseDouble(scanner.nextLine());
                                    if (discountRate >= 0 && discountRate <= 100) {
                                        return new ProductInvoiceDetail(product, quantity, discountRate);
                                    } else
                                        System.out.println("Number entered is outside the valid range, please re-enter!");
                                } catch (Exception e) {
                                    System.out.println("Invalid value Integer, please try again!" + "\n");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid value Integer, please try again!" + "\n");
                    }
                }
            }
        }
    }

    public void handleSelectInvoice(Scanner scanner, Menu menu, User user, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Product> products,
                                    Map<String, Customer> customers, Map<Integer, Invoice> invoices, IdentityInfoService identityInfoService, CustomerService customerService) {
        //System.out.println(invoices);
        menu.menuFunctionInvoice();
        try {
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1 -> {
                    Invoice invoice = selectInvoice(scanner, invoices);
                    handleInvAfterSelect(scanner, menu, user, invoice, invoices, invoiceTemplates, customers, identityInfoService, customerService);
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

    public Invoice selectInvoice(Scanner scanner, Map<Integer, Invoice> invoices) {
        while (true) {
            System.out.println("Enter invoice ID: ");
            int selectInvId = Integer.parseInt(scanner.nextLine());
            if (!invoices.containsKey(selectInvId)) {
                System.out.println("Invoice with ID = '" + selectInvId + "' doesn't exist!");
                if (Utils.stayMenu(scanner)) continue;
                else return null;
            } else return invoices.get(selectInvId);
        }
    }

    public void handleInvAfterSelect(Scanner scanner, Menu menu, User user, Invoice invoice, Map<Integer, Invoice> invoices, Map<String, InvoiceTemplate> invoiceTemplates,
                                     Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        while (true) {
            menu.menuHandleInvoice();
            try {
                int chooseHandleInv = Integer.parseInt(scanner.nextLine());
                switch (chooseHandleInv) {
                    case 1 -> {
                        //Edit information of invoice.
                        handleEditInvoice(scanner, menu, user, invoice, invoiceTemplates, customers,
                                identityInfoService, customerService);
                    }
                    case 2 -> {
                        //Publish invoice
                        publishInvoice(scanner, invoice);
                    }
                    case 3 -> {
                        //Delete invoice
                        deleteInvoice(scanner, user, invoice, invoices);
                    }
                    case 4 -> {
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

    public void handleEditInvoice(Scanner scanner, Menu menu, User user, Invoice invoice, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers,
                                  IdentityInfoService identityInfoService, CustomerService customerService) {
        if (invoice.isInvoicePublished()) {
            System.out.println("This invoice was published and can't be edited!");
        } else {
            if (!user.isAdmin()) {
                if (invoice.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                    editInvoice(scanner, menu, invoice, invoiceTemplates, customers, identityInfoService, customerService);
                } else {
                    System.out.println("This invoice wasn't created by this user so can't edit!");
                }
            } else {
                editInvoice(scanner, menu, invoice, invoiceTemplates, customers, identityInfoService, customerService);
            }
        }
    }

    public void editInvoice(Scanner scanner, Menu menu, Invoice invoice, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers,
                            IdentityInfoService identityInfoService, CustomerService customerService) {
        while (true) {
            menu.menuEditInvoice();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                if (!Utils.checkValidPositiveNumber(choose)) continue;
                switch (choose) {
                    case 1 -> {
                        //Edit invoice's template
                        editTemplateInInvoice(scanner, invoice, invoiceTemplates);
                    }
                    case 2 -> {
                        //Edit invoice's customer
                        editCustomerInInvoice(scanner, invoice, customers, identityInfoService, customerService);
                    }
                    case 3 -> {
                        //Edit invoice's products
                    }
                    case 4 -> {
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

    public void editTemplateInInvoice(Scanner scanner, Invoice invoice, Map<String, InvoiceTemplate> invoiceTemplates) {
        //Show list templates of invoice
        System.out.println(invoiceTemplates);
        //Select template want to replace
        while (true) {
            System.out.println("Select template by serial you want to replace:");
            String templateSerial = scanner.nextLine();
            if (!invoiceTemplates.containsKey(templateSerial)) {
                System.out.println("Template with serial = '" + templateSerial + "' doesn't exist, please re-enter!");
            } else if (invoice.getInvoiceTemplate().getTemplateSerial().equalsIgnoreCase(templateSerial)) {
                System.out.println("New template can't be the same as old template, please re-enter!");
            } else {
                System.out.println("Are you sure to replace to template '" + templateSerial + "' ? (Y/N)");
                String choose = scanner.nextLine();
                if (choose.equalsIgnoreCase("Y")) {
                    System.out.println("Invoice template was replaced successfully!");
                    invoice.setInvoiceTemplate(invoiceTemplates.get(templateSerial));
                }
                break;
            }
        }
    }

    public void editCustomerInInvoice(Scanner scanner, Invoice invoice, Map<String, Customer> customers, IdentityInfoService identityInfoService, CustomerService customerService) {
        invoice.setCustomer(inputCustomerInInvoice(scanner, customers, identityInfoService, customerService));
        System.out.println("Customer edited successfully!");
    }

    public void publishInvoice(Scanner scanner, Invoice invoice) {
        if (invoice.isInvoicePublished()) {
            System.out.println("This invoice has already been published!");
        } else {
            System.out.println("Are you sure to publish this invoice? (Y/N)");
            String choose = scanner.nextLine();
            if (choose.equalsIgnoreCase("Y")) {
                System.out.println("Invoice published successfully!");
                invoice.setInvoicePublished(true);
                invoice.setInvoiceNo();
            }
        }
    }

    public void deleteInvoice(Scanner scanner, User user, Invoice invoice, Map<Integer, Invoice> invoices) {
        if (!Utils.checkUserIsAdmin(user)) return;
        else if (invoice.isInvoicePublished() && Utils.checkUserIsAdmin(user)) {
            System.out.println("This invoice has been published, are you sure to delete? (Y/N)");
            String choose = scanner.nextLine();
            if (choose.equalsIgnoreCase("Y")) {
                System.out.println("Invoice deleted successfully!");
                invoice.setInvoiceDeleted(true);
            }
        } else if ((!invoice.isInvoicePublished() && Utils.checkUserIsAdmin(user)) || ((!invoice.isInvoicePublished() && !Utils.checkUserIsAdmin(user)))) {
            System.out.println("This invoice hasn't been published, are you sure to delete? (Y/N)");
            String choose = scanner.nextLine();
            if (choose.equalsIgnoreCase("Y")) {
                System.out.println("Invoice deleted successfully!");
                invoices.remove(invoice.getInvoiceId());
            }
        }
    }
}
