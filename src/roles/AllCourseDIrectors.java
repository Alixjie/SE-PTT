package roles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public void syncCourseUpdatePTT(String courseid,Set<String> oldPTTIds, Set<String> newPTTIds) {
        Set<String> addedPTTIds = new HashSet<>(newPTTIds);
        addedPTTIds.removeAll(oldPTTIds);

        Set<String> removedPTTIds = new HashSet<>(oldPTTIds);
        removedPTTIds.removeAll(newPTTIds);

        for (String pttId : addedPTTIds) {
            PTT ptt = this.findPTTByID(pttId);
            if (ptt != null) {
                ptt.addCourse(courseid);
            }
        }

        for (String pttId : removedPTTIds) {
            PTT ptt = this.findPTTByID(pttId);
            if (ptt != null) {
                ptt.removeCourse(courseid);
            }
        }
    }

    public void syncTrainingUpdatePTT(String trainingName,Set<String> oldPTTIds, Set<String> newPTTIds) {
        Set<String> addedPTTIds = new HashSet<>(newPTTIds);
        addedPTTIds.removeAll(oldPTTIds);

        Set<String> removedPTTIds = new HashSet<>(oldPTTIds);
        removedPTTIds.removeAll(newPTTIds);

        for (String pttId : addedPTTIds) {
            PTT ptt = this.findPTTByID(pttId);
            if (ptt != null) {
                ptt.addTraining(trainingName);
            }
        }

        for (String pttId : removedPTTIds) {
            PTT ptt = this.findPTTByID(pttId);
            if (ptt != null) {
                ptt.removeTraining(trainingName);
            }
        }
    }
}