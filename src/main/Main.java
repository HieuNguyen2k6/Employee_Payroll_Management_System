/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import files.EmployeeFile;
import service.EmployeeService;

/**
 *
 * @author ACER
 */
public class Main {
    public static void main(String[] args) {
        EmployeeService empService = new EmployeeService(new EmployeeFile());
    }
    
}
