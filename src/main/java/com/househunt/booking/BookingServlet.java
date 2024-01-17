package com.househunt.booking;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.househunt.dao.BookingDAO;
import com.househunt.model.Booking;
import com.househunt.model.DBConnection;
import com.househunt.model.RentalData;

@WebServlet("/BookProperty")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    

    @Override
    public void init() throws ServletException {
        super.init();

        // Create the booking table if it does not exist
        BookingDAO bookingDAO = new BookingDAO();
        bookingDAO.createBookingTable();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Retrieve user type from session
        String userType = (String) session.getAttribute("userType");

        if ("tenant".equals(userType)) {
            // Tenant is booking
            // Retrieve tenant ID from session
        	
            Integer tenantIdAttribute = (Integer) session.getAttribute("tenantId");
            int tenantId = (tenantIdAttribute != null) ? tenantIdAttribute : 0;
            // Retrieve owner ID and rental ID from the request parameters
            int rentalId = Integer.parseInt(request.getParameter("rentalId"));
            int ownerId = Integer.parseInt(request.getParameter("ownerId"));
          

            System.out.println("Owner ID: " + ownerId);
            System.out.println("Rental ID: " + rentalId);

            // Continue with the booking process using the retrieved rentalId

            RentalData rentalData = getRentalDataById(rentalId);

            if (rentalData != null) {
                // Set rental data as a request attribute
                request.setAttribute("rentalData", rentalData);
            }

            // Create a BookingDAO and insert the booking
            try {
                BookingDAO bookingDAO = new BookingDAO(DBConnection.getConnection());
                Booking booking = new Booking(tenantId, ownerId, rentalId, "Pending");

                // Insert the booking and get the updated Booking object
                booking = bookingDAO.insertBooking(booking);

                if (booking != null) {
                    // Forward the booking details to the confirmation page
                    request.setAttribute("booking", booking);

                  

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/bookingConfirmation.jsp");
                    dispatcher.forward(request, response);
                    return; // Added return to avoid further execution after forwarding
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Your method to fetch RentalData by rentalId
    private RentalData getRentalDataById(int rentalId) {
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
                    } else {
                        System.out.println("No rental data found for rentalId: " + rentalId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return rentalData;
    }
}
