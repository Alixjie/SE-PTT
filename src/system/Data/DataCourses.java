package system.Data;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import course.Course;
import system.*;


public class DataCourses {
    private List<Course> courses;



    public DataCourses() {
        String String = DataWithFile.getInstance().read("Data/Courses.txt");
        String[] CoursesString = String.split("\n");
        courses = Arrays.stream(CoursesString)
                .map(DataCourses::StringToCourse)
                .collect(Collectors.toList());

    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public Course findCourseByID(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public void showAllCourses() {
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getId());
            System.out.println("Course Name: " + course.getName());
            System.out.println("Course Director: " + course.getDirectorId());
            System.out.println();
        }
    }

    //TODO: add error handles
    public void saveAllCourses() throws IOException {
        String content = courses.stream()
                .map(DataCourses::CoursetoString)
                .collect(Collectors.joining("\n"));
            DataWithFile.getInstance().write("Data/Courses.txt", content);
        
    }

    public static Course StringToCourse(String courseString) {
        String[] parts = courseString.split(",");
        String id = parts[0];
        String name = parts[1];
        String directorId = parts[2];
        String requirement = parts[3];
        List<String> teacherIds = Arrays.asList(parts[4].split(";"));
        return new Course(id, name,directorId,requirement,teacherIds);
    }

    public static String CoursetoString(Course course) {
        return course.getId() + "," + course.getName() + "," + String.join(";", course.getTeacherIds()) + "," + String.join(";", course.getTeacherIds());
    }
}
