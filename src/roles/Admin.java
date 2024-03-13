package roles;

import static system.Constants.roles.RoleAdmin;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import course.*;
import system.Main;
import system.Data.*;

public class Admin extends Staff {

    public Admin(String id) {
        super(id);
    }

    public Admin(String id, String name) {
        super(id, name, RoleAdmin);
    }

    @Override
    public void showFunctionality() {
        DataCourses allCourses = new DataCourses();
        DataTrainings alltrainings = new DataTrainings();
        DataCourseDIrectors allCourseDirectors = new DataCourseDIrectors();
        DataPTTs allPTTs = new DataPTTs();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println();
            System.out.println("------------------ main page------------------");
            System.out.println("You can choose the function by number");
            System.out.println("1. list all courses");
            System.out.println("2. list all trainings");
            System.out.println("3. list all course directors");
            System.out.println("4. list all PTTs");
            System.out.println("5. select a specific course(you can update it here)");
            System.out.println("6. select a specific training(you can update it here)");
            System.out.println("7. add a new PTT");
            System.out.println("8. update a PTT");
            System.out.println("9. delete a PTT");
            System.out.println("10. add a new training");
            System.out.println("...");
            System.out.println("0. Exit");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 10.");
                scanner.next(); // discard the non-integer input
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    allCourses.showAllCourses();
                    break;
                case 2:
                    alltrainings.showAllTrainings();
                    break;
                case 3:
                    allCourseDirectors.showAllCourseDirectors();
                    break;
                case 4:
                    allPTTs.showAllPTTs();
                    break;
                case 5:{
                    System.out.println("Please input the course ID");
                    String courseId = scanner.next();
                    Course course = allCourses.findCourseByID(courseId);
                    if (course != null) {

                        String oldDirectorId = course.getDirectorId();
                        Set<String> oldTeacherId = new HashSet<>(course.getTeacherIds());
                        
                        course.Functionality(RoleAdmin,scanner);

                        String newDirectorId = course.getDirectorId();
                        Set<String> newTeacherId = new HashSet<>(course.getTeacherIds());

                        allPTTs.syncCourseUpdatePTT(course.getId(),oldTeacherId, newTeacherId);
                        allCourseDirectors.syncCourseUpdateDirector(course.getId(),oldDirectorId, newDirectorId);
                        
                    } else {
                        System.out.println("Course not found");
                    }}
                    break;
                case 6:{
                    System.out.println("Please input the training ID");
                    String trainingId = scanner.next();
                    Training training = alltrainings.findTrainingByID(trainingId);
                    if (training != null) {
                        Set<String> oldPTTIds = new HashSet<>(training.getParticiantIDs());
                        training.Functionality(RoleAdmin,scanner);
                        Set<String> newPTTsIds = new HashSet<>(training.getParticiantIDs());
                        allPTTs.syncTrainingUpdatePTT(training.getName(),oldPTTIds, newPTTsIds);
                    } else {
                        System.out.println("Training not found");
                    }}
                    break;
                case 7:{
                    System.out.println("Please input the PTT ID");
                    String PTTId = scanner.next();
                    PTT PTT = allPTTs.findPTTByID(PTTId);
                    if (PTT != null) {
                        System.out.println("PTT already exists");
                    } else {
                        System.out.println("Please input the PTT name");
                        String PTTName = scanner.next();
                        allPTTs.addPTT(PTTId, PTTName);
                        System.out.println("PTT added");
                    }}
                    break;
                case 8:{
                    System.out.println("Please input the PTT ID");
                    String PTTId = scanner.next();
                    PTT PTT = allPTTs.findPTTByID(PTTId);
                    if (PTT != null) {
                        System.out.println("Please input the course IDs to add to course list, separated by spaces");
                        String courseIds = scanner.nextLine();
                        List<String> newCourseList = Arrays.asList(courseIds.split(" "));
                        PTT.setCoursecourseList(newCourseList);

                        System.out.println("Please input the course IDs to add to finished training list, separated by spaces");
                        String finishedCourseIds = scanner.nextLine();
                        List<String> newFinishedTrainning = Arrays.asList(finishedCourseIds.split(" "));
                        PTT.setFinishedTrainning(newFinishedTrainning);
                        System.out.println("PTT updated");
                    } else {
                        System.out.println("PTT not found");
                    }}
                    break;
                case 9:{
                    System.out.println("Please input the PTT ID");
                    String PTTId = scanner.next();
                    PTT PTT = allPTTs.findPTTByID(PTTId);
                    if (PTT != null) {
                        allPTTs.removePTT(PTT);
                        System.out.println("PTT deleted");
                    } else {
                        System.out.println("PTT not found");
                    }}
                    break;
                case 10:{
                    System.out.println("Please input the training ID");
                    String trainingId = scanner.next();
                    Training training = alltrainings.findTrainingByID(trainingId);
                    if (training != null) {
                        System.out.println("Training already exists");
                    } else {
                        System.out.println("Please input the training name");
                        String trainingName = scanner.next();
                        alltrainings.addTraining(trainingId, trainingName);
                        System.out.println("Training added");
                    }}
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 10.");
                }
        } while (choice != 0);

   
        try {
            allCourses.saveAllCourses();
            allCourseDirectors.saveAllCourseDirectors();
            allPTTs.saveAllPTTs();
            alltrainings.saveAllTrainings();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        scanner.close();
        Main.goBackToMain();
    }

}