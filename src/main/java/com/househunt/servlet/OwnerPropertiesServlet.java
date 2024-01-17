package com.househunt.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.househunt.dao.RentalDataDAO;
import com.househunt.model.RentalData;

import java.util.List;

@WebServlet("/ownerProperties")
public class OwnerPropertiesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve ownerId from session
        Integer ownerId = (Integer) request.getSession().getAttribute("ownerId");

        if (ownerId == null) {
            // Handle the case when ownerId is not present in the session
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Fetch properties for the owner from the database
        List<RentalData> ownerProperties = getOwnerPropertiesFromDatabase(ownerId);

        // Set the properties as a request attribute
        request.setAttribute("ownerProperties", ownerProperties);

        // Forward to the ownerProperties.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/OwnerProperties.jsp");
        dispatcher.forward(request, response);
    }

    // Implement a method to fetch properties for the owner from the database
    private List<RentalData> getOwnerPropertiesFromDatabase(int ownerId) {
        RentalDataDAO rentalDataDAO = new RentalDataDAO();
        return rentalDataDAO.getPropertiesByOwnerId(ownerId);
    }
}
