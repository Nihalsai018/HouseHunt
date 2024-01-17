package com.househunt.logins;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.househunt.model.TenantDetails;

@WebServlet("/TenantProfile")
public class TenantProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username from the session
        String username = (String) request.getSession().getAttribute("username");

        // Fetch tenant details from the database
        TenantDetails tenantDetails = getTenantDetails(username);

        // Set the retrieved details as request attributes
        request.setAttribute("fullName", tenantDetails.getFullName());
        request.setAttribute("phone", tenantDetails.getPhone());
        request.setAttribute("email", tenantDetails.getEmail());
        request.setAttribute("address", tenantDetails.getAddress());
        request.setAttribute("occupation", tenantDetails.getOccupation());

        // Forward the request to the tenantProfile.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/tenantProfile.jsp");
        dispatcher.forward(request, response);
    }

    private TenantDetails getTenantDetails(String username) {
        TenantDetails tenantDetails = new TenantDetails();

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                // Prepare SQL query
                String sql = "SELECT * FROM tenant_details WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Check if the result set has data
                        if (resultSet.next()) {
                            tenantDetails.setFullName(resultSet.getString("full_name"));
                            tenantDetails.setPhone(resultSet.getString("phone"));
                            tenantDetails.setEmail(resultSet.getString("email"));
                            tenantDetails.setAddress(resultSet.getString("address"));
                            tenantDetails.setOccupation(resultSet.getString("occupation"));
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tenantDetails;
    }
}
