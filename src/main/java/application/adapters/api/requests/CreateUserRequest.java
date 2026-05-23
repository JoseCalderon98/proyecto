package application.adapters.api.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    private String relatedId;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras y espacios")
    private String fullName;

    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "^[0-9]{6,15}$", message = "La identificación debe contener solo números y tener entre 6 y 15 dígitos")
    private String identification;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "El formato de correo electrónico no es válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo números y tener entre 7 y 15 dígitos")
    private String phone;

    @NotBlank(message = "La dirección es obligatoria")
    private String address;

    @NotBlank(message = "El rol del sistema es obligatorio")
    @Pattern(regexp = "^(NATURAL_CLIENT|ENTERPRISE_CLIENT|TELLER|COMMERCIAL_EXECUTIVE|ENTERPRISE_EMPLOYEE|ENTERPRISE_SUPERVISOR|INTERNAL_ANALYST)$", message = "Rol no válido. Roles permitidos: NATURAL_CLIENT, ENTERPRISE_CLIENT, TELLER, COMMERCIAL_EXECUTIVE, ENTERPRISE_EMPLOYEE, ENTERPRISE_SUPERVISOR, INTERNAL_ANALYST")
    private String systemRole;

    @NotBlank(message = "El estado del usuario es obligatorio")
    @Pattern(regexp = "^(ACTIVE|INACTIVE|BLOCKED)$", message = "Estado no válido. Valores permitidos: ACTIVE, INACTIVE, BLOCKED")
    private String userStatus;

    @NotBlank(message = "El nombre de usuario (username) es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    public String getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(String relatedId) {
        this.relatedId = relatedId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
