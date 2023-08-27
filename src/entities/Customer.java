package entities;

import lombok.Getter;
import lombok.Setter;

public class Customer extends IdentityInfo {
    private static int autoId;
    @Getter
    private int customerId;
    @Getter @Setter
    private String buyerName;
    @Getter @Setter
    private boolean isCompany;

    public Customer(int taxCode, String name, String address, String email, int phoneNumber, String bankAccount, String buyerName, boolean isCompany) {
        super(taxCode, name, address, email, phoneNumber, bankAccount);
        this.customerId = ++autoId;
        this.buyerName = buyerName;
        this.isCompany = isCompany;
    }
}
