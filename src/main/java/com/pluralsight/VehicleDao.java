package com.pluralsight;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("VIN"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("color"),
                rs.getInt("year"),
                rs.getDouble("price"),
                rs.getBoolean("sold")
        );
    }

    public List<Vehicle> getVehiclesByMake(String make) {
        return searchVehicles("SELECT * FROM vehicles WHERE make = ?", make);
    }

    public List<Vehicle> getVehiclesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        return searchVehiclesWithDoubleRange(sql, min, max);
    }

    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        return searchVehiclesWithIntRange(sql, minYear, maxYear);
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return searchVehicles("SELECT * FROM vehicles WHERE color = ?", color);
    }

    public List<Vehicle> getVehiclesByMileageRange(int min, int max) {
        String sql = "SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?";
        return searchVehiclesWithIntRange(sql, min, max);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return searchVehicles("SELECT * FROM vehicles WHERE type = ?", type);
    }

    // Yardımcı metodlar
    private List<Vehicle> searchVehicles(String sql, String value) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapResultSetToVehicle(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private List<Vehicle> searchVehiclesWithDoubleRange(String sql, double min, double max) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapResultSetToVehicle(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private List<Vehicle> searchVehiclesWithIntRange(String sql, int min, int max) {
        List<Vehicle> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapResultSetToVehicle(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public Vehicle getVehicleByVin(String vin) {
        String sql = "SELECT * FROM vehicles WHERE VIN = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapResultSetToVehicle(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
    public void removeVehicle(String vin) {
        String deleteInventory = "DELETE FROM inventory WHERE VIN = ?";
        String deleteVehicle = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection conn = DBUtil.getConnection()) {
            // 1. inventory'den sil
            try (PreparedStatement stmt = conn.prepareStatement(deleteInventory)) {
                stmt.setString(1, vin);
                stmt.executeUpdate();
            }

            // 2. vehicles'dan sil
            try (PreparedStatement stmt = conn.prepareStatement(deleteVehicle)) {
                stmt.setString(1, vin);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}