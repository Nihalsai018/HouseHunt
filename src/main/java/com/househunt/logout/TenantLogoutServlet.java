package com.househunt.logout;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/TenantLogout")
public class TenantLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null) {
            // Remove specific session attributes based on user type
            session.removeAttribute("username");

            // Invalidate the session
            session.invalidate();

            // Redirect to the login page after logout
            response.sendRedirect(request.getContextPath() + "/TenantLogin");
        } else {
            // If the user is not logged in, redirect to the login page
            response.sendRedirect(request.getContextPath() + "/TenantLogin");
        }
    }
}
