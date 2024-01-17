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

@WebServlet("/bookingConfirmation")
public class BookingConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer tenantId = (Integer) session.getAttribute("tenantId");

        if (tenantId != null) {
            int tenantIdValue = tenantId.intValue();

            // Log statements for debugging
            System.out.println("Tenant ID: " + tenantIdValue);

            List<Booking> bookings = BookingDAO.getBookingsByTenantIdWithOwnerDetails(tenantIdValue);
            request.setAttribute("bookings", bookings);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/tenantBookingStatus.jsp");
            requestDispatcher.forward(request, response);
        } else {
            // Handle the case where tenantId is null
            // Log statements for debugging
            System.out.println("Tenant ID is null");

            // You may want to redirect the user to a login page or handle it accordingly.
            // For now, let's forward to an error page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }


    // ...
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer tenantId = (Integer) session.getAttribute("tenantId");

        // Check if the tenantId is still valid
        if (tenantId != null && tenantId > 0) {
            int bookingId = Integer.parseInt(request.getParameter("booking_id"));
            String newStatus = request.getParameter("new_status");

            // Update booking status
            BookingDAO.updateBookingStatus(bookingId, newStatus);

            // Redirect back to the booking confirmation page after updating the status
            response.sendRedirect(request.getContextPath() + "/bookingConfirmation");
        } else {
            // Handle the case where tenantId is not valid
            // For now, let's forward to an error page
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
