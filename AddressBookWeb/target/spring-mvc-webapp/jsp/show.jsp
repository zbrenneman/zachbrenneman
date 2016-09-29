<%-- 
    Document   : show
    Created on : Sep 14, 2016, 7:39:30 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View<title>
                </head>
                <body>

                    <jsp:include page="navbar.jsp"></jsp:include>

                        <div class="container">
                            <h1>Address</h1>

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
                                <td>${address.zip} </td>

                            </tr>
                        </table>

                        <a href="/AddressBookWeb" class="btn">Return Home</a>

                    </div>

                </body>

                </body>
                </html>
