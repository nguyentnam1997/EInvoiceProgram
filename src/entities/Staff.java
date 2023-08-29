package entities;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class Staff extends Account {
    @Getter @Setter
    private int sellerTaxCode;
    @Getter @Setter
    private boolean isAdmin;
    @Getter @Setter
    private List<Invoice> staffInvoiceList;

    public Staff(String username, String password, int sellerTaxCode, boolean isAdmin) {
        super(username, password);
        this.sellerTaxCode = sellerTaxCode;
        this.isAdmin = isAdmin;
        this.staffInvoiceList = null;
    }
}
