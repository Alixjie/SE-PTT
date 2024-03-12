package system.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import roles.CourseDirector;
import system.DataWithFile;

public class DataCourseDIrectors {
    private List<CourseDirector> CourseDirectors;

    public DataCourseDIrectors() {
        String data = DataWithFile.getInstance().read("Data/CourseDirectors.txt");
        String[] courseDirectorsString = data.split("\n");
        CourseDirectors = Arrays.stream(courseDirectorsString)
                .map(DataCourseDIrectors::StringToCourseDirector)
                .collect(Collectors.toList());
    }

    public CourseDirector findCourseDirectorByID(String id) {
        for (CourseDirector courseDirector : CourseDirectors) {
            if (courseDirector.getId().equals(id)) {
                return courseDirector;
            }
        }
        return null;
    }

    public void showAllCourseDirectors(){
        for (CourseDirector courseDirector : CourseDirectors) {
            System.out.println("Course Director ID: " + courseDirector.getId());
            System.out.println("Course Director Name: " + courseDirector.getName());
            System.out.println("Courses: " + (courseDirector.getCourseList() != null ? String.join(", ", courseDirector.getCourseList()) : "N/A"));
            System.out.println();
        }
    }

    public void saveAllCourseDirectors() throws IOException {
        String content = CourseDirectors.stream()
                .map(DataCourseDIrectors::CourseDirectortoString)
                .collect(Collectors.joining("\n"));
        DataWithFile.getInstance().write("Data/CourseDirectors.txt", content);
    }

    public void syncCourseUpdateDirector(String courseid,String oldDirectorId, String newDirectorId) {
        if (!oldDirectorId.equals(newDirectorId)) {
            CourseDirector oldDirector = findCourseDirectorByID(oldDirectorId);
            CourseDirector newDirector = findCourseDirectorByID(newDirectorId);
            if (oldDirector != null) {
                oldDirector.removeCourse(courseid);
            }
            if (newDirector != null) {
                newDirector.addCourse(courseid);
            }
        }
    }

    public static CourseDirector StringToCourseDirector(String String) {
        String[] parts = String.split(",");
        String id = parts[0];
        String name = parts[1];
        List<String> Courses;
        if (parts.length > 2 && !parts[2].equals("null")) {
            Courses = Arrays.asList(parts[2].split(";"));
        } else {
            Courses = new ArrayList<>();
        }
        return new CourseDirector(id, name, Courses);
    }

    public static String CourseDirectortoString(CourseDirector courseDirector) {
        String coursesString = (courseDirector.getCourseList() == null || courseDirector.getCourseList().isEmpty()) ? "null" : String.join(";", courseDirector.getCourseList());
        return courseDirector.getId() + "," + courseDirector.getName()  + "," +  coursesString;
    }
}