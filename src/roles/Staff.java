package roles;

import system.Constants.roles;

public abstract class Staff {
    private String id;
    private String name;
    private roles role;

    public Staff(String id) {
        this.id = id;
    }

    public Staff(String id, String name, roles role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
//    public void functionOne() {
//    }
//    public void functionTwo() {
//    }

    public abstract void showFunctionality();
    // System.out.println("You can choose the function by number");
    // System.out.println("1. FunctionOne");
    // System.out.println("2. FunctionTwo");
    // System.out.println("...");
    // System.out.println("n. Exit");
//    Scanner in = new Scanner(System.in);
//    int choice = in.nextInt();
//    switch (choice){
//        case 1:
//            FunctionOne();
//            break;
//        case 2:
//            FunctionOne();
//            break;
//        case n:
//            Main.goBackToMain();
//    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setrole(roles role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public roles getrole() {
        return role;
    }
}
