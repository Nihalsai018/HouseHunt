package com.househunt.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.househunt.model.DBConnection;
import com.househunt.model.RentalData;

/**
 * Servlet implementation class ITHubServlet
 */
public class ITHubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ITHubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
            String area = request.getParameter("area");
            List<RentalData> rentalProperties = getRentalPropertiesByArea(area);

            // Set the rental properties as a request attribute
            request.setAttribute("rentalProperties", rentalProperties);

            

        // Forward to the ViewProperties.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/ithub.jsp");
        dispatcher.forward(request, response);
    }
        // Implement a method to fetch rental properties from the database based on the area
        private List<RentalData> getRentalPropertiesByArea(String area) {
            List<RentalData> rentalProperties = new ArrayList<>();

            String SQL = "SELECT * FROM rental_data WHERE area = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setString(1, area);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        RentalData rentalData = new RentalData();
                        rentalData.setRentalId(resultSet.getInt("rental_id"));
                        rentalData.setOwnerId(resultSet.getInt("owner_id"));
                        rentalData.setFullName(resultSet.getString("full_name"));
                        rentalData.setPhone(resultSet.getString("phone"));
                        rentalData.setAddress(resultSet.getString("address"));
                        rentalData.setArea(resultSet.getString("area"));
                        rentalData.setRent(resultSet.getString("rent"));
                        rentalData.setSize(resultSet.getString("size"));
                        rentalData.setFamilyStatus(resultSet.getString("family_status"));

                        rentalProperties.add(rentalData);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately, log to a file or throw a custom exception
            }

            return rentalProperties;
        }
    }
