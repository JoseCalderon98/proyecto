package domain.user;

public class SystemRole {
    private int roleId;
    private String description;

    public SystemRole(int roleId, String description) {
        this.roleId = roleId;
        this.description = description;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
