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
import java.sql.Statement;

@WebServlet("/OwnerRegister")
public class OwnerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    
    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize database and create tables
        createTables();
    }

    private void createTables() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                // Create the 'owner' table if it does not exist
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS owner (" +
                            "owner_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "username VARCHAR(255) NOT NULL," +
                            "password VARCHAR(255) NOT NULL)");
                }

                // Create the 'ownerregister' table if it does not exist
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS ownerregister (" +
                            "owner_id INT," +
                            "username VARCHAR(255) NOT NULL UNIQUE," +
                            "password VARCHAR(255) NOT NULL," +
                            "full_name VARCHAR(255) NOT NULL," +
                            "phone VARCHAR(20) NOT NULL," +
                            "address VARCHAR(255) NOT NULL," +
                            "PRIMARY KEY (owner_id)," +
                            "FOREIGN KEY (owner_id) REFERENCES owner(owner_id))");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception details
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/owner_register.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve data from the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("full_name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
       

        // Perform registration logic (save to the database)
        boolean registrationSuccessful = registerOwner(username, password, fullName, phone, address);

        if (registrationSuccessful) {
            // Redirect to the login page after successful registration
            response.sendRedirect(request.getContextPath() + "/OwnerLogin");
        } else {
            // Redirect to a failure page or display an error message
            response.sendRedirect("registration-failure.jsp");
        }
    }

    private boolean registerOwner(String username, String password, String fullName, String phone, String address) {
        // Use try-with-resources to automatically close resources
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {
                // Insert data into the owner table
                String ownerSql = "INSERT INTO owner (username, password) VALUES (?, ?)";
                try (PreparedStatement ownerStatement = connection.prepareStatement(ownerSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    ownerStatement.setString(1, username);
                    ownerStatement.setString(2, password); // Note: Hash and salt the password

                    // Execute the update
                    int ownerRowsAffected = ownerStatement.executeUpdate();

                    if (ownerRowsAffected > 0) {
                        // Retrieve the generated owner_id
                        try (ResultSet generatedKeys = ownerStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int ownerId = generatedKeys.getInt(1);

                                // Insert data into the ownerregister table using the obtained owner_id
                                String detailsSql = "INSERT INTO ownerregister (owner_id, username, password, full_name, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
                                try (PreparedStatement detailsStatement = connection.prepareStatement(detailsSql)) {
                                    detailsStatement.setInt(1, ownerId);
                                    detailsStatement.setString(2, username);
                                    detailsStatement.setString(3, password); // Note: Hash and salt the password
                                    detailsStatement.setString(4, fullName);
                                    detailsStatement.setString(5, phone);
                                    detailsStatement.setString(6, address);

                                    // Execute the update
                                    int detailsRowsAffected = detailsStatement.executeUpdate();

                                    // Check if both inserts were successful
                                    return detailsRowsAffected > 0;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception details
        }

        return false; // Registration failed
    }


}
