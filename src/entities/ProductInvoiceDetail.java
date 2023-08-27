package entities;

import lombok.Data;

@Data
public class ProductInvoiceDetail {
    private int invoiceId;
    private int quantity;
    private ProductPrice productPrice;
}
