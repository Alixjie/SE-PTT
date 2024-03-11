import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        UserControl userControl = new UserControl();
        if (userControl.scanUser() == 0) {
            System.out.println("Please sign up first(Administrator)");
            System.out.println("Please input the Username");
            Scanner in = new Scanner(System.in);
            String username = in.nextLine();
            System.out.println("Please input the Password");
            String password = in.nextLine();
            userControl.userAdd(username + "," + password + "," + "0");
            System.out.println("Create Admin");
        }
        else {
            System.out.println("Please input the Username");
            Scanner in = new Scanner(System.in);
            String username = in.nextLine();
            System.out.println("Please input the Password");
            String password = in.nextLine();
            int type = -1;
            for (int i = 0; i < userControl.getUser().size(); i++) {
                User tempUser = (User) userControl.getUser().get(i);
                if (username.equals(tempUser.getName())) {
                    if (password.equals(tempUser.getPassword())) {
                        type = tempUser.getType();
                        break;
                    }
                }

            }
            switch (type) {
                case 0:
                    System.out.println("Create Admin");
                    break;
                case 1:
                    System.out.println("Create Course Director");
                    break;
                default:
                    System.out.println("Username or Password Error!");
                    break;
            }
        }

    }

    public static void goBackToMain() {
        System.out.println("Bye!");
        System.exit(0);
    }
}