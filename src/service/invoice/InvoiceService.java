package service.invoice;

import entities.*;
import service.identity.CustomerService;
import service.identity.IdentityInfoService;
import utils.Utils;
import view.Menu;
import view.Show;

import java.nio.IntBuffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InvoiceService extends IdentityInfoService {
    public void handleManageInvoice(Scanner scanner, Menu menu, User user, Seller seller, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Product> products,
                                    Map<String, Customer> customers, Map<Integer, Invoice> invoices, Map<Integer, ProductInvoiceDetail> productInvoiceDetails,
                                    IdentityInfoService identityInfoService, CustomerService customerService,
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
                            handleSelectInvoice(scanner, menu, user, invoiceTemplates, products, productInvoiceDetails, customers, invoices, identityInfoService, customerService);
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
            System.out.println("========== Create new invoice ==========");
            while (true) {
                System.out.print("Enter invoice template serial: ");
                String invTempSerial = scanner.nextLine();
                if (!invoiceTemplates.containsKey(invTempSerial.toUpperCase())) {
                    System.out.println("Template with serial '" + invTempSerial.toUpperCase() + "' doesn't exist, try again!");
                    continue;
                }
                if (!invoiceTemplates.get(invTempSerial.toUpperCase()).isActive()) {
                    System.out.println("Template with serial '" + invTempSerial.toUpperCase() + "' doesn't active, try again!");
                    continue;
                }
                InvoiceTemplate invoiceTemplate = invoiceTemplates.get(invTempSerial.toUpperCase());
                LocalDate invoiceDate = LocalDate.now();
                System.out.print("\n" + "Enter description of invoice: ");
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
                        System.out.println("----------  Information of products: ---------");
                        Map<Integer, ProductInvoiceDetail> productInvoiceDetails = inputProductListToInvoice(scanner, products);
                        System.out.println("Create invoice successful!");
                        return new Invoice(invoiceTemplate, invoiceDate, description, seller, customer, user, paymentMethod, productInvoiceDetails);

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
                    Show.showInfoCustomers(customers);  //Show list customers
                    while (true) {
                        System.out.print("Enter customer code: ");
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
                while (true) {
                    System.out.print("Enter customer code: ");
                    String customerCode = scanner.nextLine();
                    if (Utils.checkValidStringIsNull(customerCode)) continue;
                    if (customers.containsKey(customerCode)) {
                        System.out.println("Customer with code '" + customerCode + "' already exist, choose this existing customer? (Y/N)");
                        String chooseReEnter = scanner.nextLine();
                        if (chooseReEnter.equalsIgnoreCase("Y")) {
                            return customers.get(customerCode);
                        }
                        else break;
                    } else {
                        return customerService.inputCustomer(scanner, customerCode, identityInfoService);
                    }
                }
            }
        }
    }

    public Map<Integer, ProductInvoiceDetail> inputProductListToInvoice(Scanner scanner, Map<String, Product> products) {
        Map<Integer, ProductInvoiceDetail> productInvoiceDetails = new HashMap<>();
        //System.out.println("\n" + "----------  Information of products: ---------");
        while (true) {
            try {
                System.out.print("Enter the number of product lines: ");
                int countProduct = Integer.parseInt(scanner.nextLine());
                if (Utils.checkValidPositiveNumber(countProduct)) {
                    Show.showInfoProducts(products);
                    for (int i = 0; i < countProduct; i++) {
                        ProductInvoiceDetail productInvoiceDetail = inputProductInvoiceDetail(scanner, i, products, productInvoiceDetails);
                        productInvoiceDetails.put(productInvoiceDetail.getProductInvoiceId(), productInvoiceDetail);
                    }
                    return productInvoiceDetails;
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public ProductInvoiceDetail inputProductInvoiceDetail(Scanner scanner, int index, Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        System.out.println("\n" + (index + 1) + ". Product line " + (index + 1) + ":");
        while (true) {
            System.out.print("Enter product code: ");
            String productCode = scanner.nextLine();
            var a = productInvoiceDetails.entrySet().stream().filter(e -> e.getValue().getProduct().getProductCode().equalsIgnoreCase(productCode));
            if (!products.containsKey(productCode)) {
                System.out.println("Product with code = '" + productCode + "' doesn't exist, please re-enter!");
            } else if (a.findAny().isPresent()) {
                System.out.println("Product with code = '" + productCode + "' has been added, please re-enter!");
            } else {
                Product product = products.get(productCode);
                while (true) {
                    System.out.print("Enter quantity of this product / service: ");
                    try {
                        int quantity = Integer.parseInt(scanner.nextLine());
                        if (Utils.checkValidPositiveNumber(quantity)) {
                            while (true) {
                                try {
                                    System.out.print("\n" + "Enter discount rate (%) of this product: ");
                                    double discountRate = Double.parseDouble(scanner.nextLine());
                                    if (discountRate >= 0 && discountRate <= 100) {
                                        return new ProductInvoiceDetail(product, quantity, discountRate);
                                    } else
                                        System.out.println("Number entered is outside the valid range, please re-enter!");
                                } catch (Exception e) {
                                    System.out.println("Invalid value Integer, please try again!");
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
                                    Map<Integer, ProductInvoiceDetail> productInvoiceDetails, Map<String, Customer> customers, Map<Integer, Invoice> invoices,
                                    IdentityInfoService identityInfoService, CustomerService customerService) {
        while (true) {
            if (!Utils.checkInvoicesIsEmpty(invoices)) {
                Show.showInfoInvoices(invoices);
                menu.menuFunctionInvoice();
                try {
                    int choose = Integer.parseInt(scanner.nextLine());
                    switch (choose) {
                        case 1 -> {
                            Invoice invoice = selectInvoice(scanner, invoices);
                            if (invoice != null) {
                                handleInvAfterSelect(scanner, menu, user, invoice, invoices, invoiceTemplates, customers, products, productInvoiceDetails, identityInfoService, customerService);
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
            else break;
        }
    }

    public Invoice selectInvoice(Scanner scanner, Map<Integer, Invoice> invoices) {
        while (true) {
            System.out.print("Enter invoice ID: ");
            int selectInvId = Integer.parseInt(scanner.nextLine());
            if (!invoices.containsKey(selectInvId)) {
                System.out.println("Invoice with ID = '" + selectInvId + "' doesn't exist!");
                if (Utils.stayMenu(scanner)) continue;
                else return null;
            } else return invoices.get(selectInvId);
        }
    }

    public void handleInvAfterSelect(Scanner scanner, Menu menu, User user, Invoice invoice, Map<Integer, Invoice> invoices, Map<String, InvoiceTemplate> invoiceTemplates,
                                     Map<String, Customer> customers, Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails,
                                     IdentityInfoService identityInfoService, CustomerService customerService) {
        while (true) {
            menu.menuHandleInvoice();
            try {
                int chooseHandleInv = Integer.parseInt(scanner.nextLine());
                switch (chooseHandleInv) {
                    case 1 -> {
                        //Show invoice details
                        Show.showInfoInvoiceDetails(invoice);
                    }
                    case 2 -> {
                        //Edit information of invoice.
                        handleEditInvoice(scanner, menu, user, invoice, invoiceTemplates, customers, products, productInvoiceDetails, identityInfoService, customerService);
                    }
                    case 3 -> {
                        //Publish invoice
                        publishInvoice(scanner, invoice);
                        return;
                    }
                    case 4 -> {
                        //Delete invoice
                        deleteInvoice(scanner, user, invoice, invoices);
                        return;
                    }
                    case 5 -> {
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
                                  Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails, IdentityInfoService identityInfoService, CustomerService customerService) {
        if (invoice.isInvoicePublished()) {
            System.out.println("This invoice was published and can't be edited!");
        } else {
            if (!user.isAdmin()) {
                if (invoice.getUser().getUsername().equalsIgnoreCase(user.getUsername())) {
                    editInvoice(scanner, menu, invoice, invoiceTemplates, customers, products, productInvoiceDetails, identityInfoService, customerService);
                } else {
                    System.out.println("This invoice wasn't created by this user so can't edit!");
                }
            } else {
                editInvoice(scanner, menu, invoice, invoiceTemplates, customers, products, productInvoiceDetails, identityInfoService, customerService);
            }
        }
    }

    public void editInvoice(Scanner scanner, Menu menu, Invoice invoice, Map<String, InvoiceTemplate> invoiceTemplates, Map<String, Customer> customers, Map<String, Product> products,
                            Map<Integer, ProductInvoiceDetail> productInvoiceDetails, IdentityInfoService identityInfoService, CustomerService customerService) {
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
                        handleEditProductInInvoice(scanner, menu, invoice, products, productInvoiceDetails);
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
        Show.showInfoInvoiceTemplates(invoiceTemplates);
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

    public void handleEditProductInInvoice(Scanner scanner, Menu menu, Invoice invoice, Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        while (true) {
            menu.menuEditProductInvoice();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        //Add product invoice
                        addProductInInvoice(scanner, invoice, products, productInvoiceDetails);
                    }
                    case 2 -> {
                        //Edit product invoice
                        editProductInvoice(scanner, menu, invoice, productInvoiceDetails);
                    }
                    case 3 -> {
                        //Delete product invoice
                        deleteProductInInvoice(scanner, invoice);
                    }
                    case 4 -> {
                        reCalculatePriceOfInvoice(invoice);  //Tính lại giá tiền của hoá đơn rồi thoát
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

    public void addProductInInvoice(Scanner scanner, Invoice invoice, Map<String, Product> products, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        ProductInvoiceDetail newProductInv = inputProductInvoiceDetail(scanner, productInvoiceDetails.size(), products, productInvoiceDetails);
        productInvoiceDetails.put(newProductInv.getProductInvoiceId(), newProductInv);
        invoice.setProductInvoiceDetails(productInvoiceDetails);
        System.out.println("Add invoice's product with ID'" + newProductInv.getProductInvoiceId() + "' successfully!");
    }

    public void editProductInvoice(Scanner scanner, Menu menu, Invoice invoice, Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        Show.showInfoProductInvoiceDetails(productInvoiceDetails);
        while (true) {
            menu.menuChooseProductInvoice();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        editProductInInvAfterSelect(scanner, menu, invoice, selectProductInInvoice(scanner, invoice));
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

    public ProductInvoiceDetail selectProductInInvoice(Scanner scanner, Invoice invoice) {
        while (true) {
            System.out.println("Enter invoice's product ID: ");
            int productInvoiceId = Integer.parseInt(scanner.nextLine());
            if (!invoice.getProductInvoiceDetails().containsKey(productInvoiceId)) {
                System.out.println("Invoice's product with id '" + productInvoiceId + "' doesn't exist, please re-enter!");
            } else {
                return invoice.getProductInvoiceDetails().get(productInvoiceId);
            }
        }
    }

    public void editProductInInvAfterSelect(Scanner scanner, Menu menu, Invoice invoice, ProductInvoiceDetail productInvoiceDetail) {
        while (true) {
            menu.menuEditProductInvAfterSelect();
            try {
                int choose = Integer.parseInt(scanner.nextLine());
                switch (choose) {
                    case 1 -> {
                        editQuantityOfInvoiceProduct(scanner, productInvoiceDetail);
                    }
                    case 2 -> {
                        editDiscountOfInvoiceProduct(scanner, productInvoiceDetail);
                    }
                    case 3 -> {
                        reCalculatePriceOfInvoice(invoice);  //Tính lại giá tiền của hoá đơn rồi thoát
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

    public void editQuantityOfInvoiceProduct(Scanner scanner, ProductInvoiceDetail productInvoiceDetail) {
        while (true) {
            System.out.println("Enter new quantity of invoice's product: ");
            try {
                int newQuantity = Integer.parseInt(scanner.nextLine());
                if (Utils.checkValidPositiveNumber(newQuantity)) {
                    productInvoiceDetail.setQuantity(newQuantity); //Gán lại số lượng mới
                    reCalculatePriceOfInvoiceProduct(productInvoiceDetail); //Tính lại VAT, tổng tiền
                    System.out.println("Edit new quantity successfully!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public void editDiscountOfInvoiceProduct(Scanner scanner, ProductInvoiceDetail productInvoiceDetail) {
        while (true) {
            System.out.println("Enter new discount rate of invoice's product: ");
            try {
                double newDiscountRate = Double.parseDouble(scanner.nextLine());
                if (Utils.checkValidPositiveNumber(newDiscountRate)) {
                    productInvoiceDetail.setDiscountRate(newDiscountRate); //Gán lại discount rate mới
                    reCalculatePriceOfInvoiceProduct(productInvoiceDetail); //Tính lại VAT, tổng tiền
                    System.out.println("Edit new discount rate successfully!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }
    }

    public void deleteProductInInvoice(Scanner scanner, Invoice invoice) {
        while (true) {
            System.out.println("Enter invoice's product ID want to delete:");
            try {
                int valueDelProductId = Integer.parseInt(scanner.nextLine());
                if (!invoice.getProductInvoiceDetails().containsKey(valueDelProductId)) {
                    System.out.println("Invoice's product with ID '" + valueDelProductId + "' doesn't exist, please re-enter!");
                } else {
                    System.out.println("Are you sure to delete this invoice's product ? (Y/N)");
                    String choose = scanner.nextLine();
                    if (choose.equalsIgnoreCase("Y")) {
                        invoice.getProductInvoiceDetails().remove(valueDelProductId);
                        System.out.println("Delete invoice's product with ID '" + valueDelProductId + "' successfully!");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value Integer, please try again!");
            }
        }

    }

    public void reCalculatePriceOfInvoiceProduct(ProductInvoiceDetail productInvoiceDetail) {
        productInvoiceDetail.calculateVATPrice(productInvoiceDetail.getProduct());  //Tính lại VAT
        productInvoiceDetail.calculateTotalPrice(productInvoiceDetail.getProduct());    //Tính lại tổng tiền
    }

    public void reCalculatePriceOfInvoice(Invoice invoice) {
        invoice.setTotalVATPrice(invoice.calculateTotalVATInvoice(invoice.getProductInvoiceDetails())); //Tính lại giá VAT của invoice
        invoice.setTotalPrice(invoice.calculateTotalPriceInvoice(invoice.getProductInvoiceDetails()));   //Tính lại thành tiền của invoice
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
        else if (invoice.isInvoiceDeleted()) {
            System.out.println("This invoice has been deleted!");
        } else if (invoice.isInvoicePublished() && Utils.checkUserIsAdmin(user)) {
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
