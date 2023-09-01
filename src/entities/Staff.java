package entities;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Staff extends Account{
    private int sellerTaxCode;
    private boolean isAdmin;
    private List<Invoice> staffInvoiceList;

    public Staff(String username, String password, String email, int sellerTaxCode, boolean isAdmin) {
        super(username, password, email);
        this.sellerTaxCode = sellerTaxCode;
        this.isAdmin = isAdmin;
        this.staffInvoiceList = null;
    }
}
