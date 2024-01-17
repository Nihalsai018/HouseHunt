<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Owner Registration</title>
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
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: rgba(159, 248, 248, 0.301);
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            width: 100%;
            background-color: rgb(252, 251, 251);
            overflow: hidden;
            text-align: center;
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .company-name img {
            width: 130px;
            height: 70px;
            margin-right: 10px;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
            text-align: center;
            max-width: 600px;
            width: 100%;
            margin-top: 20px;
        }

        h2 {
            color: rgb(163, 16, 119); 
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
            color: #333;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 6px;
            margin-bottom: 16px;
            border: 1px solid #3498db;
            border-radius: 6px;
            box-sizing: border-box;
        }

        input:focus {
            outline: none;
            border: 1px solid #e74c3c;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            width: 100px;
            padding: 10px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        button:hover {
            background-color: #27ae60;
        }

        p a {
            color: #3498db;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        p a:hover {
            color: #2980b9;
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
    <div class="login-container">
        <h2>Owner Register</h2>
        <form action="OwnerRegister" method="post">
            <div class="form-group row">
                <div class="col-md-6">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" value="${ownerDetails.username}" required>
                </div>
                <div class="col-md-6">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" value="${ownerDetails.password}" required>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-md-6">
                    <label for="full_name">Full Name:</label>
                    <input type="text" class="form-control" id="full_name" name="full_name" value="${ownerDetails.full_name}" required>
                </div>
                <div class="col-md-6">
        <label for="phone">Phone:</label>
        <input type="text" class="form-control" id="phone" name="phone" 
               value="${ownerDetails.phone}" required
               minlength="10" maxlength="10" 
               pattern="[0-9]+" title="Please enter only numeric characters (0-9)">
    </div>
            </div>

            <div class="form-group row">
                <div class="col-md-6">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" value="${ownerDetails.address}" required>
                </div>
                <!-- Add any additional fields specific to owners in the second column of the third row -->
            </div>

            <!-- Add any additional rows and columns as needed -->

            <div class="form-group row">
                <div class="col-md-12 text-center">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
