package com.mccayl.dao;

import com.mccayl.manager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public void addEmployee(String name, String department) {
        String sql = "INSERT INTO employees (name, department) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.executeUpdate();
            System.out.println("Сотрудник добавлен: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllEmployees() {
        List<String> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(rs.getInt("id") + ": " + rs.getString("name") +
                        " (отдел: " + rs.getString("department") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
