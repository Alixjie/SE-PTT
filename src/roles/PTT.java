package roles;

import static system.Constants.roles.RolePTT;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import course.AllCourses;
import course.Course;
import course.training;
import system.Main;

public class PTT extends Staff {

    List<String> courseList;
    List<String> FinishedTrainning;

    public PTT(String id) {
        super(id);
    }

    public PTT(String id, String name) {
        super(id, name,RolePTT);
    }

    public PTT(String id, String name, List<String> courseList, List<String> finishedTrainning) {
        super(id, name,RolePTT);
        this.courseList = courseList;
        FinishedTrainning = finishedTrainning;
    }

    public List<String> getcourseList() {
        return courseList;
    }

    public void setCoursecourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    public List<String> getFinishedTrainning() {
        return FinishedTrainning;
    }

    public void setFinishedTrainning(List<String> finishedTrainning) {
        FinishedTrainning = finishedTrainning;
    }

    public void addCourse(String courseId){
        if (!this.courseList.contains(courseId)) {
            this.courseList.add(courseId);}
    }

    public void removeCourse(String courseId){
        if (this.courseList.contains(courseId)) {
            this.courseList.remove(courseId);}
    }

    public void addFinishedTrainning(String trainingname){
        if (!this.FinishedTrainning.contains(trainingname)) {
            this.FinishedTrainning.add(trainingname);}
    }

    public void removeFinishedTrainning(String trainingname){
        if (this.FinishedTrainning.contains(trainingname)) {
            this.FinishedTrainning.remove(trainingname);}
    }

    private void showselfinfo() {
        System.out.println("Your ID: " + this.getId());
        System.out.println("Your name: " + this.getName());
        System.out.println("Your role: " + this.getrole());
        if (this.courseList != null) {
            System.out.println("Your courses: " + String.join(", ", this.courseList));
        } else {
            System.out.println("Your courses: N/A");
        }
        if (this.FinishedTrainning != null) {
            System.out.println("Your finished trainings: " + String.join(", ", this.FinishedTrainning));
        } else {
            System.out.println("Your finished trainings: N/A");
        }
    }


    @Override
    public void showFunctionality() {
        AllCourses allCourses = new AllCourses();
        Alltrainings alltrainings = new Alltrainings();
        AllPTTs allPTTs = new AllPTTs();
        PTT selfPTT = allPTTs.findPTTByID(this.getId());       

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("You can choose the function by number");
            System.out.println("1. View self info");
            System.out.println("2. list all courses");
            System.out.println("3. list your courses");
            System.out.println("4. list all trainings");
            System.out.println("5. select a specific course");
            System.out.println("6. select a specific training");
            System.out.println("...");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    selfPTT.showselfinfo();
                    break;
                case 2:
                    allCourses.showAllCourses();
                    break;
                case 3:
                    if (selfPTT.courseList != null) {
                        List<String> courseNames = new ArrayList<>();
                        for (String courseId : selfPTT.courseList) {
                            Course course = allCourses.findCourseByID(courseId);
                            if (course != null) {
                                courseNames.add(courseId + " - " + course.getName());
                            } else {
                                courseNames.add(courseId + " - N/A");
                            }
                        }
                        System.out.println("Your courses: " + String.join(", ", courseNames));
                    } else {
                        System.out.println("Your courses: N/A");
                    }
                    break;
                case 4:
                    alltrainings.showAllTrainings();
                    break;
                case 5:
                    System.out.println("Please input the course ID");
                    String courseId = scanner.next();
                    Course course = allCourses.findCourseByID(courseId);
                    if (course != null) {
                        course.Functionality(selfPTT.getrole(),scanner);
                    } else {
                        System.out.println("Course not found");
                    }
                    break;
                case 6:
                    System.out.println("Please input the training ID");
                    String trainingId = scanner.next();
                    training training = alltrainings.findTrainingByID(trainingId);
                    if (training != null) {
                        training.Functionality(selfPTT.getrole(),scanner);
                    } else {
                        System.out.println("Training not found");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 4.");
                }
        } while (choice != 0);

        //PTT will not save any data
        scanner.close();
        Main.goBackToMain();
    }

}