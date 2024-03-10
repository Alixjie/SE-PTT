package roles;

import java.util.Set;

/**
 * Filename: Coursedirector.java
 * Author: Charles Chen
 * Date: 2024-03-09
 * version: 1.0
 * description: 
 */
public class CourseDirector extends Staff{

    private static final Set<String> CourseDirector_PERMISSIONS = Set.of
    ("modify_releated_course_requirement",
    "view_course_requirement"
        // Add more permissions here
    );

    public CourseDirector(String id, String name, String password) {
        super(id, name, "CourseDirector", password);
        this.permissions.addAll(CourseDirector_PERMISSIONS);
    }
    
}
