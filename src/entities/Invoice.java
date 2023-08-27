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
    private int invId;

    @Setter(AccessLevel.NONE)
    private int transactionId;

    @Setter(AccessLevel.NONE)
    private int invNo;

    private LocalDateTime invDateTime;
    private String description;
    private Seller seller;
    private Customer customer;
    private InvoiceTemplate invTemplate;
    private int paymentMethod;
    private double totalVATPrice;
    private double totalPrice;
    private boolean isInvoicePublished;
    private int invoiceStatus;
    private List<ProductInvoiceDetail> productInvoiceDetails;

    public Invoice(LocalDateTime invDateTime, String description, Seller seller, Customer customer, InvoiceTemplate invTemplate, int paymentMethod, double totalVATPrice, double totalPrice, boolean isInvoicePublished, int invoiceStatus, List<ProductInvoiceDetail> productInvoiceDetails) {
        this.invId = ++invId;
        this.transactionId = ++autoTransactionId;
        this.invNo = ++invNo;
        this.invDateTime = invDateTime;
        this.description = description;
        this.seller = seller;
        this.customer = customer;
        this.invTemplate = invTemplate;
        this.paymentMethod = paymentMethod;
        this.totalVATPrice = totalVATPrice;
        this.totalPrice = totalPrice;
        this.isInvoicePublished = isInvoicePublished;
        this.invoiceStatus = invoiceStatus;
        this.productInvoiceDetails = productInvoiceDetails;
    }
}
