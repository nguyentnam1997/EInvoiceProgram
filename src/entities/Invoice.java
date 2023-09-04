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
    //private static int autoInvNo;
    @Setter(AccessLevel.NONE)
    private int invoiceId;
    @Setter(AccessLevel.NONE)
    private int invoiceNo;
    private InvoiceTemplate invoiceTemplate;
    private LocalDateTime invoiceDate;
    private String description;
    private Seller seller;
    private Customer customer;
    private User user;
    private int paymentMethod;
    private double totalVATPrice;
    private double totalPrice;
    private boolean isInvoicePublished;
    private int invoiceStatus;
    private List<ProductInvoiceDetail> productInvoiceDetails;

    public Invoice(InvoiceTemplate invoiceTemplate, LocalDateTime invoiceDate, String description, Seller seller, Customer customer, User user,  int paymentMethod, double totalVATPrice, double totalPrice, boolean isInvoicePublished, int invoiceStatus, List<ProductInvoiceDetail> productInvoiceDetails) {
        this.invoiceId = ++autoId;
        this.invoiceTemplate = invoiceTemplate;
        this.invoiceDate = invoiceDate;
        this.description = description;
        this.seller = seller;
        this.customer = customer;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.totalVATPrice = totalVATPrice;
        this.totalPrice = totalPrice;
        this.isInvoicePublished = isInvoicePublished;
        this.invoiceStatus = invoiceStatus;
        this.productInvoiceDetails = productInvoiceDetails;
    }
}
