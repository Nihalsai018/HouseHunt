package com.househunt.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.househunt.dao.RentalDataDAO; // Assuming you have a DAO class
import com.househunt.model.RentalData; // Assuming you have a RentalData class

import java.util.List;


@WebServlet("/ViewProperties")
public class ViewPropertyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch rental properties from the database
        List<RentalData> rentalProperties = getRentalPropertiesFromDatabase();

        // Set the rental properties as a request attribute
        request.setAttribute("rentalProperties", rentalProperties);

        // Forward to the ViewProperties.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ViewProperty.jsp");
        dispatcher.forward(request, response);
    }

    // Implement a method to fetch rental properties from the database
    private List<RentalData> getRentalPropertiesFromDatabase() {
        // You should have a DAO class for data access. Assuming you have RentalDataDAO.
        RentalDataDAO rentalDataDAO = new RentalDataDAO();
        return rentalDataDAO.getAllRentalProperties(); // Implement this method in your DAO
    }
}
