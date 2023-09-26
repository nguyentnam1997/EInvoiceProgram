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
    private Product product;
    private int quantity;
    private double discountRate;
    private double discountPrice;
    private double VATPrice;
    private double totalPrice;

    public ProductInvoiceDetail(Product product, int quantity, double discountRate) {
        this.productInvoiceId = ++autoId;
        this.product = product;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.discountPrice = calculateDiscountPrice(product);
        //this.discountRateString = getDiscountRate(this.discountRate);
        this.VATPrice = calculateVATPrice(getProduct());
        this.totalPrice = calculateTotalPrice(getProduct());
    }
//    public String getDiscountRate(double discountRate) {
//       return discountRate + "%";
//    }
    public double calculateDiscountPrice(Product product) {
        return product.getUnitPrice() * (getDiscountRate() / 100);
    }
    public double calculateVATPrice(Product product) {
        return ((product.getUnitPrice() - calculateDiscountPrice(product)) * product.getVATRate()) * getQuantity() ;
    }
    public double calculateTotalPrice(Product product) {
        return (product.getUnitPrice() - calculateDiscountPrice(product)) * getQuantity() + calculateVATPrice(product);
    }
}
