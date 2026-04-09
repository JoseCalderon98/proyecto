package domain.models.user;

import domain.models.client.Client;

public class User {
    private int userId;
    private Client client;
    private String relatedId;
    private String fullName;
    private String identification;
    private String email;
    private String phone;
    private String address;
    private UserRole systemRole;
    private UserStatus userStatus;
    private String username;

    public User(int userId, Client client, String relatedId, String fullName, String identification, String email, String phone, String address, UserRole systemRole, UserStatus userStatus, String username) {
        this.userId = userId;
        this.client = client;
        this.relatedId = relatedId;
        this.fullName = fullName;
        this.identification = identification;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.systemRole = systemRole;
        this.userStatus = userStatus;
        this.username = username;
    }
    
    public User() {
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public String getRelatedId() { return relatedId; }
    public void setRelatedId(String relatedId) { this.relatedId = relatedId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public UserRole getSystemRole() { return systemRole; }
    public void setSystemRole(UserRole systemRole) { this.systemRole = systemRole; }

    public UserStatus getUserStatus() { return userStatus; }
    public void setUserStatus(UserStatus userStatus) { this.userStatus = userStatus; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}

