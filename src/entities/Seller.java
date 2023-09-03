package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
public class Seller extends IdentityInfo {
    @Getter @Setter
    private Map<String, User> users;
    @Getter @Setter
    private List<Invoice> invoices;
    @Getter @Setter
    private List<Customer> customers;

   /* public Seller(int taxCode, String name, String address, String email, int hotline, String bankAccount) {
        super(taxCode, name, address, email, hotline, bankAccount);
    }*/

    public Seller(int taxCode, String name, String address, String email, int hotline, String bankAccount, Map<String, User> users) {
        super(taxCode, name, address, email, hotline, bankAccount);
        this.users = users;
        this.invoices = null;
        this.customers = null;
    }

    @Override
    public String toString() {
        return "Seller{" + super.toString() +
                '}';
    }
}
