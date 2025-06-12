package com.pluralsight;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesContractDao {
    public void save(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (VIN, customer_name, customer_email, sale_price, monthly_payment, sale_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contract.getVehicle().getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getEmail());
            stmt.setDouble(4, contract.getTotalPrice());
            stmt.setDouble(5, contract.getMonthlyPayment());
            stmt.setString(6, contract.getDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
