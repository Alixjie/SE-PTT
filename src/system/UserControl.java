package system;
import java.util.ArrayList;

public class UserControl {
    private ArrayList<User> user;

    public ArrayList<User> getUser() {
        return user;
    }

    public void userAdd(String userDebri) {
        String[] userInfo = userDebri.split(",");
        if (user == null)
            user = new ArrayList<>();
        try {
            user.add(new User(userInfo[0], userInfo[1], Constants.roles.valueOf(userInfo[2])));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role: " + userInfo[2]);
        }
    }

    public int scanUser() {
        String userString = DataWithFile.getInstance().read("Data/user.txt");
        if (userString.isEmpty()) {
            System.out.println("There is no users!");
            return 0;
        } else {
            String[] userDebris = userString.split("\n");
            for (String userDebri : userDebris) {
                //System.out.println(userDebri);
                userAdd(userDebri);
            }
            return 1;
        }
    }
}

