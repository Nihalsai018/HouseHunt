
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

import java.util.logging.Level;

import java.util.logging.Logger;
 
@WebServlet("/TenantRegister")

public class TenantRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
 
    @Override

    public void init() throws ServletException {

        // Initialize database and create tables

        createTables();

    }
 
    private void createTables() {

        try {

            // Load MySQL JDBC driver

            Class.forName("com.mysql.cj.jdbc.Driver");
 
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root");

                 Statement statement = connection.createStatement()) {

                // Create tenant table

                String createTenantTable = "CREATE TABLE IF NOT EXISTS tenant (tenant_id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50), password VARCHAR(50))";

                statement.executeUpdate(createTenantTable);
 
                // Create tenant_details table

                String createDetailsTable = "CREATE TABLE IF NOT EXISTS tenant_details (tenant_id INT PRIMARY KEY, "

                        + "full_name VARCHAR(100), phone VARCHAR(20), email VARCHAR(100), address VARCHAR(255), occupation VARCHAR(50), "

                        + "FOREIGN KEY (tenant_id) REFERENCES tenant(tenant_id))";

                statement.executeUpdate(createDetailsTable);

            }

        } catch (SQLException | ClassNotFoundException ex) {

            Logger.getLogger(TenantRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
 
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/tenant_details.jsp");

        requestDispatcher.forward(req, resp);

    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        // Retrieve data from the registration form

        String username = request.getParameter("username");

        String password = request.getParameter("password");

        String fullName = request.getParameter("fullName");

        String phone = request.getParameter("phone");

        String email = request.getParameter("email");

        String address = request.getParameter("address");

        String occupation = request.getParameter("occupation");
 
        // Perform registration logic (save to the database)

        boolean registrationSuccessful = registerTenant(username, password, fullName, phone, email, address, occupation);
 
        if (registrationSuccessful) {

            // Redirect to the login page after successful registration

            response.sendRedirect(request.getContextPath() + "/TenantLogin");

        } else {

            // Redirect to a failure page or display an error message

            response.sendRedirect("registration-failure.jsp");

        }

    }
 
    private boolean registerTenant(String username, String password, String fullName, String phone, String email, String address, String occupation) {

        try {

            // Load MySQL JDBC driver

            Class.forName("com.mysql.cj.jdbc.Driver");
 
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {

                // Insert data into the tenant table

                String tenantSql = "INSERT INTO tenant (username, password) VALUES (?, ?)";

                try (PreparedStatement tenantStatement = connection.prepareStatement(tenantSql, Statement.RETURN_GENERATED_KEYS)) {

                    tenantStatement.setString(1, username);

                    tenantStatement.setString(2, password); // Note: Hash and salt the password
 
                    // Execute the update

                    int tenantRowsAffected = tenantStatement.executeUpdate();
 
                    if (tenantRowsAffected > 0) {

                        // Retrieve the generated tenant_id

                        try (ResultSet generatedKeys = tenantStatement.getGeneratedKeys()) {

                            if (generatedKeys.next()) {

                                int generatedTenantId = generatedKeys.getInt(1);
 
                                // Insert data into the tenant_details table

                                String detailsSql = "INSERT INTO tenant_details (tenant_id, full_name, phone, email, address, occupation) VALUES (?, ?, ?, ?, ?, ?)";

                                try (PreparedStatement detailsStatement = connection.prepareStatement(detailsSql)) {

                                    detailsStatement.setInt(1, generatedTenantId);

                                    detailsStatement.setString(2, fullName);

                                    detailsStatement.setString(3, phone);

                                    detailsStatement.setString(4, email);

                                    detailsStatement.setString(5, address);

                                    detailsStatement.setString(6, occupation);
 
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

            // Registration failed

        }

        return false;

    }

}
