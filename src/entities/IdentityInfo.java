package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IdentityInfo {
    @Setter(AccessLevel.NONE)
    private Integer taxCode;
    private String name;
    private String address;
    private String email;
    private int hotline;
    private String bankAccount;

    public IdentityInfo(int taxCode, String name, String address, String email, int hotline, String bankAccount) { //constructor for Organization
        this.taxCode = taxCode;
        this.name = name;
        this.address = address;
        this.email = email;
        this.hotline = hotline;
        this.bankAccount = bankAccount;
    }
    public IdentityInfo(int taxCode, String name, String address) {
        this.taxCode = taxCode;
        this.name = name;
        this.address = address;
        this.email = "";
        this.hotline = 0;
        this.bankAccount = "";
    }
    public IdentityInfo(String address, String email, int hotline, String bankAccount) {   //constructor for Personal
        this.taxCode = null;
        this.name = null;
        this.address = address;
        this.email = email;
        this.hotline = hotline;
        this.bankAccount = bankAccount;
    }
    public IdentityInfo() {};

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
