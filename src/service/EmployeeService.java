/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import files.IFileReadWrite;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

/**
 *
 * @author ACER
 */
public class EmployeeService {

    private List<Employee> empList;
    private IFileReadWrite<Employee> empFile;

    public EmployeeService(IFileReadWrite<Employee> empFile) {
        this.empFile = empFile;
        this.empList = new ArrayList<>();
    }
    
    public void loadDataFromFile() {
        this.empList = empFile.loadFromFile();
    }

    public void add(Employee emp) {
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

    public boolean delete(Employee emp) {
        if (emp != null) {
            return empList.remove(emp);
        }
        return false;
    }

    public void save() {
        empFile.saveToFile(empList);
    }
}
