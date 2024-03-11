import java.util.Scanner;

public abstract class Staff {
    private int id;
    private String name;
    private String position;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }
}
