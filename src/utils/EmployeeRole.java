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
                str = "Developer";
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
        if (role.equals("Developer")) {
            return Role.DEVERLOPER;
        } else if (role.equals("Tester")) {
            return Role.TESTER;
        } else if (role.equals("Manager")) {
            return Role.MANAGER;
        } else {
            return Role.HR;
        }
    }

    public static String[] getListRole() {
        String[] listRole = {"Developer", 
            "Tester", 
            "Manager", 
            "HR"};
        return listRole;
    }
}   
