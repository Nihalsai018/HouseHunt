
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!doctype html>
<html lang="en">
  <head>
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">
    <title>Search Houses</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/blog/">
  
    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">
 
   
 <link rel="stylesheet" href="css/areas.css">
  <script>
    
    /*inspect mode*/
    // Disable right-click context menu
document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
});
 
    </script> 
  </head>
  <body style=" background-color: rgba(159, 248, 248, 0.301);">
    
    <nav>
        <div class="company-name">
            <a href="HouseHunt">
                <img src="images/logo-png.png" alt="" width="130px" height="70px">
            </a>
        </div>
    </nav>
   
    
<main class="container">
 
    <div class="row g-5">
        <div class="col-md-4">
            <div class="position-sticky" style="top: 2rem;">
                <div class="p-4 mb-3 bg-light rounded">
                <h3 class="pb-4 mb-4 fst-italic border-bottom" style="font-weight:bold;  color:black;">
                Search Houses in Khammam Location...
            </h3>
                    <h4 class="fst-italic" style="font-weight:bold;  color: rgb(163, 16, 119);">About</h4>
                    <p class="mb-0">At House Hunting Hub, we are dedicated to making your search for the perfect home seamless and enjoyable. Whether you're a family looking for a cozy house with a spacious backyard or a bachelor seeking a trendy apartment in the heart of the city, we have curated listings to match every lifestyle.
.</p>
                </div>

                <div class="p-4">
                    <h4 class="fst-italic" style="font-weight:bold;  color: rgb(163, 16, 119);">Areas</h4>
                    <ol class="list-unstyled mb-0">
                        <ol class="list-unstyled mb-0">
                        
     <!-- Buttons  -->                   
                        
    <li><a href="BRColonyServlet?area=B.R.Colony" class="archive-button"><button>B.R.Colony</button></a></li>
<!-- Include similar links for other areas -->
    <li><a href="ITHubServlet?area=IT Hub" class="archive-button"><button>IT Hub</button></a></li>
    
<li><a href="MamathaRoadServlet?area=Mamatha Cross Road" class="archive-button"><button>Mamatha Cross Road</button></a></li>

<li><a href="ZPCentreServlet?area=ZP Centre" class="archive-button"><button>ZP Centre</button></a></li>

<li><a href="IndiraNagarServlet?area=Indira Nagar" class="archive-button"><button>Indira Nagar</button></a></li>

<li><a href="GandhiChowkServlet?area=Gandhi Chowk" class="archive-button"><button>Gandhi Chow</button></a></li>


<div id="FB">
<!--  the is selecting  the family and batchelors -->
<li><a href="FamilyServlet?family_status=family" class="archive-button"><button>Family</button></a></li>
<li><a href="BachleorsServlet?family_status=bachelor" class="archive-button"><button>Bachelor</button></a></li>
</div>
<br>
  <nav class="blog-pagination" aria-label="Pagination">
                <a class="btn btn-outline-primary" href="#">Back to Top</a>
            </nav>
</ol>

                    </ol>
                </div>
            </div>
        </div>

        <div class="col-md-8">
           <br><br><br>

            <c:forEach var="rentalData" items="${rentalProperties}">
                <article class="blog-post">
                    <div id="m" style="display: flex;">
                        <div id="c2">
                            <!-- Your image carousel code here -->
                            <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" data-bs-interval="10000" style="height:250px; width: 450px;">
                                        <img src="images/house.jpg" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item" data-bs-interval="2000" style="height:250px; width: 450px;">
                                        <img src="images/house1.jpg" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item" data-bs-interval="2000" style="height:250px; width: 450px;">
                                        <img src="images/h3.jpg" class="d-block w-100" alt="...">
                                    </div>
                                     <div class="carousel-item" data-bs-interval="2000" style="height:250px; width: 450px;">
                                        <img src="images/h1.jpg" class="d-block w-100" alt="...">
                                    </div>
                                     <div class="carousel-item" data-bs-interval="2000" style="height:250px; width: 450px;">
                                        <img src="images/h2.jpg" class="d-block w-100" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                            
                        </div>
                        
                       <div id="c1">
                            <table class="t">
                                 <%-- <tr>
                                      <th>Owner ID</th>
                                      <td>${rentalData.ownerId}</td>
                                 </tr> --%>
                                <tr>
                                    <th>Full Name</th>
                                    <td>${rentalData.fullName}</td>
                                </tr>
                                <tr>
                                    <th>Phone</th>
                                    <td>${rentalData.phone}</td>
                                </tr>
                                <tr>
                                    <th>Address</th>
                                    <td>${rentalData.address}</td>
                                </tr>
                                <tr>
                                    <th>Area</th>
                                    <td>${rentalData.area}</td>
                                </tr>
                               <tr>
    <th>Rent</th>
    <td><span class="rent-value">${rentalData.rent}</span></td>
</tr>

                                <tr>
                                    <th>Size</th>
                                    <td>${rentalData.size}</td>
                                </tr>
                                <tr>
                                    <th>Family Status</th>
                                    <td>${rentalData.familyStatus}</td>
                                </tr>
                            </table>

                            <div class="btn-book">
                                <!-- Form to handle booking -->
                              <form action="${pageContext.request.contextPath}/BookProperty" method="post" id="bookingForm">
                                   
                                    <input type="hidden" name="rentalId" value="${rentalData.rentalId}">
                                   <input type="hidden" name="ownerId" value="${rentalData.ownerId}">
                                


                               <input type="hidden" name="fullName" value="${rentalData.fullName}">
                                    <input type="hidden" name="phone" value="${rentalData.phone}">
                                    <input type="hidden" name="address" value="${rentalData.address}">
                                    <input type="hidden" name="area" value="${rentalData.area}">
                                    <input type="hidden" name="rent" value="${rentalData.rent}">
                                    <input type="hidden" name="size" value="${rentalData.size}">
                                    <input type="hidden" name="familyStatus" value="${rentalData.familyStatus}">  
                                    <!-- Add more hidden fields as needed -->

                                    <br><br>
                                    <!-- Book button -->
                                 <button type="submit" class="btn btn-success" id="bookButton">Book</button>

                                </form>
                            </div>

                    </div>
                </article>
               
                
            </c:forEach>

          

          
        </div>
          <hr>
    </div>
</main>
<!-- Your other scripts -->


<script>
    $(document).ready(function () {
        $(document).on("click", "#bookButton", function () {
            // Add client-side validation here if needed

            // Confirmation dialog
            var isConfirmed = confirm("Are you sure you want to book this property?");
            if (isConfirmed) {
                // Submit the form using AJAX or other logic
                $(this).closest("form").submit();
            }
        });
    });
</script>

 
    
  </body>
</html>
