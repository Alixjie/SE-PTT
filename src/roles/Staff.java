package roles;

import java.util.HashSet;
import java.util.Set;

public class Staff {
    private String id;
    private String name;
    private String role;
    private String password;
    protected Set<String> permissions;

    private static final Set<String> STAFF_PERMISSIONS = Set.of("get_course_info");

    public Staff(String id, String name, String role, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.permissions = new HashSet<>();
        this.permissions.addAll(STAFF_PERMISSIONS);
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Set<String> getPermissions() {
        return permissions;
    }
}