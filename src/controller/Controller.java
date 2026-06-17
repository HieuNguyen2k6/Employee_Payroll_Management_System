/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import files.EmployeeFile;
import java.util.ArrayList;
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
        this.empList.loadDataFromFile();
        view.setEmployeeService(this.empList);
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

    public void updateEmp() {
        String id;
        boolean update = false;
        id = view.readString("\nEnter Employee Id to update: ");
        if (Validator.validEmpId(id)) {
            if (empList.findById(id) != null) {
                Employee emp = empList.findById(id);
                while (!update) {
                    try {
                        System.out.println("\n===== Employee Information =====");
                        System.out.println("ID           : " + emp.getId());
                        System.out.println("Name         : " + emp.getName());
                        System.out.println("Role         : " + emp.getRole());
                        System.out.println("Base Salary  : " + String.format("%.0f", emp.getBaseSalary()));
                        System.out.println("Working Days : " + emp.getWorkingDays());
                        System.out.println("Bonus        : " + String.format("%,.0f", emp.getBonus()));
                        System.out.println("Status       : " + emp.getStatus());
                        System.out.println("-------------------------------");
                        int choice = view.showUpdateMenu();
                        switch (choice) {
                            case 1:
                                emp.setName(enterName());
                                view.showMessage(">> Update Name success!!!");
                                break;
                            case 2:
                                emp.setRole(enterRole());
                                view.showMessage(">> Update Role success!!!");
                                break;
                            case 3:
                                emp.setBaseSalary(enterBaseSal());
                                view.showMessage(">> Update Base Salary success!!!");
                                break;
                            case 4:
                                emp.setWorkingDays(enterWorkingDay());
                                view.showMessage(">> Update Working Days success!!!");
                                break;
                            case 5:
                                emp.setBonus(enterBonus());
                                view.showMessage(">> Update Bonus success!!!");
                                break;
                            case 6:
                                emp.setStatus(enterStatus());
                                view.showMessage(">> Update Status success!!!");
                                break;
                            case 7:
                                update = true;
                                break;
                            default:
                                view.showMessage(">> This function is not available");
                        }
                    } catch (Exception e) {
                        view.showMessage(">> Invalid input. Please try again.");
                    }
                }
            } else {
                view.showMessage(">> This employee has not registered yet.");
            }
        } else {
            view.showMessage(">> Invalid! ID format (E001)!!!");
        }
    }

    public void removeEmp() {
        String id;
        id = view.readString("\nEnter Employee Id to remove: ");
        if (Validator.validEmpId(id)) {
            if (empList.findById(id) != null) {
                Employee emp = empList.findById(id);
                System.out.println("\n===== Employee Information =====");
                System.out.println("ID           : " + emp.getId());
                System.out.println("Name         : " + emp.getName());
                System.out.println("Role         : " + emp.getRole());
                System.out.println("Base Salary  : " + String.format("%.0f", emp.getBaseSalary()));
                System.out.println("Working Days : " + emp.getWorkingDays());
                System.out.println("Bonus        : " + String.format("%,.0f", emp.getBonus()));
                System.out.println("Status       : " + emp.getStatus());
                System.out.println("-------------------------------");

                String confirm = view.readString("Are you sure you want to delete this registration? (Y/N): ");
                if (confirm.equalsIgnoreCase("Y")) {
                    empList.delete(emp);
                    view.showMessage(">> Deleted successfully!");
                } else {
                    view.showMessage(">> Delete canceled.");
                }
            } else {
                view.showMessage(">> This employee has not registered yet.");
            }
        } else {
            view.showMessage(">> Invalid! ID format (E001)!!!");
        }
    }

    public void searchEmp() {
        List<Employee> list = empList.getAll();

        if (list.isEmpty()) {
            view.showMessage("\n>> No Employee have registered yet");
        } else {
            List<Employee> searchResult = new ArrayList<>();
            String values;

            int choice = view.showSearchMenu();
            switch (choice) {
                case 1:
                    values = view.readString("\nEnter Employee Name: ");
                    for (Employee emp : list) {
                        if (emp.getName().toLowerCase().contains(values.toLowerCase())) {
                            searchResult.add(emp);
                        }
                    }
                    break;
                case 2:
                    EmployeeRole.Role searchRole = enterRole();
                    for (Employee emp : list) {
                        if (emp.getRole() == searchRole) {
                            searchResult.add(emp);
                        }
                    }
                    break;
                case 3:
                    values = view.readString("\nEnter Employee Status: ");
                    for (Employee emp : list) {
                        if (emp.getStatus().toLowerCase().contains(values.toLowerCase())) {
                            searchResult.add(emp);
                        }
                    }
                    break;
                default:
                    view.showMessage(">> This function is not available");
            }

            if (!searchResult.isEmpty()) {
                view.showList(searchResult);
            } else {
                view.showMessage(">> No one matches the search criteria!");
            }
        }
    }

    public void displayEmp() {
        List<Employee> list = empList.getAll();
        if (list.isEmpty()) {
            view.showMessage("\n>> No employee have registered yet");
        } else {
            view.showList(list);
        }
    }
}
