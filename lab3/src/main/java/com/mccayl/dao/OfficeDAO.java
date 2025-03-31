package com.mccayl.dao;

import com.mccayl.manager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfficeDAO {
    public void addOffice(String roomNumber, int floor, String building) {
        String sql = "INSERT INTO offices (room_number, floor, building) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomNumber);
            stmt.setInt(2, floor);
            stmt.setString(3, building);
            stmt.executeUpdate();
            System.out.println("Кабинет добавлен: " + roomNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllOffices() {
        List<String> offices = new ArrayList<>();
        String sql = "SELECT * FROM offices";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                offices.add(rs.getInt("id") + ": Кабинет " + rs.getString("room_number") +
                        ", этаж " + rs.getInt("floor") + ", здание " + rs.getString("building"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offices;
    }
}
