package system.Data;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import roles.Admin;
import system.*;

public class DataAdmins {
    private List<Admin> admins;

    public DataAdmins() {
        String data = DataWithFile.getInstance().read("Data/Admins.txt");
        String[] adminsString = data.split("\n");
        admins = Arrays.stream(adminsString)
                .map(DataAdmins::StringToAdmin)
                .collect(Collectors.toList());
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void removeAdmin(Admin admin) {
        admins.remove(admin);
    }

    public Admin findAdminByID(String id) {
        for (Admin admin : admins) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    public void showAllAdmins() {
        for (Admin admin : admins) {
            System.out.println("Admin ID: " + admin.getId());
            System.out.println("Admin Name: " + admin.getName());
            System.out.println();
        }
    }

    public void saveAllAdmins() throws IOException {
        String content = admins.stream()
                .map(DataAdmins::AdminToString)
                .collect(Collectors.joining("\n"));
        DataWithFile.getInstance().write("Data/Admins.txt", content);
    }

    public static Admin StringToAdmin(String adminString) {
        String[] parts = adminString.split(",");
        String id = parts[0];
        String name = parts[1];
        return new Admin(id, name);
    }

    public static String AdminToString(Admin admin) {
        return admin.getId() + "," + admin.getName();
    }
}