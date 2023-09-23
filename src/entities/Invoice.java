package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class Invoice {
    private static int autoId;
    private static int autoInvNo;
    @Setter(AccessLevel.NONE)
    private int invoiceId;
    //@Setter(AccessLevel.NONE)
    private Integer invoiceNo;
    private InvoiceTemplate invoiceTemplate;
    private LocalDate invoiceDate;
    private String description;
    private Seller seller;
    private Customer customer;
    private User user;
    private String paymentMethod;
    private Map<Integer, ProductInvoiceDetail> productInvoiceDetails;
    private double totalVATPrice;
    private double totalPrice;
    private boolean isInvoicePublished;
    private boolean isInvoiceDeleted;

    public Invoice(InvoiceTemplate invoiceTemplate, LocalDate invoiceDate, String description, Seller seller, Customer customer, User user,  int paymentMethod,  Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        this.invoiceId = ++autoId;
        this.invoiceNo = 0;
        this.invoiceTemplate = invoiceTemplate;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.seller = seller;
        this.customer = customer;
        this.user = user;
        this.paymentMethod = getPaymentMethod(paymentMethod);
        this.productInvoiceDetails = productInvoiceDetails;
        this.totalVATPrice = calculateTotalVATInvoice(getProductInvoiceDetails());
        this.totalPrice = calculateTotalPriceInvoice(getProductInvoiceDetails());
        this.isInvoicePublished = false;
        this.isInvoiceDeleted = false;
    }
    public String getPaymentMethod(int paymentMethod) {
        if (paymentMethod == 1) return "TM";
        else if (paymentMethod == 2) return "CK";
        else return "TM/CK";
    }

    public int getAutoInvNo(boolean isInvoicePublished) {
        if (isInvoicePublished) return ++autoInvNo;
        else return autoInvNo;
    }

    public void setInvoiceNo() {
        this.invoiceNo = getAutoInvNo(isInvoicePublished);
    }

    public double calculateTotalVATInvoice(Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        double totalVAT = 0;
        for (Map.Entry<Integer, ProductInvoiceDetail> entry: productInvoiceDetails.entrySet()) {
            totalVAT = totalVAT + entry.getValue().getVATPrice();
        }
        return totalVAT;
    }
    public double calculateTotalPriceInvoice(Map<Integer, ProductInvoiceDetail> productInvoiceDetails) {
        double totalPrice = 0;
        for (Map.Entry<Integer, ProductInvoiceDetail> entry: productInvoiceDetails.entrySet()) {
            totalPrice = totalPrice + entry.getValue().getTotalPrice();
        }
        return totalPrice;
    }

}
