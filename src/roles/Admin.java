package roles;

public class Admin extends Staff {
    public Admin(String id, String name, String password) {
        super(id, name, "Admin", password);
    }

    // 可以添加一些特定于Admin的属性和方法
}