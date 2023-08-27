package entities;

import lombok.Getter;
import lombok.Setter;

public class InvoiceTemplate {
    @Getter @Setter
    private String templateSerial;
    @Getter @Setter
    private String templateName;
    @Getter @Setter
    private int templateType;

    public InvoiceTemplate(String templateSerial, String templateName, int templateType) {
        this.templateSerial = templateSerial;
        this.templateName = templateName;
        this.templateType = templateType;
    }

}
