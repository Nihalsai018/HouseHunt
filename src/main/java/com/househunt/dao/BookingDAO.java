
package com.househunt.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.househunt.model.Booking;
import com.househunt.model.DBConnection;
import com.househunt.model.OwnerDetails;
import com.househunt.model.RentalData;
import com.househunt.model.TenantDetails;
 
public class BookingDAO {
    private Connection connection;
    // Constructor to receive the database connection
    public BookingDAO(Connection connection) {
        this.connection = connection;
    }
    
    
    
    public BookingDAO() {
		super();
	}



	// Method to create the booking table if it does not exist
    public void createBookingTable() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                try (Statement statement = connection.createStatement()) {
                    // Create the 'booking' table if it does not exist
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS booking (" +
                            "booking_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "tenant_id INT," +
                            "owner_id INT," +
                            "rental_id INT," +
                            "status VARCHAR(20) DEFAULT 'Pending'," +
                            "FOREIGN KEY (tenant_id) REFERENCES tenant(tenant_id)," +
                            "FOREIGN KEY (owner_id) REFERENCES owner(owner_id)," +
                            "FOREIGN KEY (rental_id) REFERENCES rental_data(rental_id))");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception details
        }
    }

 // Method to get rental data by owner ID
    public static List<RentalData> getRentalDataByOwnerId(int ownerId) {
        List<RentalData> rentalDataList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM rental_data WHERE owner_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ownerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

                        rentalDataList.add(rentalData);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, log to a file or throw a custom exception
        }

        return rentalDataList;
    }
    
    // Method to insert a new booking into the database
    public Booking insertBooking(Booking booking) {
        // Check if the tenant with the specified ID exists
        if (!isTenantExists(booking.getTenantId())) {
            System.out.println("Error: Tenant with ID " + booking.getTenantId() + " does not exist.");
            return null;
        }
        
        
        String sql = "INSERT INTO booking (tenant_id, owner_id, rental_id, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, booking.getTenantId());
            statement.setInt(2, booking.getOwnerId());
            statement.setInt(3, booking.getRentalId());
            statement.setString(4, booking.getStatus());
           // Log the SQL query
            System.out.println("Executing SQL query: " + sql + " with parameters: " +
                    booking.getTenantId() + ", " +
                    booking.getOwnerId() + ", " +
                    booking.getRentalId() + ", " +
                    booking.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int bookingId = generatedKeys.getInt(1);
                        booking.setBookingId(bookingId);
                   }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error handling strategy
        }
        return booking;
    }
    
    
    // Method to check if a tenant with the specified ID exists
    private boolean isTenantExists(int tenantId) {
        String sql = "SELECT * FROM tenant WHERE tenant_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, tenantId);
           // Log the SQL query
            System.out.println("Executing SQL query: " + sql + " with parameters: " + tenantId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if a tenant with the specified ID exists
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error handling strategy
            return false; // Assume tenant does not exist in case of an exception
        }
    }
    public boolean isOwnerExists(int ownerId) {
        String sql = "SELECT * FROM owner WHERE owner_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ownerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if an owner with the specified ID exists
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error handling strategy
            return false; // Assume owner does not exist in case of an exception
        }
    }
    
    // Method to check if a rental with the specified ID exists
    public boolean isRentalExists(int rentalId) {
        String sql = "SELECT * FROM rental_data WHERE rental_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rentalId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if a rental with the specified ID exists
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error handling strategy
            return false; // Assume rental does not exist in case of an exception
        }
    }
    
    
    
    public static List<Booking> getBookingsByOwnerId(int ownerId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection =DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE owner_id = ?")) {
            preparedStatement.setInt(1, ownerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(resultSet.getInt("booking_id"));
                    booking.setTenantId(resultSet.getInt("tenant_id"));
                    booking.setOwnerId(resultSet.getInt("owner_id"));
                    booking.setRentalId(resultSet.getInt("rental_id"));
                    booking.setStatus(resultSet.getString("status"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return bookings;
    }

    // Add this method to update the booking status
    public static void updateBookingStatus(int bookingId, String newStatus) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE booking SET status = ? WHERE booking_id = ?")) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
    
 // Method to get bookings by ownerId with tenant details
    public static List<Booking> getBookingsByOwnerIdWithTenantDetails(int ownerId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT b.*, t.* " +
                    "FROM booking b " +
                    "JOIN tenant_details t ON b.tenant_id = t.tenant_id " +
                    "WHERE b.owner_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ownerId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Booking booking = new Booking();
                        booking.setBookingId(resultSet.getInt("booking_id"));
                        booking.setTenantId(resultSet.getInt("tenant_id"));
                        booking.setOwnerId(resultSet.getInt("owner_id"));
                        booking.setRentalId(resultSet.getInt("rental_id"));
                        booking.setStatus(resultSet.getString("status"));

                        // Populate TenantDetails
                        TenantDetails tenantDetails = new TenantDetails();
                        tenantDetails.setTenantId(resultSet.getInt("tenant_id"));
                        tenantDetails.setFullName(resultSet.getString("full_name"));
                        tenantDetails.setPhone(resultSet.getString("phone"));
                        tenantDetails.setEmail(resultSet.getString("email"));
                        tenantDetails.setAddress(resultSet.getString("address"));
                        tenantDetails.setOccupation(resultSet.getString("occupation"));
                        // Add other properties...

                        // Set TenantDetails in Booking
                        booking.setTenantDetails(tenantDetails);

                        bookings.add(booking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }

        return bookings;
    }

    
    
    
    // Method to get bookings by tenantId with owner details
    public static List<Booking> getBookingsByTenantIdWithOwnerDetails(int tenantId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT b.*, r.*, o.* " +
                    "FROM booking b " +
                    "JOIN rental_data r ON b.rental_id = r.rental_id " +
                    "JOIN ownerregister o ON b.owner_id = o.owner_id " +
                    "WHERE b.tenant_id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tenantId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Booking booking = new Booking();
                        booking.setBookingId(resultSet.getInt("booking_id"));
                        booking.setTenantId(resultSet.getInt("tenant_id"));
                        booking.setOwnerId(resultSet.getInt("owner_id"));
                        booking.setRentalId(resultSet.getInt("rental_id"));
                        booking.setStatus(resultSet.getString("status"));

                        // Populate RentalData
                        RentalData rentalData = new RentalData();
                        rentalData.setRentalId(resultSet.getInt("rental_id"));
                        rentalData.setOwnerId(resultSet.getInt("owner_id"));
                        rentalData.setFullName(resultSet.getString("full_name"));
                        rentalData.setPhone(resultSet.getString("phone"));
                        rentalData.setAddress(resultSet.getString("address"));
                        rentalData.setArea(resultSet.getString("area"));
                        rentalData.setRent(resultSet.getString("rent")); 
                        rentalData.setSize(resultSet.getString("size"));
//                        rentalData.setFamilyStatus(resultSet.getString("familyStatus"));
//                        
                        // Add other properties...

                        // Populate OwnerDetails
                        OwnerDetails ownerDetails = new OwnerDetails();
                        ownerDetails.setOwnerId(resultSet.getInt("owner_id"));
                        ownerDetails.setFullName(resultSet.getString("full_name"));
                        ownerDetails.setPhone(resultSet.getString("phone"));
                        ownerDetails.setAddress(resultSet.getString("address"));
                        // Add other properties...

                        // Set RentalData and OwnerDetails in Booking
                        booking.setRentalData(rentalData);
                        booking.setOwnerDetails(ownerDetails);

                        bookings.add(booking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }

        return bookings;
    }

    
    
 // Method to get bookings by tenantId
    public static List<Booking> getBookingsByTenantId(int tenantId) {
        List<Booking> bookings = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM booking WHERE tenant_id = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, tenantId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Booking booking = new Booking();
                        booking.setBookingId(resultSet.getInt("booking_id"));
                        booking.setTenantId(resultSet.getInt("tenant_id"));
                        booking.setOwnerId(resultSet.getInt("owner_id"));
                        booking.setRentalId(resultSet.getInt("rental_id"));
                        booking.setStatus(resultSet.getString("status"));

                        // You can add more details if needed

                        bookings.add(booking);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception properly
        }

        return bookings;
    }
}