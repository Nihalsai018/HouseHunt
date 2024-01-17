<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin Dashboard</title>
 <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        /* your existing styles */

               body {
            /* existing styles */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: rgba(159, 248, 248, 0.301); /* Blue color */
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
           
        }

        nav {
            /* styles for the navbar */
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
           /* Blue color */
            width: 100%;
            
             background-color: rgb(252, 251, 251);
    overflow: hidden;
    
   
    text-align: center;
    position: sticky;
    top: 0;
    z-index: 100;
        }

        .company-name {
            /* styles for the company name/logo in the navbar */
            display: flex;
            align-items: center;
            color: white;
        }

        .company-name img {
            /* styles for the logo image in the navbar */
            width: 130px;
            height: 70px;
            margin-right: 10px; /* Add margin to separate the logo from text */
        }

      
     h2 {
             color: rgb(163, 16, 119); 
        }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
       background-color: #f2f2f2;
        color: black;
    }

    tbody tr:hover {
        background-color: #f5f5f5;
    }
    </style>
</head>
<body>
 <nav>
        <div class="company-name">
            <a href="HouseHunt">
                <img src="images/logo-png.png" alt="" width="130px" height="70px">
            </a>
        </div>
    </nav>
<br><br>

<h1> Welcome Admin</h1>
    <h2>Contact List</h2>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Message</th>
                <th>Created At</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="contact" items="${contactList}">
                <tr>
                    <td>${contact.id}</td>
                    <td>${contact.name}</td>
                    <td>${contact.email}</td>
                    <td>${contact.message}</td>
                    <td>${contact.createdAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
