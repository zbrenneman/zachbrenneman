<%-- 
    Document   : entry
    Created on : Sep 10, 2016, 4:29:22 PM
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

        <title>Interest Calculator</title>

        <style>

            table{
                margin-right: auto;
                margin-left: auto;
                margin: 15px;

            }

        </style>

    </head>
    
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <h1>Interest Calculator</h1>



            <form action="IntCalcServlet" method="POST">

                <div class="form-group">
                    <label>Enter annual interest rate</label>
                    <input class ="form-control" type="number" placeholder ="%" step="0.001" value="rate" name="intRate" required/>
                </div>

                <div class="form-group">
                    <label>How would you like to compound interest</label> 
                    <select class="form-control" name="compound" required>
                        <option value="d">Daily</option>
                        <option value="m">Monthly</option>
                        <option value="q">Quarterly</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>What is the initial amount of the principal? $</label>
                    <input class="form-control" type="number" step="0.01" value="amount" name="principalAmt" required/>
                </div>

                <div class="form-group">
                    <label>How many years should the money stay in the account?</label>
                    <input class="form-control" type="number" value ="year" name="years" required/><br>
                </div>

                <input class="btn" type="submit" value="Submit"></br>

            </form>

            <table class="table table-responsive">
                <tr>
                    <td>Year</td>
                <c:forEach var="results" items="${years}">

                    <td><fmt:formatNumber type="number" value="${results}" /></td>

                </c:forEach>
            </tr>

            <tr>
                <td>Start of Year Principal</td>
                <c:forEach var="results" items="${startP}">

                    <td><fmt:formatNumber type="currency" value="${results}" /></td>

                </c:forEach>
            </tr>
            <tr>
                <td>Interest Earned This Year</td>
                <c:forEach var="results" items="${interest}">

                    <td><fmt:formatNumber type="currency" value="${results}" /></td>

                </c:forEach>
            </tr>
            <tr>
                <td>End of Year Principal</td>
                <c:forEach var="results" items="${endP}">

                    <td><fmt:formatNumber type="currency" value="${results}" /></td>

                </c:forEach>
            </tr>
        </table>

    </body>
</html>
