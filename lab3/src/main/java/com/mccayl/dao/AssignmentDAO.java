package com.mccayl.dao;

import com.mccayl.manager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AssignmentDAO {
    public void assignComputerToEmployee(int computerId, int employeeId) {
        String sql = "INSERT INTO assignments (computer_id, employee_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, computerId);
            stmt.setInt(2, employeeId);
            stmt.executeUpdate();
            System.out.println("Компьютер ID " + computerId + " назначен сотруднику ID " + employeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
