<%-- 
    Document   : delete
    Created on : Sep 14, 2016, 7:57:20 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
         <jsp:include page="navbar.jsp"></jsp:include>
        
        <div class="container">
            <h1>Delete Address</h1>

            <form action="${pageContext.request.contextPath}/address/delete" method="POST">

                <input type="hidden" name ="id" value="${address.id}"/>

                <div class="form-group">
                    <label>First Name:</label>
                    <input class="form-control" value="${address.firstName}" type="text" name="firstName" />
                </div>
                <div class="form-group">
                    <label>Last Name:</label>
                    <input class="form-control" value="${address.lastName}" type="text" name="lastName" />
                </div>
                <div class="form-group">
                    <label>Street:</label>
                    <input class="form-control" value="${address.street}" type="text" name="street" />
                </div>
                <div class="form-group">
                    <label>City:</label>
                    <input class="form-control" value="${address.city}" type="text" name="city" />
                </div>
                <div class="form-group">
                    <label>State:</label>
                    <input class="form-control" value="${address.state}" type="text" name="state" />
                </div>
                 <div class="form-group">
                    <label>Zip Code:</label>
                    <input class="form-control" value="${address.zip}" type="text" name="zip" />
                </div>

                <input type="submit" value="Confirm Delete" /> 


            </form>
        </div>
    </body>
        
    </body>
</html>
