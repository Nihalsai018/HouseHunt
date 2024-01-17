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

import com.househunt.model.OwnerDetails;
@WebServlet("/OwnerLogin")
public class OwnerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/owner_login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate the owner
        OwnerDetails ownerDetails = authenticateOwner(username, password);

        if (ownerDetails != null) {
            // Authentication successful
            System.out.println("Authentication successful");

            // Set owner details and user type in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("ownerId", ownerDetails.getOwnerId());
            session.setAttribute("userType", "owner"); // Add user type information

            
            
            response.sendRedirect(request.getContextPath() + "/ownerDashboard");
        } else {
            // Authentication failed
            System.out.println("Authentication failed");

            // Set an error message attribute and forward back to the login page
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/owner_login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private OwnerDetails authenticateOwner(String username, String password) {
        OwnerDetails ownerDetails = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection using try-with-resources
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                // Authenticate owner with hashed password
                ownerDetails = authenticateOwnerWithHashedPassword(connection, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception details or redirect to an error page
            e.printStackTrace();
        }

        return ownerDetails;
    }

    private OwnerDetails authenticateOwnerWithHashedPassword(Connection connection, String username, String password) throws SQLException {
        OwnerDetails ownerDetails = null;

        // Create a prepared statement
        String sql = "SELECT * FROM owner WHERE username = ? AND password = ?";
        System.out.println("SQL Query: " + sql);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if any rows are returned (indicating valid credentials)
                if (resultSet.next()) {
                    // Retrieve owner details from the result set
                    int ownerId = resultSet.getInt("owner_id");

                    // Create an OwnerDetails object
                    ownerDetails = new OwnerDetails();
                    ownerDetails.setOwnerId(ownerId);

                    // Set more fields in the OwnerDetails object as needed
                }
            }
        }

        return ownerDetails;
    }
}
