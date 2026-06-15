/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import files.EmployeeFile;
import java.util.List;
import model.Employee;
import service.EmployeeService;
import utils.EmployeeRole;
import utils.Validator;
import view.View;

/**
 *
 * @author Hiu
 */
public class Controller {

    private View view;
    private EmployeeService empList;
    private boolean isChanged = false;

    public Controller(View view) {
        this.view = view;
        // Khởi tạo luôn empList để tránh lỗi rỗng khi chưa bấm chức năng 1
        this.empList = new EmployeeService(new EmployeeFile());
    }

    public void loadEmp() {
        this.empList = new EmployeeService(new EmployeeFile());
        view.setEmployeeService(this.empList);
    }

    public String enterId() {
        String id;
        while (true) {
            id = view.readString("Enter Student Id: ");
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
            if (name.isEmpty()) {
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

    public void addEmp() {
        view.showMessage("\n===== New Employee =====");
        String id = enterId();
        String name = enterName();
        EmployeeRole.Role role = enterRole();
        double basicSal = enterBaseSal();
        int workingDay = enterWorkingDay();
        double bonus = enterBonus();
        String active = enterStatus();
        
        empList.add(new Employee(id, name, role, basicSal, workingDay, bonus, active));
        view.showMessage(">> Add Employee successful!");
    }
    
    public void displayEmp(){
        List<Employee> list = empList.getAll();
        if (list.isEmpty()) {
            view.showMessage("\n>> No students have registered yet");
        } else {
            view.showList(list);
        }
    }
}
