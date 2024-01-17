package com.househunt.logins;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.househunt.dao.BookingDAO;
import com.househunt.model.Booking;
@WebServlet("/ownerDashboard")
public class OwnerDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Database Connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
            String query = "SELECT od.* FROM owner o JOIN ownerregister od ON o.owner_id = od.owner_id WHERE BINARY o.username = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        request.setAttribute("fullName", resultSet.getString("full_name"));
                        request.setAttribute("phoneNumber", resultSet.getString("phone"));
                        request.setAttribute("address", resultSet.getString("address"));

                        // Fetch booking data for the owner
                     // Fetch booking data for the owner
                        int ownerId = resultSet.getInt("owner_id");
                        List<Booking> bookings = BookingDAO.getBookingsByOwnerIdWithTenantDetails(ownerId);
                        request.setAttribute("bookings", bookings);

                    
                        
                        // Forward to the owner dashboard JSP
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/owner-dashboard.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        // Handle the case when no owner is found
                        PrintWriter out = response.getWriter();
                        out.println("<h2>Error: Owner not found!</h2>");
                    }
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
