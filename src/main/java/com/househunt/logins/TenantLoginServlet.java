// TenantLoginServlet.java
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

import com.househunt.model.TenantDetails;

@WebServlet("/TenantLogin")
public class TenantLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/tenant_login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate the tenant by checking the credentials in the database
        TenantDetails tenantDetails = authenticateTenant(username, password);

        if (tenantDetails != null) {
            // Authentication successful
            // Set username, user type, and tenantId in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userType", "tenant");
            session.setAttribute("tenantId", tenantDetails.getTenantId());

            // Redirect to the tenant dashboard
            response.sendRedirect(request.getContextPath() + "/tenantDashboard");
        } else {
            // Authentication failed
            // Set an error message attribute and forward back to the login page
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/tenant_login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private TenantDetails authenticateTenant(String username, String password) {
        TenantDetails tenantDetails = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection using try-with-resources
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root")) {

                // Create a prepared statement
                String sql = "SELECT * FROM tenant WHERE username = ? AND password = ?";
                System.out.println("SQL Query: " + sql);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                // Use try-with-resources for PreparedStatement and ResultSet
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    // Execute the query
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        // Check if any rows are returned (indicating valid credentials)
                    	
                        if (resultSet.next()) {
                            // Retrieve tenant details from the result set
                            int tenantId = resultSet.getInt("tenant_id");
                            // Add more fields as needed

                            // Create a TenantDetails object
                            tenantDetails = new TenantDetails();
                            tenantDetails.setTenantId(tenantId);
                            // Set more fields in the TenantDetails object as needed
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception details or handle it appropriately
            e.printStackTrace();
        }

        return tenantDetails;
    }
}
