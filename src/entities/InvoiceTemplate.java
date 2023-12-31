package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter(AccessLevel.NONE)
public class InvoiceTemplate {
    private String templateSerial;
    private final String templateName = "VAT Invoice";
    @Getter @Setter
    private boolean isActive;

    public InvoiceTemplate(String templateSeries) {
        this.templateSerial = "1C23T" + templateSeries.toUpperCase();
        this.isActive = true;
    }

    public String getActiveStatus() {
        if (isActive) return "ACTIVE";
        else return "INACTIVE";
    }
}
