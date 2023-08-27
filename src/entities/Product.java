package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private static int autoId;

    @Setter(AccessLevel.NONE)
    private int productId;
    private String productCode;
    private String description;

    public Product(String productCode, String description) {
        this.productId = ++autoId;
        this.productCode = productCode;
        this.description = description;
    }
}
