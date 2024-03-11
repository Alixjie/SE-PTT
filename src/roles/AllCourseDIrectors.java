package roles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import system.DataWithFile;

public class AllCourseDIrectors {
    private List<CourseDirector> CourseDirectors;

    public AllCourseDIrectors() {
        String data = DataWithFile.getInstance().read("Data/CourseDirectors.txt");
        String[] courseDirectorsString = data.split("\n");
        CourseDirectors = Arrays.stream(courseDirectorsString)
                .map(CourseDirector::fromString)
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
}
