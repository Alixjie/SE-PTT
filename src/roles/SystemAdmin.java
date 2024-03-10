package roles;

import java.util.Set;

public class SystemAdmin extends Staff {

    private static final Set<String> SystemAdmin_PERMISSIONS = Set.of
    ("modify_all_course_requirement",
    "view_course_requirement"
        // Add more permissions here
    );

    public SystemAdmin(String id, String name, String password) {
        super(id, name, "SystemAdmin", password);
        this.permissions.addAll(SystemAdmin_PERMISSIONS);
    }

}