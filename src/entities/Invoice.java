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
    private Integer invoiceId;
    @Setter(AccessLevel.NONE)
    private Integer invoiceNo;
    private InvoiceTemplate invoiceTemplate;
    private LocalDateTime invoiceDate;
    private Seller seller;
    private Customer customer;
    private User user;
    private double totalVATPrice;
    private double totalPrice;
    private boolean isInvoicePublished;
    private int invoiceStatus;
    private List<ProductInvoiceDetail> productInvoiceDetails;

    public Invoice(InvoiceTemplate invoiceTemplate, LocalDateTime invoiceDate, Seller seller, Customer customer, User user, double totalVATPrice, double totalPrice, boolean isInvoicePublished, int invoiceStatus, List<ProductInvoiceDetail> productInvoiceDetails) {
        this.invoiceId = ++autoId;
        this.invoiceTemplate = invoiceTemplate;
        this.invoiceDate = invoiceDate;
        this.seller = seller;
        this.customer = customer;
        this.user = user;
        this.totalVATPrice = totalVATPrice;
        this.totalPrice = totalPrice;
        this.isInvoicePublished = isInvoicePublished;
        this.invoiceStatus = invoiceStatus;
        this.productInvoiceDetails = productInvoiceDetails;
    }
    public Invoice() {}
//    public String getPaymentMethod(int paymentMethod) {
//        if (paymentMethod == 1) return "TM";
//        else if (paymentMethod == 2) return "CK";
//        else return "TM/CK";
//    }
}
