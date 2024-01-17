package com.househunt.booking;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.househunt.dao.BookingDAO;
import com.househunt.model.Booking;
@WebServlet("/update_status")
public class UpdateStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer ownerId = (Integer) session.getAttribute("ownerId");

        if (ownerId != null) {
            int ownerIdValue = ownerId.intValue();

            // Log statements for debugging
            System.out.println("Owner ID: " + ownerIdValue);

            List<Booking> bookings = BookingDAO.getBookingsByOwnerIdWithTenantDetails(ownerIdValue);
            request.setAttribute("bookings", bookings);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/bookingRequests.jsp");
            requestDispatcher.forward(request, response);
        } else {
            // Handle the case where ownerId is null
            // Log statements for debugging
            System.out.println("Owner ID is null");

            // You may want to redirect the user to a login page or handle it accordingly.
            // For now, let's forward to an error page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int ownerId = (int) session.getAttribute("ownerId");

        int bookingId = Integer.parseInt(request.getParameter("booking_id"));
        String newStatus = request.getParameter("new_status");

        // Check if the ownerId is still valid
        if (ownerId > 0) {
            BookingDAO.updateBookingStatus(bookingId, newStatus);

            // Redirect back to the booking requests page after updating the status
            response.sendRedirect(request.getContextPath() + "/update_status");
        } else {
            // Handle the case where ownerId is not valid
            // For now, let's forward to an error page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
