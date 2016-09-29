<%-- 
    Document   : entry
    Created on : Sep 11, 2016, 1:13:17 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flooring Calculator</title>

        <style>

            h1, p{
                text-align: center;
            }
            
        </style>

    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>


            <h1>Flooring Calculator</h1>

            <form action="FloorCalcServlet" method="POST">
                <div class="form-group">
                    <label>Enter width of floor(feet</label>
                    <input class="form-control" type="number" step="0.001" name="width" required />
                </div>
                <div class="form-group">
                    <label>Enter length of floor(feet)</label>
                    <input class="form-control" type="number" step="0.001" name="length" required/><br>
                </div>
                <div class="form-group">
                    <label>Enter cost of flooring per Sq. Ft.</label>
                    <input type="number" step="0.01" name="cost" required/>
                </div>
                
                <input class="btn" type="submit" value="Submit">

            </form>


            <p>The cost of the material is <fmt:formatNumber type="currency" value="${matCost}"/> </p>




    </body>
</html>
