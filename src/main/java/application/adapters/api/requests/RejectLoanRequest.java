package application.adapters.api.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RejectLoanRequest {

    @NotNull(message = "El ID de usuario es obligatorio")
    @Min(value = 1, message = "El ID de usuario debe ser válido")
    private Integer userId;

    @NotBlank(message = "El rol de usuario es obligatorio")
    @Pattern(regexp = "^(NATURAL_CLIENT|ENTERPRISE_CLIENT|TELLER|COMMERCIAL_EXECUTIVE|ENTERPRISE_EMPLOYEE|ENTERPRISE_SUPERVISOR|INTERNAL_ANALYST)$",
             message = "Rol no válido. Roles permitidos: NATURAL_CLIENT, ENTERPRISE_CLIENT, TELLER, COMMERCIAL_EXECUTIVE, ENTERPRISE_EMPLOYEE, ENTERPRISE_SUPERVISOR, INTERNAL_ANALYST")
    private String userRole;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
