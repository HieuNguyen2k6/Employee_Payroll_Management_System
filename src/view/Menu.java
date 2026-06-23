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
        boolean ischange = false;
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
                        ischange = true;
                        break;
                    case 3:
                        empController.updateEmp();
                        ischange = true;
                        break;
                    case 4:
                        empController.removeEmp();
                        ischange = true;
                        break;
                    case 5:
                        empController.searchEmp();
                        break;
                    case 6:
                        empController.calculateEmp();
                        break;
                    case 7:
                        empController.displayEmp();
                        break;
                    case 8:
                        empController.saveData();
                        ischange = false;
                        break;
                    case 9:
                        empController.exitSystem(ischange);
                        break;
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
