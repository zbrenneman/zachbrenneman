<%-- 
    Document   : show
    Created on : Sep 14, 2016, 7:39:30 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View<title>
                </head>
                <body>

                    <jsp:include page="navbar.jsp"></jsp:include>

                        <div class="container">
                            <h1>Dvd</h1>

                            <table class="table table-responsive">

                                <tr>
                                    <th>Title</th>
                                    <th>Release</th>
                                    <th>Rating</th>
                                    <th>Studio</th>
                                    <th>Director</th>
                                </tr>
                                <tr>
                                <td>${dvd.title}</td>
                                <td>${dvd.release}</td>
                                <td>${dvd.rating} </td>
                                <td>${dvd.studio} </td>
                                <td>${dvd.director}</td>
                            </tr>
                        </table>

                        <a href="/DvdLibraryWeb" class="btn">Return Home</a>

                    </div>

                </body>

                </body>
                </html>
