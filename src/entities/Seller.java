package entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
public class Seller extends IdentityInfo {
    @Getter @Setter
    private int invoiceQuantity;
    @Getter @Setter
    private Map<String, Staff> staffs;
    @Getter @Setter
    private List<Invoice> invoiceList;

    public Seller(int taxCode, String name, String address, String email, int phoneNumber, String bankAccount, int invoiceQuantity, Map<String, Staff> staffs) {
        super(taxCode, name, address, email, phoneNumber, bankAccount);
        this.invoiceQuantity = invoiceQuantity;
        this.staffs = staffs;
        this.invoiceList = null;
    }
}
