package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    @Setter(AccessLevel.NONE)
    private String productCode;

    private String productName;
    private String description;
    private double unitPrice;
    private float VATRate;

    public Product(String productCode, String productName, String description, double unitPrice, float VATRate) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.VATRate = VATRate;
    }
}
