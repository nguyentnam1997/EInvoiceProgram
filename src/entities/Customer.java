package entities;

import lombok.Getter;
import lombok.Setter;

public class Customer extends IdentityInfo {
    @Getter @Setter
    private String customerId;
    @Getter
    private String customerCode;
    @Getter
    @Setter
    private String buyerName;
    @Getter
    @Setter
    private boolean isOrganization;

    public Customer(String customerId, boolean isOrganization, int taxCode, String name, String address, String email, int phoneNumber, String bankAccount, String buyerName) {
        super(taxCode, name, address, email, phoneNumber, bankAccount);
        this.customerId = customerId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public Customer(String customerId, boolean isOrganization, String address, String email, int phoneNumber, String bankAccount, String buyerName) {
        super(address, email, phoneNumber, bankAccount);
        this.customerId = customerId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public Customer() {

    }
    public Customer(IdentityInfo identityInfo, String customerId, boolean isOrganization, String buyerName) {
        super(identityInfo.getTaxCode(), identityInfo.getName(), identityInfo.getAddress(), identityInfo.getEmail(), identityInfo.getHotline(), identityInfo.getBankAccount());
        this.customerId = customerId;
        this.isOrganization = isOrganization;
        this.buyerName = buyerName;
    }

    public String getPositionCustomer() {
        if (isOrganization) return "Organization";
        else return "Personal";
    }
}
