package com.househunt.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.househunt.dao.RentalDataDAO;
import com.househunt.model.RentalData;

public class AddPropertyServlet extends HttpServlet {
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
                // Create the 'rental_data' table if it does not exist
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("CREATE TABLE IF NOT EXISTS rental_data (" +
                            "rental_id INT AUTO_INCREMENT PRIMARY KEY," +
                            "owner_id INT," +
                            "full_name VARCHAR(255) NOT NULL," +
                            "phone VARCHAR(20) NOT NULL," +
                            "address VARCHAR(255) NOT NULL," +
                            "area VARCHAR(100) NOT NULL," +
                            "rent VARCHAR(20) NOT NULL," +
                            "size VARCHAR(20) NOT NULL," +
                            "family_status VARCHAR(20) NOT NULL," +
                            "FOREIGN KEY (owner_id) REFERENCES owner(owner_id))");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception details
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/addProperty.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check if ownerId is present in the session
        Integer ownerId = (Integer) session.getAttribute("ownerId");

        if (ownerId == null) {
            // Handle the case when ownerId is not present in the session
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to the login page
            return; // Stop further processing
        }

        // Continue with your existing logic to retrieve form data and insert into the database
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String area = request.getParameter("area");
        String rent = request.getParameter("rent");
        String size = request.getParameter("size");
        String familyStatus = request.getParameter("familyStatus");

        RentalData rentalData = new RentalData();
        rentalData.setOwnerId(ownerId);
        rentalData.setFullName(fullName);
        rentalData.setPhone(phone);
        rentalData.setAddress(address);
        rentalData.setArea(area);
        rentalData.setRent(rent);
        rentalData.setSize(size);
        rentalData.setFamilyStatus(familyStatus);

        RentalDataDAO rentalDataDAO = new RentalDataDAO();

        // Updated method to get the generated rentalId
        int generatedRentalId = rentalDataDAO.insertRentalData(rentalData);
        if (generatedRentalId != -1) {
            // Store the rentalId in the session
            session.setAttribute("rentalId", generatedRentalId);

            // Print the rentalId before redirection
            System.out.println("Rental ID set in session: " + generatedRentalId);

            response.sendRedirect(request.getContextPath() + "/ownerDashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/add-property-failure.jsp");
        }
    }

}
