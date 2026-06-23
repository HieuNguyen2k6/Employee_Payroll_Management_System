/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files;

import java.util.List;

/**
 *
 * @author ACER
 */
public interface IFileReadWrite<T> {
    void saveToFile(List<T> list);
    List<T> loadFromFile();
}
