package entities;
import lombok.Getter;
import lombok.Setter;

public class Staff extends Account {
    @Getter @Setter
    private int sellerTaxCode;
    @Getter @Setter
    private boolean isAdmin;

    public Staff(String username, String password, int sellerTaxCode, boolean isAdmin) {
        super(username, password);
        this.sellerTaxCode = sellerTaxCode;
        this.isAdmin = isAdmin;
    }
}
