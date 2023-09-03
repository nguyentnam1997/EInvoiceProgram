package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Invoice {
    private static int autoId;
    private static int autoInvNo;
    private static int autoTransactionId;
    @Setter(AccessLevel.NONE)
    private int invoiceId;

//    @Setter(AccessLevel.NONE)
//    private int transactionId;

    @Setter(AccessLevel.NONE)
    private int invoiceNo;

    private LocalDateTime invoiceDate;
    private String description;
    private Seller seller;
    private Customer customer;
    private User user;
    private InvoiceTemplate invoiceTemplate;
    private int paymentMethod;
    private double totalVATPrice;
    private double totalPrice;
    private boolean isInvoicePublished;
    private int invoiceStatus;
    private List<ProductInvoiceDetail> productInvoiceDetails;

    public Invoice(LocalDateTime invoiceDate, String description, Seller seller, Customer customer, User user, InvoiceTemplate invoiceTemplate, int paymentMethod, double totalVATPrice, double totalPrice, boolean isInvoicePublished, int invoiceStatus, List<ProductInvoiceDetail> productInvoiceDetails) {
        this.invoiceId = ++autoId;
        //this.transactionId = ++autoTransactionId;
        this.invoiceNo = ++autoInvNo;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.seller = seller;
        this.customer = customer;
        this.user = user;
        this.invoiceTemplate = invoiceTemplate;
        this.paymentMethod = paymentMethod;
        this.totalVATPrice = totalVATPrice;
        this.totalPrice = totalPrice;
        this.isInvoicePublished = isInvoicePublished;
        this.invoiceStatus = invoiceStatus;
        this.productInvoiceDetails = productInvoiceDetails;
    }
}
