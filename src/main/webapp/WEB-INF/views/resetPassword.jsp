<!DOCTYPE html>
<html lang="en">
<head>
    <title>Password Reset Confirmation</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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


        .container {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: rgb(163, 16, 119);
        }

        p {
            color: #0f0f0f;
            margin-bottom: 20px;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .login-btn {
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        .login-btn:hover {
            background-color: #007bff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
    <center>
    <br><br><br>
    <div class="container">
        <h2>Password Reset Confirmation</h2>
        <p>Your password has been reset successfully. You can now log in with your new password.</p>

        <!-- <p>Return to <a href="${pageContext.request.contextPath}/login">Login</a></p> -->

        <a href="${pageContext.request.contextPath}/OwnerLogin" class="btn btn-primary login-btn">Login</a>
    </div>
</center>
</body>
</html>
