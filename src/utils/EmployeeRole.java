/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ACER
 */
public class EmployeeRole {

    public enum Role {
        DEVERLOPER,
        TESTER,
        MANAGER,
        HR;
    };

    //Developer – Tester – Manager – HR
    public static String getStringRole(EmployeeRole.Role role) {
        String str = "";
        switch (role) {
            case DEVERLOPER:
                str = "Deverloper";
                break;
            case TESTER:
                str = "Tester";
                break;
            case MANAGER:
                str = "Manager";
                break;
            case HR:
                str = "HR";
                break;
        }
        return str;
    }

    public static EmployeeRole.Role getRole(String role) {
        if (role.equalsIgnoreCase("Deverloper")) {
            return Role.DEVERLOPER;
        } else if (role.equalsIgnoreCase("Tester")) {
            return Role.TESTER;
        } else if (role.equalsIgnoreCase("Manager")) {
            return Role.MANAGER;
        }else if (role.equalsIgnoreCase("Hr")) {
            return Role.HR;
        } else {
            return Role.HR;
        }
    }

    public static void printRoleMenu() {
        Role[] roles = Role.values(); // Lấy ra mảng các giá trị trong Enum
        System.out.println("--- Select Employee Role ---");
        for (int i = 0; i < roles.length; i++) {
            // In ra dạng: 1. Developer, 2. Tester,...
            System.out.println((i + 1) + ". " + getStringRole(roles[i]));
        }
        System.out.println("----------------------------");
    }

    /**
     * Hàm trả về Role tương ứng với số người dùng chọn (1 -> DEVERLOPER, 2 -> TESTER,...)
     */
    public static EmployeeRole.Role getRoleByChoice(int choice) {
        Role[] roles = Role.values();
        // Kiểm tra nếu số nhập nằm ngoài phạm vi (ví dụ nhập 5, 6, 0...)
        if (choice < 1 || choice > roles.length) {
            return null; 
        }
        return roles[choice - 1]; // Trả về role tương ứng (vì mảng chạy từ 0 nên lấy choice - 1)
    }
}   
