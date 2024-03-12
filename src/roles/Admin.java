package roles;

import static system.Constants.roles.RoleAdmin;

public class Admin extends Staff {
    public Admin(String id, String name) {
        super(id, name, RoleAdmin);
    }

    @Override
    public void showFunctionality() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showFunctionality'");
    }

}