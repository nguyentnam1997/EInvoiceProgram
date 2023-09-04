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
    @Getter @Setter
    private boolean isActive;

    public InvoiceTemplate(String templateSeries, int templateType) {
        this.templateSerial = templateType + "C23T" + templateSeries;
        this.templateName = getTemplateType(templateType);
        this.templateType = templateType;
        this.isActive = true;
    }
    public String getTemplateType(int input) {
        if (input == 1) return "VAT Invoice";
        else if (input == 2) return "Sales Invoice";
        else return null;
    }
    public String getActiveStatus() {
        if (isActive) return "ACTIVE";
        else return "INACTIVE";
    }
}
