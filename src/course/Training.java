package course;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import system.Constants.roles;

import static system.Constants.roles.*;


public class Training {
    String uuid;
    String name;
    String teacher;
    LocalDate date;
    List<String> ParticiantIDs;

    public Training(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public Training(String uuid, String name, String teacher, LocalDate date, List<String> particiantIDs) {
        this.uuid = uuid;
        this.name = name;
        this.teacher = teacher;
        this.date = date;
        ParticiantIDs = particiantIDs;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<String> getParticiantIDs() {
        return ParticiantIDs;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setParticiantIDs(List<String> particiantIDs) {
        ParticiantIDs = particiantIDs;
    }

    public void showTrainingInfo() {
        System.out.println("Training ID: " + this.getUuid());
        System.out.println("Training name: " + this.getName());
        System.out.println("Training teacher: " + (this.getTeacher() != null ? this.getTeacher() : "N/A"));
        System.out.println("Training date: " + (this.getDate() != null ? this.getDate().toString() : "N/A"));
        if (this.ParticiantIDs != null) {
            System.out.println("Training participants: " + String.join(", ", this.ParticiantIDs));
        } else {
            System.out.println("Training participants: N/A");
        }
    }

    public void updatetraining(String newteacher, LocalDate newdate, List<String> newParticiantIDs) {
        this.teacher = newteacher;
        this.date = newdate;
        this.ParticiantIDs = newParticiantIDs;
    }

    public void Functionality(roles roles, Scanner scanner) {
    this.showTrainingInfo();
    if (roles == RoleAdmin) {
        AdminFunctionality(scanner);
    } else if (roles == RolePTT) {
        TeacherFunctionality(scanner);}
    }

    private void AdminFunctionality(Scanner scanner) {
        int choice;
        do {
            System.out.println();
            System.out.println("------------------ training page------------------");
            System.out.println("You can choose the function by number");
            System.out.println("1. view training info");
            System.out.println("2. update training info");
            System.out.println("0. exit course page");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 10.");
                scanner.next(); // discard the non-integer input
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice){
                case 1:
                    this.showTrainingInfo();
                    break;
                case 2:
                    scanner.nextLine(); // consume newline left-over
                    System.out.println("Enter new training teacher:");
                    String newTeacher = scanner.nextLine();

                    System.out.println("Enter new training date (format: YYYY-MM-DD):");
                    String dateString = scanner.nextLine();
                    LocalDate newDate = LocalDate.parse(dateString);

                    System.out.println("Enter new participant IDs (sapce separated):");
                    String participantIdsString = scanner.nextLine();
                    List<String> newParticipantIds = Arrays.asList(participantIdsString.split(" "));

                    this.updatetraining(newTeacher, newDate, newParticipantIds);
                    System.out.println("training have been updated");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 0 and 2.");
            }
        } while (choice != 0);
    }

    private void TeacherFunctionality(Scanner scanner) {
        int choice;
        do{
        System.out.println();
        System.out.println("------------------ training page------------------");
        System.out.println("You can choose the function by number");
        System.out.println("1. view training info");
        System.out.println("0. exit");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 0 and 10.");
            scanner.next(); // discard the non-integer input
        }
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over
        switch (choice){
            case 1:
                this.showTrainingInfo();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid input. Please enter a number between 0 and 1.");
        }
        } while (choice != 0);
    }
    }
