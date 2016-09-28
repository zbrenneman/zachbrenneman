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
                <h1>Dvd Successfully Deleted</h1>

                <table class="table table-responsive">
                    <tr>
                        <th>Title</th>
                        <th>Rating</th>
                        <th>Release</th>
                        <th>Director</th>
                        <th>Studio</th>
                    </tr>
                    <tr>
                        <td>${dvd.title}</td>
                        <td>${dvd.rating}</td>
                        <td>${dvd.release} </td>
                        <td>${dvd.director} </td>
                        <td>${dvd.studio} </td>
                    </tr>
            </table>

            <a href="/DvdLibraryWeb" class="btn">Return Home</a>

        </div>
    </body>

</body>
</html>
