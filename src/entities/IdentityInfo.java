package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class IdentityInfo {
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private static int autoId;

    @Setter(AccessLevel.NONE)
    private int identityId;
    private int taxCode;
    private String name;
    private String address;
    private String email;
    private int phoneNumber;
    private String bankAccount;

    public IdentityInfo(int taxCode, String name, String address, String email, int phoneNumber, String bankAccount) {
        this.identityId = ++autoId;
        this.taxCode = taxCode;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
    }
}
