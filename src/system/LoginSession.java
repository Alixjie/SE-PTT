package system;

import roles.Staff;
import java.util.Set;

public class LoginSession {
    private Staff user;
    private Set<String> permissions;

    public LoginSession(Staff user, String password) throws Exception {
        if (!user.checkPassword(password)) {
            throw new Exception("Invalid password");
        }
        this.user = user;
        this.permissions = user.getPermissions();
    }

    public Staff getUser() {
        return user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}