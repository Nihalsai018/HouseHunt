package com.househunt.logins;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.househunt.dao.BookingDAO;
import com.househunt.model.Booking;

@WebServlet("/tenantDashboard")
public class TenantDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                String query = "SELECT td.* FROM tenant t JOIN tenant_details td ON t.tenant_id = td.tenant_id WHERE BINARY t.username = ?";
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setString(1, username);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Set attributes to be used in the JSP
                            request.setAttribute("fullName", resultSet.getString("full_name"));
                            request.setAttribute("email", resultSet.getString("email"));
                            request.setAttribute("phoneNumber", resultSet.getString("phone"));
                            request.setAttribute("address", resultSet.getString("address"));
                            request.setAttribute("occupation", resultSet.getString("occupation"));

                            // Fetch bookings data for the tenant
                            int tenantId = resultSet.getInt("tenant_id");
                            List<Booking> bookings = BookingDAO.getBookingsByTenantIdWithOwnerDetails(tenantId);
                            request.setAttribute("bookings", bookings);

                            // Forward to the tenant dashboard JSP
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/tenant_dashboard.jsp");
                            dispatcher.forward(request, response);
                        } else {
                            // Handle the case when no tenant is found
                            response.sendRedirect("error.jsp");
                        }
                    }
                }
            } catch (SQLException e) {
                response.sendRedirect("error.jsp");
            }
        } else {
            // Handle the case when username is null
            response.sendRedirect("error.jsp");
        }
    }
}
