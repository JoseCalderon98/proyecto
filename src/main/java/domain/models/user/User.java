package domain.models.user;

public class User {
    private int userId;
    private String relatedId;
    private String fullName;
    private String identification;
    private String email;
    private String phone;
    private String address;
    private UserRole systemRole;
    private UserStatus userStatus;
    private String username;
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public UserRole getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(UserRole systemRole) {
        this.systemRole = systemRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
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

    public void validate() {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is mandatory");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (phone == null || !phone.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Phone must be between 7 and 15 digits");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address is mandatory");
        }
        if (systemRole == null) {
            throw new IllegalArgumentException("System role is mandatory");
        }
        if (userStatus == null) {
            throw new IllegalArgumentException("User status is mandatory");
        }
        if (password == null || password.trim().isEmpty() || password.length() < 6) {
            throw new IllegalArgumentException("Password is mandatory and must be at least 6 characters long");
        }
    }
}
