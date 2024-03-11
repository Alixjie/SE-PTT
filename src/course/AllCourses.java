package course;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import system.*;


public class AllCourses {
    private List<Course> courses;



    public AllCourses() {
        String String = DataWithFile.getInstance().read("Data/Courses.txt");
        String[] CoursesString = String.split("\n");
        courses = Arrays.stream(CoursesString)
                .map(Course::fromString)
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
    public void saveAllCourses() {
        String content = courses.stream()
                .map(Course::toString)
                .collect(Collectors.joining("\n"));
        try {
            DataWithFile.getInstance().write("Data/Courses.txt", content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
