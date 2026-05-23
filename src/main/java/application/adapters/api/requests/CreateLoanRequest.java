package application.adapters.api.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public class CreateLoanRequest {

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    @Pattern(regexp = "^(PERSONAL|MORTGAGE|AUTO|BUSINESS)$", message = "Tipo de préstamo no válido. Tipos permitidos: PERSONAL, MORTGAGE, AUTO, BUSINESS")
    private String loanType;

    @NotBlank(message = "El ID del cliente solicitante es obligatorio")
    @Pattern(regexp = "^[0-9]{6,15}$", message = "El ID del cliente debe contener solo números y tener entre 6 y 15 dígitos")
    private String applicantClientId;

    @NotNull(message = "El monto solicitado es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto solicitado debe ser mayor a cero")
    @DecimalMax(value = "999999999.99", message = "El monto solicitado excede el límite permitido")
    private BigDecimal requestedAmount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.01", message = "La tasa de interés debe ser mayor a cero")
    @DecimalMax(value = "100.00", message = "La tasa de interés no puede ser mayor al 100%")
    private BigDecimal interestRate;

    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo debe ser de al menos 1 mes")
    @Max(value = 360, message = "El plazo no puede superar los 360 meses (30 años)")
    private Integer termMonths;

    @NotBlank(message = "La cuenta de destino para el desembolso es obligatoria")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "La cuenta de destino debe contener solo números y tener entre 10 y 20 dígitos")
    private String destinationAccountForDisbursement;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getApplicantClientId() {
        return applicantClientId;
    }

    public void setApplicantClientId(String applicantClientId) {
        this.applicantClientId = applicantClientId;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public String getDestinationAccountForDisbursement() {
        return destinationAccountForDisbursement;
    }

    public void setDestinationAccountForDisbursement(String destinationAccountForDisbursement) {
        this.destinationAccountForDisbursement = destinationAccountForDisbursement;
    }
}
