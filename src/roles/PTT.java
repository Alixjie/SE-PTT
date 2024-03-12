package roles;

import static system.Constants.roles.RolePTT;

public class PTT extends Staff {
    public PTT(String id, String name) {
        super(id, name,RolePTT);
    }

    @Override
    public void showFunctionality() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showFunctionality'");
    }

}