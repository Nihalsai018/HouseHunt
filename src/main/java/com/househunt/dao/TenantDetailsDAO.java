package com.househunt.dao;

import com.househunt.model.DBConnection;
import com.househunt.model.TenantDetails;
import com.househunt.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TenantDetailsDAO {
    
    public static List<TenantDetails> getAllTenantDetails() {
        List<TenantDetails> tenantDetailsList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tenant_details";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        TenantDetails tenantDetails = new TenantDetails();
                        tenantDetails.setTenantId(resultSet.getInt("tenant_id"));
                        tenantDetails.setFullName(resultSet.getString("full_name"));
                        tenantDetails.setPhone(resultSet.getString("phone"));
                        tenantDetails.setEmail(resultSet.getString("email"));
                        tenantDetails.setAddress(resultSet.getString("address"));
                        tenantDetails.setOccupation(resultSet.getString("occupation"));

                        tenantDetailsList.add(tenantDetails);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenantDetailsList;
    }

    // Method to get TenantDetails by tenantId
    public static TenantDetails getTenantDetailsByTenantId(int tenantId) {
        TenantDetails tenantDetails = null;

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tenant_details WHERE tenant_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tenantId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        tenantDetails = new TenantDetails();
                        tenantDetails.setTenantId(resultSet.getInt("tenant_id"));
                        tenantDetails.setFullName(resultSet.getString("full_name"));
                        tenantDetails.setPhone(resultSet.getString("phone"));
                        tenantDetails.setEmail(resultSet.getString("email"));
                        tenantDetails.setAddress(resultSet.getString("address"));
                        tenantDetails.setOccupation(resultSet.getString("occupation"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tenantDetails;
    }

    public static void setTenantDetailsForBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            TenantDetails tenantDetails = getTenantDetailsByTenantId(booking.getTenantId());
            if (tenantDetails != null) {
                booking.setTenantDetails(tenantDetails);
                System.out.println("Booking ID: " + booking.getBookingId() + ", TenantDetails set: " + tenantDetails);
            } else {
                System.out.println("No TenantDetails found for tenant_id: " + booking.getTenantId());
            }
        }
    }


}
