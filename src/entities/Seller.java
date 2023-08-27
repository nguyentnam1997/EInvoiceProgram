package entities;

import lombok.Getter;
import lombok.Setter;

public class Seller extends  IdentityInfo{
    @Getter @Setter
    private int invoiceQuantity;

    public Seller(int taxCode, String name, String address, String email, int phoneNumber, String bankAccount, int invoiceQuantity) {
        super(taxCode, name, address, email, phoneNumber, bankAccount);
        this.invoiceQuantity = invoiceQuantity;
    }
}
