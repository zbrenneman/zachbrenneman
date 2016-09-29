<%-- 
    Document   : tipcalc
    Created on : Sep 12, 2016, 9:51:19 PM
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
        <title>Tip Calculator</title>

        <style>

            h1,p{
                text-align: center;
            }

            p{
                font-size: 150%;
            }

        </style>

    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <h1>Tip Calculator</h1>

            <form action="TipCalcServlet" method="POST">

                <div class="form-group">
                    <label>Enter cost of bill</label>
                    <input class ="form-control" type="number" step="0.01" name="billCost" required/>
                </div>

                <div class="form-group">
                    <label>Enter tip percentage</label> 
                    <input class ="form-control" type="number" step="0.01" placerholder="%" name="tipAmt" required/>
                </div>

                <input class="btn" type="submit" value="Submit"></br>

            </form>


            <p>Subtotal    : <fmt:formatNumber type="currency" value="${billCost}"/></p>
        <p>Tip %       : <fmt:formatNumber type="number" value="${tipPercent}"/></p>
        <p>Tip         :  <fmt:formatNumber type="currency" value="${tipAmt}"/></p>
        <p>Grand Total : <fmt:formatNumber type="currency" value="${billTotal}"/></p>



    </body>
</html>
