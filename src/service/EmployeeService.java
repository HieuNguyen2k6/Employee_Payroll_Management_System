/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import files.EmployeeFile;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

/**
 *
 * @author ACER
 */
public class EmployeeService {
    private ArrayList<Employee> empList;
    private EmployeeFile empFile;

    public EmployeeService(EmployeeFile empFile) {
        this.empFile = empFile;
        this.empList = empFile.loadFile();
    }
    
    public void add(Employee emp){
        empList.add(emp);
    }
    
    public List<Employee> getAll() {
        return empList;
    }
    
    public Employee findById(String code) {
        for (Employee s : empList) {
            if (s.getId().equalsIgnoreCase(code)) {
                return s;
            }
        }
        return null;
    }
}
