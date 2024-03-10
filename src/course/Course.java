package course;
import java.util.List;

import roles.CourseDirector;
import roles.Staff;
import roles.SystemAdmin;
import system.LoginSession;

public class Course {
    private String id;
    private String name;
    private String directorId;
    private String requirement;
    private List<String> teacherIds;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirectorId() {
        return directorId;
    }

    public String getRequirement() {
        return requirement;
    }

    public List<String> getTeacherIds() {
        return teacherIds;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public void updateRequirement(LoginSession session, String newRequirement) {
    if (session.getPermissions().contains("modify_all_course_requirement") || 
    (session.getPermissions().contains("modify_releated_course_requirement") && this.directorId.equals(session.getUser().getId())))
    {
        this.requirement = newRequirement;
    } else {
        throw new IllegalArgumentException("Insufficient permissions to update the course requirement.");
    }
    }

    public void getCourseInfo(LoginSession session) {
        System.out.println("Course ID: " + this.id);
        System.out.println("Course Name: " + this.name);
        System.out.println("Director ID: " + (this.directorId != null ? this.directorId : "N/A"));

        if (session.getPermissions().contains("view_course_requirement")) {
            System.out.println("Requirement: " + (this.requirement != null ? this.requirement : "N/A"));
        }

        System.out.println("Teacher IDs: " + (this.teacherIds != null ? String.join(", ", this.teacherIds) : "N/A"));
    }



    public void setTeacherIds(List<String> teacherIds) {
        this.teacherIds = teacherIds;
    }
}