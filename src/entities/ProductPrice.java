package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ProductPrice {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private static int autoId;

    @Setter(AccessLevel.NONE)
    private int productPriceId;
    private Product product;
    private double unitPrice;
    private float VATRate;
    private float discountRate;
    private double totalPrice;
    private LocalDateTime createdDate;

    public ProductPrice(Product product, double unitPrice, float VATRate, float discountRate, double totalPrice, LocalDateTime createdDate) {
        this.productPriceId = ++autoId;
        this.product = product;
        this.unitPrice = unitPrice;
        this.VATRate = VATRate;
        this.discountRate = discountRate;
        this.totalPrice = totalPrice;
        this.createdDate = createdDate;
    }
}
