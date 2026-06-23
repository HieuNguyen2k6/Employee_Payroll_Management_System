/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import service.EmployeeService;
import view.View;

/**
 *
 * @author ACER
 */
public class Inputer {
    private View view;
    private EmployeeService empList;
    
    public Inputer(View view, EmployeeService empList) {
        this.view = view;
        this.empList = empList;
    }
    
    public String enterId() {
        String id;
        while (true) {
            id = view.readString("Enter Employee Id: ");
            id = id.toUpperCase().trim();
            if (!id.isEmpty()) {
                if (Validator.validEmpId(id)) {
                    if (empList.findById(id) == null) {
                        break;
                    }
                    view.showMessage(">> ID already exists!");
                } else {
                    view.showMessage(">> Invalid! ID format (E001)!!!");
                }
            } else {
                view.showMessage(">> ID cannot be empty!!!");
            }
        }
        return id;
    }

    public String enterName() {
        String name;
        while (true) {
            name = view.readString("Enter Name: ");
            if (!Validator.validString(name)) {
                view.showMessage(">> Name cannot empty!!!");
            } else {
                break;
            }
        }
        return name;
    }

    public EmployeeRole.Role enterRole() {
        int choice;
        EmployeeRole.Role selectedRole = null;

        do {
            try {
                // 1. In menu các Role (1. Developer, 2. Tester...)
                EmployeeRole.printRoleMenu();

                // 2. Đọc số người dùng nhập
                choice = view.readChoice("Enter your choice (1-4): ");

                // 3. Chuyển số thành Enum tương ứng
                selectedRole = EmployeeRole.getRoleByChoice(choice);

                if (selectedRole == null) {
                    view.showMessage(">> Invalid choice! Please select from 1 to 4.");
                }
            } catch (Exception e) {
                view.showMessage(">> Please enter a valid number!");
                choice = 0; // Gán lại để vòng lặp tiếp tục chạy
            }
        } while (selectedRole == null); // Lặp lại nếu chọn sai số

        return selectedRole;
    }

    public double enterBaseSal() {
        double basicSal = view.readDouble("Enter Basic Salary: ");
        return basicSal;
    }

    public int enterWorkingDay() {
        int workingDay = view.readInt("Enter Working Days: ");
        return workingDay;
    }

    public double enterBonus() {
        double bonus = view.readDouble("Enter Bonus: ");
        return bonus;
    }

    public String enterStatus() {
        int choice;
        do {
            choice = view.showStatusMenu();
        } while (choice > 2);
        if (choice == 1) {
            return "Active";
        } else {
            return "Inactive";
        }
    }
}
