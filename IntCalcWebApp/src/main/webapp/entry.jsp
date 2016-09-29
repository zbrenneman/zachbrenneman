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
            td, form{
                font-size: 150%;
            }

            input[type=submit],select{
                margin-top:20px;
                font-size: 95%;
            }

            table, td{
                border: 1px solid burlywood;
                padding: 10px;
            }


            table{
                margin-top: 20px;
            }

        </style>
    </head>
    <body>
        <h1>Interest Calculator</h1>



        <form action="IntCalcServlet" method="POST">

            Enter annual interest rate <input type="number" placeholder ="%" step="0.001" value="rate" name="intRate" required/><br>

            How would you like to compound interest: 
            <select name="compound" required>
                <option value="d">Daily</option>
                <option value="m">Monthly</option>
                <option value="q">Quarterly</option>
            </select><br>
            
            What is the initial amount of the principal? $<input type="number" step="0.01" value="amount" name="principalAmt" required/><br>

            How many years should the money stay in the account? <input type="number" value ="year" name="years" required/><br>

            <input type="submit" value="Submit"></br>

        </form>

        <table>
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
