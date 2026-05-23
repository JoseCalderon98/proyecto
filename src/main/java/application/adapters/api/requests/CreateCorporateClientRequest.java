package application.adapters.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateCorporateClientRequest {

    @NotBlank(message = "El nombre completo del representante es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras y espacios")
    private String fullName;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^[0-9]{6,15}$", message = "La identificación debe contener solo números y tener entre 6 y 15 dígitos")
    private String identification;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(min = 2, max = 150, message = "El nombre de la empresa debe tener entre 2 y 150 caracteres")
    private String companyName;

    @NotBlank(message = "El NIT es obligatorio")
    @Pattern(regexp = "^[0-9]{9,15}(-[0-9])?$", message = "El NIT debe ser numérico de 9 a 15 dígitos, opcionalmente con dígito de verificación (ej: 900123456-1)")
    private String taxId;

    @NotBlank(message = "El ID del representante legal es obligatorio")
    @Pattern(regexp = "^[0-9]{6,15}$", message = "El ID del representante legal debe contener solo números y tener entre 6 y 15 dígitos")
    private String legalRepresentativeId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }
}
