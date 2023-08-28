package entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InvoiceTemplate {
    private String templateSerial;
    private String templateName;
    private int templateType;

    public InvoiceTemplate(String templateSerial, String templateName, int templateType) {
        this.templateSerial = templateSerial;
        this.templateName = templateName;
        this.templateType = templateType;
    }

}
