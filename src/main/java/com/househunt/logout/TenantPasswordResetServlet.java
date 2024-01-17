package com.househunt.logout;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/tenantResetPass")
public class TenantPasswordResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the password reset form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/TenantResetPasswordForm.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        // Check if new password matches confirm new password
        if (!newPassword.equals(confirmNewPassword)) {
            out.println("<h2>New Password and Confirm New Password do not match.</h2>");
            return; // Stop the reset password process
        }

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // Load the JDBC driver and establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/househunthub";
            String jdbcUsername = "root";
            String jdbcPassword = "root";
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            // Update the password in the tenant table
            String updatePasswordQuery = "UPDATE tenant SET password = ? WHERE username = ?";
            statement = conn.prepareStatement(updatePasswordQuery);
            statement.setString(1, newPassword);
            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                out.println("<h2>Password reset successful!</h2>");
                // You may want to redirect to a login page or some other appropriate page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/tenant_login.jsp");
                dispatcher.forward(request, response);
            } else {
                out.println("<h2>Username not found. Password reset failed.</h2>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
