public class User {
    private String Name;
    private String Password;
    private int Type;

    public void User(String name, String password, int type) {
        setName(name);
        setPassword(password);
        setType(type);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
