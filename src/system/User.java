package system;
import system.Constants.roles;

public class User {
    private String ID;
    private String Password;
    private roles Type;

    public User(String ID, String password, roles type) {
        setID(ID);
        setPassword(password);
        setType(type);
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public roles getType() {
        return this.Type;
    }

    public void setType(roles type) {
        this.Type = type;
    }
}
