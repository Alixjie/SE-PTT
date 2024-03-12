package system.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import roles.PTT;
import system.*;

public class DataPTTs {
    private List<PTT> pttList;

    public DataPTTs() {
        String data = DataWithFile.getInstance().read("Data/PTTs.txt");
        String[] pttString = data.split("\n");
        pttList = Arrays.stream(pttString)
                .map(DataPTTs::StringToPTT)
                .collect(Collectors.toList());
    }

    public void addPTT(String id, String name){
        pttList.add(new PTT(id, name));
    }
    public void addPTT(PTT ptt) {
        pttList.add(ptt);
    }

    public void removePTT(PTT ptt) {
        pttList.remove(ptt);
    }

    public PTT findPTTByID(String id) {
        for (PTT ptt : pttList) {
            if (ptt.getId().equals(id)) {
                return ptt;
            }
        }
        return null;
    }

    public void showAllPTTs() {
        for (PTT ptt : pttList) {
            System.out.println("PTT ID: " + ptt.getId());
            System.out.println("PTT Name: " + ptt.getName());
            System.out.println("Course List: " + String.join(", ", ptt.getCourseList()));
            System.out.println();
        }
    }

    public void saveAllPTTs() throws IOException {
        String content = pttList.stream()
                .map(DataPTTs::PTTToString)
                .collect(Collectors.joining("\n"));
        DataWithFile.getInstance().write("Data/PTTs.txt", content);
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
                ptt.addFinishedTrainning(trainingName);
            }
        }

        for (String pttId : removedPTTIds) {
            PTT ptt = this.findPTTByID(pttId);
            if (ptt != null) {
                ptt.removeFinishedTrainning(trainingName);
            }
        }
    }

    public static PTT StringToPTT(String pttString) {
        String[] parts = pttString.split(",");
        String id = parts[0];
        String name = parts[1];
        List<String> courseList;
        if (parts.length > 2 && !parts[2].equals("null")) {
            courseList = Arrays.asList(parts[2].split(";"));
        } else {
            courseList = new ArrayList<>();
        }
        List<String> finishedTraining;
        if (parts.length > 3 && !parts[3].equals("null")) {
            finishedTraining = Arrays.asList(parts[3].split(";"));
        } else {
            finishedTraining = new ArrayList<>();
        }
        return new PTT(id, name, courseList, finishedTraining);
    }

    public static String PTTToString(PTT ptt) {
        String courseListString = (ptt.getCourseList() == null || ptt.getCourseList().isEmpty()) ? "null" : String.join(";", ptt.getCourseList());
        String finishedTrainingString = (ptt.getFinishedTrainings() == null || ptt.getFinishedTrainings().isEmpty()) ? "null" : String.join(";", ptt.getFinishedTrainings());
        return ptt.getId() + "," + ptt.getName() + "," + courseListString + "," + finishedTrainingString;
    }
}