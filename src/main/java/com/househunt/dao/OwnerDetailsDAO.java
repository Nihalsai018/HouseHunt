package com.househunt.dao;


import com.househunt.model.DBConnection;
import com.househunt.model.OwnerDetails;
import com.househunt.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerDetailsDAO {

    // Method to get all owner details
    public static List<OwnerDetails> getAllOwnerDetails() {
        List<OwnerDetails> ownerDetailsList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM owner_details";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        OwnerDetails ownerDetails = new OwnerDetails();
                        ownerDetails.setOwnerId(resultSet.getInt("owner_id"));
                        ownerDetails.setFullName(resultSet.getString("full_name"));
                        ownerDetails.setPhone(resultSet.getString("phone"));
                        ownerDetails.setEmail(resultSet.getString("email"));
                        ownerDetails.setAddress(resultSet.getString("address"));

                        ownerDetailsList.add(ownerDetails);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ownerDetailsList;
    }

    // Method to get OwnerDetails by ownerId
    public static OwnerDetails getOwnerDetailsByOwnerId(int ownerId) {
        OwnerDetails ownerDetails = null;

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM owner_details WHERE owner_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ownerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        ownerDetails = new OwnerDetails();
                        ownerDetails.setOwnerId(resultSet.getInt("owner_id"));
                        ownerDetails.setFullName(resultSet.getString("full_name"));
                        ownerDetails.setPhone(resultSet.getString("phone"));
                        ownerDetails.setEmail(resultSet.getString("email"));
                        ownerDetails.setAddress(resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ownerDetails;
    }

    // Method to set OwnerDetails for a list of bookings
    public static void setOwnerDetailsForBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            OwnerDetails ownerDetails = getOwnerDetailsByOwnerId(booking.getOwnerId());
            if (ownerDetails != null) {
                booking.setOwnerDetails(ownerDetails);
                System.out.println("Booking ID: " + booking.getBookingId() + ", OwnerDetails set: " + ownerDetails);
            } else {
                System.out.println("No OwnerDetails found for owner_id: " + booking.getOwnerId());
            }
        }
    }
}

