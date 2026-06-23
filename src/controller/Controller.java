/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import files.EmployeeFile;
import files.IFileReadWrite;
import java.util.ArrayList;
import java.util.List;
import model.Employee;
import service.EmployeeService;
import utils.EmployeeRole;
import utils.Inputer;
import utils.Validator;
import view.View;

/**
 *
 * @author Hiu
 */
public class Controller {
    private Inputer input;
    private View view;
    private EmployeeService empList;

    public Controller(View view) {
        this.view = view;
        
        IFileReadWrite<Employee> empFileHandler = new EmployeeFile();
        this.empList = new EmployeeService(empFileHandler);
        
        this.input = new Inputer(this.view, this.empList);
    }

    public void loadEmp() {
        this.empList.loadDataFromFile();
        view.setEmployeeService(this.empList);
    }

    public void addEmp() {
        view.showMessage("\n===== New Employee =====");
        String id = input.enterId();
        String name = input.enterName();
        EmployeeRole.Role role = input.enterRole();
        double basicSal = input.enterBaseSal();
        int workingDay = input.enterWorkingDay();
        double bonus = input.enterBonus();
        String active = input.enterStatus();

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
                                emp.setName(input.enterName());
                                view.showMessage(">> Update Name success!!!");
                                break;
                            case 2:
                                emp.setRole(input.enterRole());
                                view.showMessage(">> Update Role success!!!");
                                break;
                            case 3:
                                emp.setBaseSalary(input.enterBaseSal());
                                view.showMessage(">> Update Base Salary success!!!");
                                break;
                            case 4:
                                emp.setWorkingDays(input.enterWorkingDay());
                                view.showMessage(">> Update Working Days success!!!");
                                break;
                            case 5:
                                emp.setBonus(input.enterBonus());
                                view.showMessage(">> Update Bonus success!!!");
                                break;
                            case 6:
                                emp.setStatus(input.enterStatus());
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

        boolean ischange = false;
        if (list.isEmpty()) {
            view.showMessage("\n>> No Employee have registered yet");
        } else {
            List<Employee> searchResult = new ArrayList<>();
            String values;
            while (!ischange) {
                try {
                    int choice = view.showSearchMenu();
                    switch (choice) {
                        case 1:
                            values = view.readString("\nEnter Employee Id: ");
                            searchResult.clear();
                            for (Employee emp : list) {
                                if (emp.getId().toLowerCase().contains(values.toLowerCase())) {
                                    searchResult.add(emp);
                                }
                            }
                            break;
                        case 2:
                            values = view.readString("\nEnter Employee Name: ");
                            for (Employee emp : list) {
                                if (emp.getName().toLowerCase().contains(values.toLowerCase())) {
                                    searchResult.add(emp);
                                }
                            }
                            break;
                        case 3:
                            EmployeeRole.Role searchRole = input.enterRole();
                            for (Employee emp : list) {
                                if (emp.getRole() == searchRole) {
                                    searchResult.add(emp);
                                }
                            }
                            break;
                        case 4:
                            values = view.readString("\nEnter Employee Status: ");
                            for (Employee emp : list) {
                                if (emp.getStatus().equalsIgnoreCase(values)) {
                                    searchResult.add(emp);
                                }
                            }
                            break;
                        case 5:
                            ischange = true;
                            break;
                        default:
                            view.showMessage(">> This function is not available");
                    }
                    if (!searchResult.isEmpty()) {
                        view.showList(searchResult);
                    } else {
                        if (ischange) {
                            break;
                        }
                        view.showMessage(">> No one matches the search criteria!");
                    }
                } catch (Exception e) {
                    view.showMessage(">> Invalid input. Please try again.");
                }
            }
        }
    }

    public void calculateEmp() {
        List<Employee> list = empList.getAll();

        for (Employee emp : list) {
            double calSalary = emp.calSalary(emp.getBaseSalary(), emp.getWorkingDays(), emp.getBonus());
            emp.setSalary(calSalary);
        }
        view.showSalary(list);
    }

    public void saveData() {
        if (empList == null) {
            System.out.println(">> Nothing to save! The registration list is empty.");
            return;
        }
        empList.save();
    }

    public void displayEmp() {
        List<Employee> list = empList.getAll();
        if (list.isEmpty()) {
            view.showMessage("\n>> No employee have registered yet");
        } else {
            view.showList(list);
        }
    }
    
    public void exitSystem(boolean isChange) {
        if (isChange) {
            String response = view.readString("Do you want to save the changes before exiting? (Y/N): ");
            if (response.equalsIgnoreCase("Y")) {
                saveData();
                view.showMessage("Data saved. Goodbye!");
                System.exit(0);
            } else if (response.equalsIgnoreCase("N")) {
                String confirm = view.readString("You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
                if (confirm.equalsIgnoreCase("Y")) {
                    view.showMessage("Goodbye!");
                    System.exit(0);
                }
            }
        } else {
            view.showMessage("Goodbye!");
            System.exit(0);
        }
    }            
}
