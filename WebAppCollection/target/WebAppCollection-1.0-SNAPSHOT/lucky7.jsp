<%-- 
    Document   : entry
    Created on : Sep 9, 2016, 4:44:12 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>

        <style>

            p{
                text-align: center;
            }
            
        </style>

    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <h1>Lucky 7s</h1>

            <div class="form-group">
                
                <form action = "L7Servlet" method = "POST">
                    <label>Enter Amount $</label> 
                    <input class="form-control" type ="number" value = "num" name="myResponse"/><br/>
                </form>
            
            </div>


            <p>The result of your game is:</p>
            <p>${message}</p>
            <p>${message2}</p>


    </body>
</html>
