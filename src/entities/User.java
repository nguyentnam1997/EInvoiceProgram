package entities;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class User {
    @Setter(AccessLevel.NONE)
    private String username;
    private String password;
    private String email;
    private boolean isAdmin;
    private boolean isActive;
    private int sellerTaxCode;
    private List<Invoice> userInvoiceList;

    public User(String username, String password, String email, boolean isAdmin, int sellerTaxCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
        this.isActive = true;
        this.sellerTaxCode = sellerTaxCode;
        this.userInvoiceList = null;
    }

    public User() {

    }
    public String getPermission() {
        if (isAdmin) return "ADMIN";
        else return "MEMBER";
    }
    public String getActiveStatus() {
        if (isActive) return "ACTIVE";
        else return "INACTIVE";
    }
}
