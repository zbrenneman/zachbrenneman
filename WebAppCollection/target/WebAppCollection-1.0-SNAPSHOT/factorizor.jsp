<%-- 
    Document   : entry
    Created on : Sep 9, 2016, 9:05:54 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factorizor</title>

        <style>
           
            p{
                text-align: center;
            }
        </style>
    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <h1>Welcome to Factorizor</h1>

            <form action="FactorizorServlet" method="POST">

                <div class=""form-group">
                     <label>Enter number</label>
                    <input class="form-control" type="number" value="num" name="myResponse"/>
                </div>

            </form>

            <br/>

            <p>${fMessage}</p>

        <c:forEach var="factors" items="${factor}">

            <p>${factors}</p>

        </c:forEach>

        <p>${message}</p><br/>
        <p>${message2}</p>

    </body>
</html>
