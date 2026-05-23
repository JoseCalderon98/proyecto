package application.adapters.api.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateBankAccountRequest {

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "El número de cuenta debe contener solo números y tener entre 10 y 20 dígitos")
    private String accountNumber;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "^(SAVINGS|CHECKING|SALARY)$",
             message = "Tipo de cuenta no válido. Tipos permitidos: SAVINGS, CHECKING, SALARY")
    private String accountType;

    @NotBlank(message = "El ID del titular es obligatorio")
    @Pattern(regexp = "^[0-9]{6,15}$", message = "El ID del titular debe contener solo números y tener entre 6 y 15 dígitos")
    private String holderId;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.00", inclusive = true, message = "El saldo inicial no puede ser negativo")
    @DecimalMax(value = "999999999.99", message = "El saldo inicial excede el límite permitido")
    private java.math.BigDecimal initialBalance;

    @NotBlank(message = "La moneda es obligatoria")
    @Pattern(regexp = "^(USD|EUR|COP)$",
             message = "Moneda no válida. Monedas permitidas: USD, EUR, COP")
    private String currency;

    @DecimalMin(value = "0.00", inclusive = true, message = "El límite de sobregiro no puede ser negativo")
    @DecimalMax(value = "999999999.99", message = "El límite de sobregiro excede el límite permitido")
    private java.math.BigDecimal overdraftLimit;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public java.math.BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(java.math.BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public java.math.BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(java.math.BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
