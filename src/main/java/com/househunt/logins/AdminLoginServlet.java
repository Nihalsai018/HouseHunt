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
import java.util.ArrayList;
import java.util.List;

import com.househunt.model.Contact;

@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // SQL query to create the admin table
    private static final String CREATE_ADMIN_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS admin (" +
                    "admin_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL," +
                    "password VARCHAR(255) NOT NULL" +
                    ")";

    @Override
    public void init() throws ServletException {
        super.init();
        createAdminTable();
    }

    private void createAdminTable() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root");

            // Create the admin table if not exists
            try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMIN_TABLE_QUERY)) {
                preparedStatement.executeUpdate();
            }

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log the exception details
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/admin_login.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Authenticate the admin by checking the credentials in the database
        boolean isAuthenticated = authenticateAdmin(username, password);

        if (isAuthenticated) {
            // Retrieve the contact details from the ContactDetailsServlet
            List<Contact> contactList = getContactDetails();

            // Set the contact list as an attribute in the request
            request.setAttribute("contactList", contactList);

            // Forward to the admin dashboard after successful login
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin_dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            // Set an error message attribute and forward back to the login page
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin_login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private List<Contact> getContactDetails() {
        List<Contact> contactList = new ArrayList<>();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/househunthub", "root", "root");

            // Create a statement
            String sql = "SELECT * FROM contacts";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result set
                while (resultSet.next()) {
                    Contact contact = new Contact();
                    contact.setId(resultSet.getInt("id"));
                    contact.setName(resultSet.getString("name"));
                    contact.setEmail(resultSet.getString("email"));
                    contact.setMessage(resultSet.getString("message"));
                    contact.setCreatedAt(resultSet.getTimestamp("created_at"));

                    // Add the contact to the list
                    contactList.add(contact);
                }
            }

            // Close the connection
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();  // Log the exception details
            // You may want to handle this exception appropriately
        }

        return contactList;
    }

    private boolean authenticateAdmin(String username, String password) {
        // For testing purposes, check if the provided username and password match the expected values
        return "admin".equals(username) && "admin@123".equals(password);
    }



}
