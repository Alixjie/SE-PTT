import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class UserControl {
    private ArrayList User;
    public int scanUser() {
        String userString = DataWithFile.getInstance().read("/Users/george/Project/JavaProjects/SE-PTT/src/user.txt");
        if (userString ) {
            System.out.println("There is no users!");
            return 0;
        }
        else {
            String[] userDebris = userString.split("\\|");
            System.out.println(userDebris);
            return 1;
        }
    }
}
