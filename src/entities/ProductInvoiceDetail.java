package entities;

import lombok.Data;

@Data
public class ProductInvoiceDetail {
    private int invoiceId;
    private int quantity;
    private ProductPrice productPrice;

    public ProductInvoiceDetail(int invoiceId, int quantity, ProductPrice productPrice) {
        this.invoiceId = invoiceId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }
}
