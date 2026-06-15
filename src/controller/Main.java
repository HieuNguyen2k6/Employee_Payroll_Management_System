/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.util.Scanner;
import view.Menu;
import view.View;

/**
 *
 * @author ACER
 */
public class Main {

    public static void main(String[] args) {
        // 1. Tạo Scanner dùng chung
        Scanner sc = new Scanner(System.in);

        // 2. Khởi tạo View
        View view = new View(sc);

        // 3. Khởi tạo Controller và truyền View vào
        Controller controller = new Controller(view);

        // 4. Khởi tạo Menu và truyền cả View lẫn Controller vào
        Menu menu = new Menu(view, controller);

        // 5. Khởi chạy ứng dụng
        menu.displayMenu();
    }
}
