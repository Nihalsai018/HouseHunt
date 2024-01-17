<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    <title>Owner Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
           
            flex-grow: 1;
        }

        h2 {
            color: rgb(163, 16, 119);
        }

       form {
            width: 100%;
            max-width: 500px;
            margin: 20px;
            padding: 20px;
            background-size: cover;
            transition: background-color 0.3s ease;
            background-color: rgb(255, 255, 255);
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
            z-index: 2;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 16px;
            font-weight: bold;
            color: #333;
        }

        input,
        select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid skyblue;
            border-radius: 4px;
            font-size: 14px;
            box-shadow: 0 4px 4px rgba(0, 0, 0, 0.1);
            transition: border-color 0.3s;
        }

        form input:focus,
        form select:focus {
            outline: none;
            border: 1px solid #e67e22; /* Highlight border color on focus */
        }

        form select:hover,
        form select:focus {
            border: 1px solid #e67e22; /* Highlight border color on hover and focus for select */
        }
        
        h3 {
            text-align: center;
            font-weight: bold;
            color: rgb(163, 16, 119);
        }
        button {
            background-color: navy;
            color: #fff;
            padding: 12px;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            font-size: 16px;
            width: 150px;
            transition: background-color 0.3s;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: block;
            margin: 0 auto; /* Center the button */
        }

        button:hover {
            background-color: #45a049;
        }

        .form-row {
            display: flex;
            flex-wrap: wrap; /* Ensure flexibility in smaller screens */
            gap: 16px;
        }

        .form-group {
            flex: 0 0 48%; /* Adjust as needed */
        }
        /* Add this style to change the background color of the entire navbar */
.navbar {
    background-color: white !important;
}

/* Add this style to align the profile dropdown to the right */
.profile-menu {
    margin-left: auto;
}
        
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="HouseHunt"><img src="images/logo-png.png" alt="" width="130px" height="70px"></a>
        <a href="OwnerProfile">View Profile</a> 
        <a href="AddProperty">Add Property</a>
        <a href="ownerProperties">Property List</a>
        <a href="update_status">Booking Requests</a>
    </div>

    <!-- Page content -->
    <div class="content">
        <div class="dashboard-container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                   <!-- Profile menu -->
                   <h2>Add Property</h2>
<ul class="navbar-nav ml-auto mb-2 mb-lg-0 profile-menu"> 
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <div class="profile-pic">
                <img src="images/icons8-user-48.png" alt="Profile Picture">
            </div>
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="OwnerProfile"><i class="fas fa-sliders-h fa-fw"></i> Account</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="LogoutServlet"><i class="fas fa-sign-out-alt fa-fw"></i> LogOut</a>
        </div>
    </li>
</ul>

                </div>
            </nav>
        </div>
      
        <form action="AddProperty" method="post">
     
        <div class="form-row">
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" required
               minlength="10" maxlength="10" 
               pattern="[0-9]+" title="Please enter only numeric characters (0-9)">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="area">Area:</label>
                <select id="area" name="area" required>
                    <option value="B.R.Colony">B.R.Colony</option>
                    <option value="IT Hub">IT Hub</option>
                    <option value="Mamatha Cross Road">Mamatha Cross Road</option>
                    <option value="ZP Centre">ZP Centre</option>
                    <option value="Indira Nagar">Indira Nagar</option>
                    <option value="Gandhi Chowk">Gandhi Chowk</option>
                </select>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="rent">Rent:</label>
                <input type="text" id="rent" name="rent" required>
            </div>
            <div class="form-group">
                <label for="size">Size:</label>
                <select id="size" name="size" required>
                    <option value="1BHK">1BHK</option>
                    <option value="2BHK">2BHK</option>
                    <option value="3BHK">3BHK</option>
                    <option value="villa">Villa</option>
                </select>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="familyStatus">Family or Bachelor:</label>
                <select id="familyStatus" name="familyStatus" required>
                    <option value="family">Family</option>
                    <option value="bachelor">Bachelor</option>
                </select>
            </div>
        </div>

        <button type="submit">Submit</button>
        </form>
    </div>
    
    
    <script>
    
    /*inspect mode*/
    // Disable right-click context menu
document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
});
 
    </script>
</body>
</html>
