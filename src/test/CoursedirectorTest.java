//import org.junit.Test;
//import static org.junit.Assert.*;
package test;
import roles.CourseDirector;
import system.LoginSession;
import course.Course;
public class CoursedirectorTest {
    /*
    @Test
    public void testGetCourseInfo() {
        CourseDirector director = new CourseDirector("1", "Director", "password");

        Course course = new Course("Course 1", "1");

        Session session = new Session(director);

        course.getCourseInfo(session);

    }
    */

    public static void main(String[] args) {
        LoginSession session = null;
        CourseDirector director = new CourseDirector("2", "Director", "password");
        Course course = new Course("Course 1", "1");
        try {
        session = new LoginSession(director,"password");} 
        catch (Exception e) {
            e.printStackTrace();    }
        course.getCourseInfo(session);
        
        try {
        course.updateRequirement(session, "New Requirement");
        } catch (Exception e) {
        e.printStackTrace();    }

        course.getCourseInfo(session);

        course.setDirectorId("2");
        
        try {
            course.updateRequirement(session, "New Requirement");
            } catch (Exception e) {
            e.printStackTrace();    }
        course.getCourseInfo(session);

        
    }
}


