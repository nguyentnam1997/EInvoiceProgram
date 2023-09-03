package entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Setter(AccessLevel.NONE)
public class InvoiceTemplate {
    private String templateSerial;
    private String templateName;
    private int templateType;

    public InvoiceTemplate(String templateSerial, int templateType) {
        this.templateSerial = templateType + "C23T" + templateSerial;
        this.templateName = getTemplateType(templateType);
        this.templateType = templateType;
    }
    public String getTemplateType(int input) {
        if (input == 1) return "VAT Invoice";
        else if (input == 2) return "Sales Invoice";
        else return null;
    }
}
