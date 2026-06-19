/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.List;
import java.util.Scanner;
import model.Employee;
import service.EmployeeService;

/**
 *
 * @author Hiu
 */
public class View {

    private final Scanner sc;
    private EmployeeService empList;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public void setEmployeeService(EmployeeService empList) {
        this.empList = empList;
    }

    public int showMenu() {
        System.out.println("\n===== Employee Payroll Management System =====");
        System.out.println("1. Load employee data from file");
        System.out.println("2. Add a new employee");
        System.out.println("3. Update employee information");
        System.out.println("4. Remove an employee by ID");
        System.out.println("5. Search employees by attribute");
        System.out.println("6. Calculate monthly payroll");
        System.out.println("7. Display employee list");
        System.out.println("8. Save data to file");
        System.out.println("9. Quit program");
        System.out.println("-------------------------------------------");
        System.out.print("Your Choice: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int showStatusMenu() {
        System.out.println("--- Active ---");
        System.out.println("1. Active");
        System.out.println("2. Inactive");
        System.out.println("--------------");
        System.out.print("Your Choice: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int showUpdateMenu() {
        System.out.println("\n===== Employee Update =====");
        System.out.println("1. Name");
        System.out.println("2. Role");
        System.out.println("3. Base Salary");
        System.out.println("4. Working Days");
        System.out.println("5. Bonus");
        System.out.println("6. Status");
        System.out.println("7. Quit program");
        System.out.println("-------------------------------");
        System.out.print("Your Choice: ");
        return Integer.parseInt(sc.nextLine());
    }

    public int showSearchMenu() {
        System.out.println("\n===== Employee Search =====");
        System.out.println("1. Id");
        System.out.println("2. Name");
        System.out.println("3. Role");
        System.out.println("4. Status");
        System.out.println("5. Quit program");
        System.out.println("-------------------------------");
        System.out.print("Your Choice: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void showList(List<Employee> list) {
        showMessage("\n======================================= Display Employee List =======================================");
        System.out.println("--------------------------------------------------------------------------------    ---------------------");
        System.out.printf("%-10s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s \n", "ID", "Name", "Role", "Base Salary", "Working Days", "Bonus", "Status");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for (Employee s : list) {
            System.out.printf("%-10s | %-20s | %-10s | %,-11.0f | %-12d | %,-10.0f | %-10s \n", s.getId(), s.getName(), s.getRole(), s.getBaseSalary(), s.getWorkingDays(), s.getBonus(), s.getStatus());
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public void showSalary(List<Employee> list) {
        showMessage("\n======================================== Display Employee List ========================================");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-10s | %-10s | %-10s | %-10s | %-10s\n", "ID", "Name", "Role", "Base Salary", "Working Days", "Bonus", "Total Salary");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (Employee s : list) {
            System.out.printf("%-10s | %-20s | %-10s | %,-11.0f | %-12d | %,-10.0f |%,-10.0f\n", s.getId(), s.getName(), s.getRole(), s.getBaseSalary(), s.getWorkingDays(), s.getBonus(), s.getSalary());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                // Nếu nhập vào thì parse sang double
                if (Double.parseDouble(input) > 0.0) {
                    return Double.parseDouble(input);
                } else {
                    showMessage(">> Must be a positive number!!!");
                }

            } catch (NumberFormatException e) {
                System.out.println(">> Invalid number, try again.");
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = sc.nextLine().trim();
                // Nếu nhập vào thì parse sang double
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) <= 26) {
                    return Integer.parseInt(input);
                } else {
                    showMessage(">> Number must be Range: 0 – 26!!!");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Invalid number, try again.");
            }
        }
    }

    public int readChoice(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        return Integer.parseInt(input);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
