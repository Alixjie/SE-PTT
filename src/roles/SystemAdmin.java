package roles;

import system.Constants;

public class SystemAdmin extends Staff {

    public SystemAdmin(String id, String name) {
        super(id, name, Constants.roles.valueOf("RoleSystemAdmin"));
    }

    @Override
    public void showFunctionality() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showFunctionality'");
    }

}