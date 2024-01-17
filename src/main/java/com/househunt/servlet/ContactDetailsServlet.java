package com.househunt.servlet;

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


/**
 * Servlet implementation class ContactDetailsServlet
 */
@WebServlet("/details")
public class ContactDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		        // Set the contact list as an attribute in the request
		        request.setAttribute("contactList", contactList);

		        // Forward to a JSP page to display the contacts
		        request.getRequestDispatcher("/WEB-INF/views/displayContacts.jsp").forward(request, response);

		    } catch (ClassNotFoundException | SQLException e) {
		        e.printStackTrace();  // Log the exception details
		        request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
		    }
		
	}


	

}
