/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Employee;
import utils.EmployeeRole;

/**
 *
 * @author Hiu
 */
public class EmployeeFile {

    private final String filePath;

    public EmployeeFile() {
        this.filePath = "src/resources/employees.txt";
    }

    public ArrayList<Employee> loadFile() {
        ArrayList<Employee> empList = new ArrayList<>();

        File file = new File(filePath);

        if (!file.exists() || file.length() == 0) {
            return empList;
        }

        try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Tách dữ liệu bằng dấu phẩy
                String[] data = line.split(",");

                // Đọc dữ liệu và dùng .trim() để loại bỏ khoảng trắng thừa
                String id = data[0].trim();
                String name = data[1].trim();

                // Lấy chuỗi role từ file (vd: "Developer") và chuyển thành Enum
                String roleStr = data[2].trim();
                EmployeeRole.Role role = EmployeeRole.getRole(roleStr);

                double baseSalary = Double.parseDouble(data[3].trim());
                int workingDays = Integer.parseInt(data[4].trim());
                double bonus = Double.parseDouble(data[5].trim());

                // CHUYỂN STRING SANG BOOLEAN STATUS (true nếu chuỗi là "true", ngược lại là false)
                boolean status = Boolean.parseBoolean(data[6].trim());

                // Khởi tạo và thêm nhân viên vào danh sách
                Employee emp = new Employee(id, name, role, baseSalary, workingDays, bonus, status);
                empList.add(emp);
            }
        } catch (Exception e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        System.out.println(">> Loaded " + empList.size() + " registered students from file.");
        return empList;
    }

    public void saveEmployeeToFile(ArrayList<Employee> studentList) {
        if (studentList.isEmpty()) {
            System.out.println(">> Nothing to save! The registration list is empty.");
            return;
        }
        try (
                 FileOutputStream fos = new FileOutputStream(filePath);  ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(studentList);
            System.out.println("Registration data has been successfully saved.");
        } catch (Exception e) {
            System.err.println(">> [ERROR] Unable to save file: " + e.getMessage());
        }
    }
}
