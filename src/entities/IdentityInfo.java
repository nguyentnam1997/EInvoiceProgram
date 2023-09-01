package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class IdentityInfo {
    @Setter(AccessLevel.NONE)
    private int taxCode;
    private String name;
    private String address;
    private String email;
    private int hotline;
    private String bankAccount;

    public IdentityInfo(int taxCode, String name, String address, String email, int hotline, String bankAccount) {
        this.taxCode = taxCode;
        this.name = name;
        this.address = address;
        this.email = email;
        this.hotline = hotline;
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return ", taxCode=" + taxCode +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", hotline=" + hotline +
                ", bankAccount='" + bankAccount + '\'';
    }
}
