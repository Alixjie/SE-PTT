package system;

import java.io.IOException;
import java.util.Scanner;

import roles.*;
import system.Constants.roles;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        UserControl userControl = new UserControl();
        if (userControl.scanUser() == 0) {
            System.out.println("Please sign up first(Administrator)");
            System.out.println("Please input the User ID");
            Scanner in = new Scanner(System.in);
            String userID = in.nextLine();
            System.out.println("Please input the Password");
            String password = in.nextLine();
            userControl.userAdd(userID + "," + password + "," + roles.RoleAdmin.toString() + "\n");
            System.out.println("Create Admin");
        }
        else {
            System.out.println("Please input the UserID");
            Scanner in = new Scanner(System.in);
            String userID = in.nextLine();
            System.out.println("Please input the Password");
            String password = in.nextLine();
            roles type = roles.Roleinvalid;
            for (int i = 0; i < userControl.getUser().size(); i++) {
                User tempUser = (User) userControl.getUser().get(i);
                if (userID.equals(tempUser.getID())) {
                    if (password.equals(tempUser.getPassword())) {
                        type = tempUser.getType();
                        break;
                    }
                }

            }
            switch (type) {
                case RoleAdmin:
                    System.out.println("User Role: Admin");
                    Admin admin = new Admin(userID);
                    admin.showFunctionality();
                    break;
                case RoleCourseDirector:
                    System.out.println("User Role: Course Director");
                    CourseDirector courseDirector = new CourseDirector(userID);
                    courseDirector.showFunctionality();
                    break;
                case RolePTT:
                    System.out.println("User Role: Part Time Teacher");
                    PTT ptt = new PTT(userID);
                    ptt.showFunctionality();
                    break;
                default:
                    System.out.println("User ID or Password Error!");
                    break;
            }
        }

    }

    public static void goBackToMain() {
        System.out.println("Bye!");
        System.exit(0);
    }
}