package entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Customer extends IdentityInfo {
    private static int autoId;
    @Getter @Setter(AccessLevel.NONE)
    private int customerId;
    @Getter
    private String customerCode;
    @Getter
    @Setter
    private String buyerName;
    @Getter
    @Setter
    private boolean isOrganization;

    public Customer(boolean isOrganization, int taxCode, String name, String address, String email, int phoneNumber, String bankAccount, String buyerName) {
        super(taxCode, name, address, email, phoneNumber, bankAccount);
        this.customerId = ++autoId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public Customer(boolean isOrganization, String address, String email, int phoneNumber, String bankAccount, String buyerName) {
        super(address, email, phoneNumber, bankAccount);
        this.customerId = ++autoId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public Customer() {

    }
    public Customer(IdentityInfo identityInfo, boolean isOrganization, String buyerName) {
        super(identityInfo.getTaxCode(), identityInfo.getName(), identityInfo.getAddress(), identityInfo.getEmail(), identityInfo.getHotline(), identityInfo.getBankAccount());
        this.customerId = ++autoId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public String getPositionCustomer() {
        if (isOrganization) return "Organization";
        else return "Personal";
    }
}
