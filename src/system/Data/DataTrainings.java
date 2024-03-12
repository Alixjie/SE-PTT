package system.Data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import system.DataWithFile;
import course.Training;

public class DataTrainings {
    private List<Training> trainings;
    

    public DataTrainings() {
        String String = DataWithFile.getInstance().read("Data/Trainings.txt");
        String[] TraingingString = String.split("\n");
        trainings = Arrays.stream(TraingingString)
                .map(DataTrainings::StringToTraining)
                .collect(Collectors.toList());
    }

    public void saveAllTrainings() throws IOException {
        String content = trainings.stream()
                .map(DataTrainings::TrainingtoString)
                .collect(Collectors.joining("\n"));
        DataWithFile.getInstance().write("Data/Trainings.txt", content);
    }

    public void addTraining(String id, String name) {
        trainings.add(new Training(id, name));
    }
    public void addTraining(Training training) {
        trainings.add(training);
    }

    public void removeTraining(Training training) {
        trainings.remove(training);
    }

    public Training findTrainingByID(String id) {
        for (Training training : trainings) {
            if (training.getUuid().equals(id)) {
                return training;
            }
        }
        return null;
    }

    public void showAllTrainings() {
        for (Training training : trainings) {
            System.out.println("Training ID: " + training.getUuid());
            System.out.println("Training Name: " + training.getName());
            System.out.println("Training Teacher: " + training.getTeacher());
            System.out.println("Training Date: " + training.getDate());
            System.out.println("Training Participants: " + String.join(", ", training.getParticiantIDs().toString()));
            System.out.println();
        }
    }

    public static Training StringToTraining(String trainingString) {
        String[] parts = trainingString.split(",");
        String uuid = parts[0];
        String name = parts[1];
        String teacher = parts.length > 2 && !parts[2].equals("null") ? parts[2] : "";
        LocalDate date = parts.length > 3 && !parts[3].equals("null") ? LocalDate.parse(parts[3]) : LocalDate.now();
        List<String> ParticiantIDs;
        if (parts.length > 4 && !parts[4].equals("null")) {
            ParticiantIDs = Arrays.asList(parts[4].split(";"));
        } else {
            ParticiantIDs = new ArrayList<>();
        }
        return new Training(uuid, name,teacher,date,ParticiantIDs);
    }

    public static String TrainingtoString(Training training) {
        String teacherString = training.getTeacher() != null ? training.getTeacher() : "null";
        String dateString = training.getDate() != null ? training.getDate().toString() : "null";
        String participantIDsString = (training.getParticiantIDs() != null && !training.getParticiantIDs().isEmpty()) ? String.join(";", training.getParticiantIDs()) : "null";
        return training.getUuid() + "," + training.getName() + "," + teacherString + "," + dateString + "," + participantIDsString;
    }
}
