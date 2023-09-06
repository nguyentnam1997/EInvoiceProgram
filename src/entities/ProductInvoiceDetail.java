package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductInvoiceDetail {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private static int autoId;

    @Setter(AccessLevel.NONE)
    private int productInvoiceId;
    private Invoice invoice;
    private Product product;
    private int quantity;
    private float discountRate;
    private double totalPrice;

    public ProductInvoiceDetail(Invoice invoice, Product product, int quantity, float discountRate, double totalPrice) {
        this.productInvoiceId = ++autoId;
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
    }
}
