import java.util.ArrayList;

public class UserControl {
    private ArrayList user;

    public ArrayList getUser() {
        return user;
    }

    public void userAdd(String userDebri) {
        String[] userInfo = userDebri.split(",");
        if (user == null)
            user = new ArrayList();
        user.add(new User(userInfo[0], userInfo[1], Integer.parseInt(userInfo[2])));
    }

    public int scanUser() {
        String userString = DataWithFile.getInstance().read("/Users/george/Project/JavaProjects/SE-PTT/src/user.txt");
        if (userString.isEmpty()) {
            System.out.println("There is no users!");
            return 0;
        } else {
            String[] userDebris = userString.split("\n");
            for (String userDebri : userDebris) {
                System.out.println(userDebri);
                userAdd(userDebri);
            }
            return 1;
        }
    }
}

