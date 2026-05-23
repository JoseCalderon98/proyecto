package application.adapters.api.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateTransferRequest {

    @NotBlank(message = "La cuenta de origen es obligatoria")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "La cuenta de origen debe contener solo números y tener entre 10 y 20 dígitos")
    private String originAccount;

    @NotBlank(message = "La cuenta de destino es obligatoria")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "La cuenta de destino debe contener solo números y tener entre 10 y 20 dígitos")
    private String destinationAccount;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto de la transferencia debe ser mayor a cero")
    @DecimalMax(value = "999999999.99", message = "El monto de la transferencia excede el límite permitido")
    private BigDecimal amount;

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
