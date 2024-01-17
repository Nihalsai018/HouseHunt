<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="com.househunt.model.Booking" %>
<%@ page import="com.househunt.model.TenantDetails" %>
<%@ page import="com.househunt.dao.BookingDAO" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    <title>Tenant Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <script>
    
    /*inspect mode*/
    // Disable right-click context menu
document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
});
 
    </script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: rgba(159, 248, 248, 0.301);
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
        }
 
        .sidebar {
            float: left;
            width: 250px;
            height: 100%;
            background-color: black;
            padding-top: 20px;
            overflow-x: hidden;
            padding-top: 20px;
        }
 
        .sidebar a {
            padding: 16px;
            text-decoration: none;
            font-size: 20px;
            color: white;
            display: block;
        }
 
        .content {
            margin-left: 0;
            padding: 1px 16px;
            height: 1000px;
            flex-grow: 1;
        }
 
        h2 {
            color: rgb(163, 16, 119);
        }
 
        .profile-container {
            text-align: center;
            margin-top: 20px;
            padding: 20px;
            background-color: #fff;
            text-align: center;
            max-width: 150px;
            width: 100%;
            float: right;
        }
 
        .profile-pic {
            display: inline-block;
            vertical-align: middle;
            width: 50px;
            height: 50px;
            overflow: hidden;
            border-radius: 50%;
        }
 
        .profile-pic img {
            width: 100%;
            height: auto;
            object-fit: cover;
        }
 
        .profile-menu .dropdown-menu {
            right: 0;
            left: unset;
        }
 
        .profile-menu .fa-fw {
            margin-right: 10px;
        }
 
        .toggle-change::after {
            border-top: 0;
            border-bottom: 0.3em solid;
        }
 
        /* Navbar styles */
        .navbar {
            background-color: white !important; /* Use !important to override Bootstrap styles */
        }
 
        .navbar-nav {
            margin-left: auto;
        }
 
        .navbar-nav .nav-item {
            padding: 0 10px;
        }
 
        .navbar-nav .nav-link {
            color: black;
        }
 
        .sidebar a:hover {
            transform: scale(1.1); /* Increase the size on hover */
            z-index: 2; /* Ensure the hovered link is on top of other elements */
            box-shadow: 0 0 10px white; /* Add a subtle box shadow on hover */
            transition: all 0.3s ease; /* Add a smooth transition effect */
        }
 
        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
 
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
 
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="HouseHunt"><img src="images/logo-png.png" alt="" width="130px " height="70px"></a>
        <a href="TenantProfile">View Profile</a>
        <a href="ViewProperties">Search Homes</a>
        <a href="bookingConfirmation">Booking Status</a>
    </div>

    <!-- Page content -->
    <div class="content">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0 profile-menu">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <div class="profile-pic">
                                <img src="images/icons8-user-48.png" alt="Profile Picture">
                            </div>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="TenantProfile"><i class="fas fa-sliders-h fa-fw"></i> Account</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="TenantLogout"><i class="fas fa-sign-out-alt fa-fw"></i> Log Out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
   <!-- Your existing content -->

<c:if test="${not empty bookings}">
    <table border="1">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Owner Details</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>
                        <ul>
                            <li><strong>Full Name:</strong> ${booking.rentalData.fullName}</li>
                            <li><strong>Phone:</strong> ${booking.rentalData.phone}</li>
                            <li><strong>Address:</strong> ${booking.rentalData.address}</li>
                            <li><strong>Area:</strong> ${booking.rentalData.area}</li>
                            <li><strong>Rent:</strong> ${booking.rentalData.rent}</li>
                            <li><strong>Size:</strong> ${booking.rentalData.size}</li>
                        </ul>
                    </td>
                    <td>${booking.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty bookings}">
    <p>No bookings found.</p>
</c:if>

<!-- Corrected closing tags -->
</div>
</body>
</html>
