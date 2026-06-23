/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ACER
 */
public class Validator {

    private static final String ID_FORMAT = "^E\\d{3}$";

    public static boolean validString(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean validEmpId(String id) {
        return id != null && id.matches(ID_FORMAT);
    }
}
