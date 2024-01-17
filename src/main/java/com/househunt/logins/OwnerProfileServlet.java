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

import com.househunt.model.OwnerDetails;
@WebServlet("/OwnerProfile")
public class OwnerProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Assuming the owner is logged in, you can get the owner username from the session
    	  String username = (String) req.getSession().getAttribute("username");


      
            // Fetch owner details from the database
            OwnerDetails ownerDetails = getOwnerDetailsByUsername(username);

            // Set ownerDetails as an attribute in the request
            req.setAttribute("ownerDetails", ownerDetails);

            // Set the retrieved details as request attributes
            req.setAttribute("fullName", ownerDetails.getFullName());
            req.setAttribute("phone", ownerDetails.getPhone());
            req.setAttribute("address", ownerDetails.getAddress());

            // Forward to the owner profile page (you can customize this)
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/owner_profile.jsp");
            requestDispatcher.forward(req, resp);
        
    }

    // Implement the method to fetch owner details by username
    private OwnerDetails getOwnerDetailsByUsername(String username) {
        OwnerDetails ownerDetails = new OwnerDetails(); // Instantiate the object

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                // Create a prepared statement to fetch owner details from the database
                String sql = "SELECT * FROM ownerregister WHERE username = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, username);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Check if the result set has data
                        if (resultSet.next()) {
                            ownerDetails.setOwnerId(resultSet.getInt("owner_id"));
                            ownerDetails.setFullName(resultSet.getString("full_name"));
                            ownerDetails.setPhone(resultSet.getString("phone"));
                            ownerDetails.setAddress(resultSet.getString("address"));

                            // Set more fields in the OwnerDetails object as needed
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log the exception details
        }

        return ownerDetails;
    }
}