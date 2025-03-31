package com.mccayl.dao;

import com.mccayl.manager.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComputerLocationDAO {
    public void placeComputerInOffice(int computerId, int officeId) {
        String sql = "INSERT INTO computer_locations (computer_id, office_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, computerId);
            stmt.setInt(2, officeId);
            stmt.executeUpdate();
            System.out.println("Компьютер ID " + computerId + " размещён в кабинете ID " + officeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
