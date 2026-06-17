/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;

/**
 *
 * @author Hiu
 */
public class Menu {
    private View view;
    private Controller empController;
    
    public Menu(View view) {
        this.view = view;
    }
    
    public Menu(View view, Controller empController) {
        this.view = view;
        this.empController = empController;
    }
    
    public void displayMenu() {
        int choice = 0;
        do {
            try {
                choice = view.showMenu();
                switch (choice) {
                    case 1:
                        empController.loadEmp();
                        break;
                    case 2:
                        empController.addEmp();
                        break;
                    case 3:
                        empController.updateEmp();
                        break;
                    case 4:
                        empController.removeEmp();
                        break;
                    case 5:
                        empController.searchEmp();
                        break;
//                    case 6:
//                        studentController.filterByCampus();
//                        break;
                    case 7:
                        empController.displayEmp();
                        break;
//                    case 8:
//                        studentController.saveData();
//                        break;
//                    case 9:
//                        studentController.exitSystem();
//                        break;
                    default:
                        System.out.println(">> This function is not available");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (choice != 9);
    }
}
