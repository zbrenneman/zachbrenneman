<%-- 
    Document   : deleteconfirm
    Created on : Sep 14, 2016, 8:12:08 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete</title>
    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class="container">
                <h1>Address Successfully Deleted</h1>

                <table class="table table-responsive">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Street</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Zip Code</th>
                    </tr>
                    <tr>
                        <td>${address.firstName}</td>
                    <td>${address.lastName}</td>
                    <td>${address.street} </td>
                    <td>${address.city} </td>
                    <td>${address.state} </td>
                    <td>${address.zip}</td>
                </tr>
            </table>

            <a href="/AddressList" class="btn">Return Home</a>

        </div>
    </body>

</body>
</html>
