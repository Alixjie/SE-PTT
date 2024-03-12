package course;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import system.Constants.roles;

import static system.Constants.roles.*;

public class Course {
    private String id;
    private String name;
    private String directorId;
    private String requirement;
    private List<String> teacherIds;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course(String id, String name, String directorId, String requirement, List<String> teacherIds) {
        this.id = id;
        this.name = name;
        this.directorId = directorId;
        this.requirement = requirement;
        this.teacherIds = teacherIds;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirectorId() {
        return directorId;
    }

    public String getRequirement() {
        return requirement;
    }

    public List<String> getTeacherIds() {
        return teacherIds;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public void updateRequirement(String newRequirement) {
        this.requirement = newRequirement;
    }

    public void showCourseInfo() {
        System.out.println("Course ID: " + this.id);
        System.out.println("Course Name: " + this.name);
        System.out.println("Director ID: " + (this.directorId != null ? this.directorId : "N/A"));
        System.out.println("Requirement: " + (this.requirement != null ? this.requirement : "N/A"));
        System.out.println("Teacher IDs: " + (this.teacherIds != null ? String.join(", ", this.teacherIds) : "N/A"));
    }



    public void setTeacherIds(List<String> teacherIds) {
        this.teacherIds = teacherIds;
    }



    public void Functionality(roles roles, Scanner scanner) {
        this.showCourseInfo();
        if (roles == RoleCourseDirector ) {
            CourseDirectorFunctionality(scanner);
        } else if (roles == RoleAdmin) {
            AdminFunctionality(scanner);
        } else if (roles == RolePTT) {
            TeacherFunctionality(scanner);
        }
    }

    private void CourseDirectorFunctionality(Scanner scanner){
        int choice;
        do {
            System.out.println("You can choose the function by number");
            System.out.println("1. change requirement");
            System.out.println("0. exit course page");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice){
                case 1:
                    System.out.println("Enter a requirement: ");
                    String requirement = scanner.nextLine();
                    this.updateRequirement(requirement);
                    System.out.println("requirement has been updated");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 1.");
            }
        } while (choice != 0);
    }

    private void AdminFunctionality(Scanner scanner){
        int choice;
        do {
            System.out.println("You can choose the function by number");
            System.out.println("1. set course director");
            System.out.println("2. set course teachers");
            System.out.println("0. exit course page");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice){
                case 1:
                    System.out.println("Current Course Director is: " + (this.directorId != null ? this.directorId : "N/A"));
                    System.out.println("Enter a new director ID: ");
                    String Directorid = scanner.nextLine();
                    this.setDirectorId(Directorid);
                    System.out.println("Director id has been updated");
                    break;
                case 2:
                    System.out.println("Please input the new teachers IDs, separated by spaces");
                    String newteacjerid = scanner.nextLine();
                    List<String> newteacerids = Arrays.asList(newteacjerid.split(" "));
                    this.setTeacherIds(newteacerids);
                    System.out.println("Teachers have been updated");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 2.");
            }
        } while (choice != 0);
    }

    private void TeacherFunctionality(Scanner scanner){
        int choice;
        do {
            System.out.println("You can choose the function by number");
            System.out.println("1. view course info");
            System.out.println("0. exit");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice){
                case 1:
                    this.showCourseInfo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 1.");
            }
        } while (choice != 0);
    }
    
}