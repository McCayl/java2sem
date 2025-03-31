package com.mccayl.dao;

import com.mccayl.manager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAO {
    public void addComputer(String name, String cpu, int ram, String storage, String os) {
        String sql = "INSERT INTO computers (name, cpu, ram, storage, os) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, cpu);
            stmt.setInt(3, ram);
            stmt.setString(4, storage);
            stmt.setString(5, os);
            stmt.executeUpdate();
            System.out.println("Компьютер добавлен: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllComputers() {
        List<String> computers = new ArrayList<>();
        String sql = "SELECT * FROM computers";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String comp = rs.getInt("id") + ": " + rs.getString("name") +
                        " [CPU: " + rs.getString("cpu") + ", RAM: " + rs.getInt("ram") +
                        "GB, Storage: " + rs.getString("storage") + ", OS: " + rs.getString("os") + "]";
                computers.add(comp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computers;
    }
}
