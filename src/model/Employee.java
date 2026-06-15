/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import utils.EmployeeRole;

/**
 *
 * @author ACER
 */
public class Employee {
    private String id;
    private String name;
    private EmployeeRole.Role role;
    private double baseSalary;
    private int workingDays;
    private double bonus;
    private String status;

    public Employee(String id, String name, EmployeeRole.Role role, double baseSalary, int workingDays, double bonus) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
        this.workingDays = workingDays;
        this.bonus = bonus;
    }

    public Employee(String id, String name, EmployeeRole.Role role, double baseSalary, int workingDays, double bonus, String status) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
        this.workingDays = workingDays;
        this.bonus = bonus;
        this.status = status;
    }

    //getter
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeeRole.Role getRole() {
        return role;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public double getBonus() {
        return bonus;
    }

    public String getStatus() {
        return status.toUpperCase();
    }
    
    //setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(EmployeeRole.Role role) {
        this.role = role;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    //calculate
    public double calSalary(){
        return baseSalary * workingDays + bonus;
    }
}
