package com.househunt.dao;

import com.househunt.model.DBConnection;
import com.househunt.model.RentalData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalDataDAO {

    static {
        // Load the JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver.");
        }
    }

    public int insertRentalData(RentalData rentalData) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO rental_data (owner_id, full_name, phone, address, area, rent, size, family_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, rentalData.getOwnerId());
            statement.setString(2, rentalData.getFullName());
            statement.setString(3, rentalData.getPhone());
            statement.setString(4, rentalData.getAddress());
            statement.setString(5, rentalData.getArea());
            statement.setString(6, rentalData.getRent());
            statement.setString(7, rentalData.getSize());
            statement.setString(8, rentalData.getFamilyStatus());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated rentalId
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedRentalId = generatedKeys.getInt(1);
                        System.out.println("Generated Rental ID: " + generatedRentalId);
                        return generatedRentalId;
                    }
                }
            }

            return -1; // Return -1 if the insertion was not successful
        } catch (SQLException e) {
            // Log the exception details using a logging framework
            e.printStackTrace();
            return -1; // Return -1 if an exception occurred
        }
    }


    
    public List<RentalData> getAllRentalProperties() {
        List<RentalData> rentalProperties = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM rental_data");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RentalData rentalData = new RentalData();
                rentalData.setRentalId(resultSet.getInt("rental_id"));
                rentalData.setOwnerId(resultSet.getInt("owner_id"));
                rentalData.setFullName(resultSet.getString("full_name"));
                rentalData.setPhone(resultSet.getString("phone"));
                rentalData.setAddress(resultSet.getString("address"));
                rentalData.setArea(resultSet.getString("area"));
                rentalData.setRent(resultSet.getString("rent"));
                rentalData.setSize(resultSet.getString("size"));
                rentalData.setFamilyStatus(resultSet.getString("family_status"));

                rentalProperties.add(rentalData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, log to a file or throw a custom exception
        }

        // Log retrieved properties for debugging
        rentalProperties.forEach(System.out::println);

        return rentalProperties;
    }

    
 // Add the following method to RentalDataDAO
    public List<RentalData> getPropertiesByOwnerId(int ownerId) {
        List<RentalData> ownerProperties = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM rental_data WHERE owner_id = ?");
        ) {
            statement.setInt(1, ownerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    RentalData rentalData = new RentalData();
                    rentalData.setRentalId(resultSet.getInt("rental_id"));
                    rentalData.setOwnerId(resultSet.getInt("owner_id"));
                    rentalData.setFullName(resultSet.getString("full_name"));
                    rentalData.setPhone(resultSet.getString("phone"));
                    rentalData.setAddress(resultSet.getString("address"));
                    rentalData.setArea(resultSet.getString("area"));
                    rentalData.setRent(resultSet.getString("rent"));
                    rentalData.setSize(resultSet.getString("size"));
                    rentalData.setFamilyStatus(resultSet.getString("family_status"));

                    ownerProperties.add(rentalData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, log to a file or throw a custom exception
        }

        return ownerProperties;
    }

    

    public static List<RentalData> getAllRentalData() {
        List<RentalData> rentalDataList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT owner_id, rental_id FROM rental_data")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ownerId = resultSet.getInt("owner_id");
                int rentalId = resultSet.getInt("rental_id");

                // Create a RentalData object without modifying the class
                RentalData rentalData = createRentalData(ownerId, rentalId);
                rentalDataList.add(rentalData);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately (log it, throw a custom exception, etc.)
        }

        return rentalDataList;
    }

    private static RentalData createRentalData(int ownerId, int rentalId) {
        // Create a RentalData object without modifying the class
        RentalData rentalData = new RentalData();
        rentalData.setOwnerId(ownerId);
        rentalData.setRentalId(rentalId);
        return rentalData;
    }
    
    // Method to get RentalData by rentalId
    public RentalData getRentalDataById(int rentalId) {
        RentalData rentalData = null;

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM rental_data WHERE rental_id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, rentalId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        rentalData = new RentalData();
                        rentalData.setRentalId(resultSet.getInt("rental_id"));
                        rentalData.setOwnerId(resultSet.getInt("owner_id"));
                        rentalData.setFullName(resultSet.getString("full_name"));
                        rentalData.setPhone(resultSet.getString("phone"));
                        rentalData.setAddress(resultSet.getString("address"));
                        rentalData.setArea(resultSet.getString("area"));
                        rentalData.setRent(resultSet.getString("rent"));
                        rentalData.setSize(resultSet.getString("size"));
                        rentalData.setFamilyStatus(resultSet.getString("family_status"));

                        // Note: Adjust column names and types based on your actual database schema
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return rentalData;
    }
    
   
    
    
}
