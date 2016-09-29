<%-- 
    Document   : navbar
    Created on : Sep 12, 2016, 2:57:07 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>NavBar</title>

        <style>
            
            #theNavBar{
                border: 12.5px solid darkgray;
                margin: 25px;
                padding: 25px;
            }
            
            a{
                font-size: 175%;
            }
            
            h1{
                text-align: center;
            }
            
            form{
                display: block;
                margin: auto;
                width: 50%;
            }
            
            body{
                background-repeat: no-repeat;
                background-image: url("https://pixabay.com/static/uploads/photo/2013/11/09/20/22/clouds-208000_960_720.jpg");
            }
            
        </style>

    </head>
    
    <body>

        <div id = "theNavBar">
            <ul class ="nav nav-tabs">
                <li><a href="/WebAppCollection/HomePage">Home</a></li>
                <li><a href="/WebAppCollection/IntCalcServlet">Interest Calculator</a></li>
                <li><a href="/WebAppCollection/FactorizorServlet">Factorizor</a></li>
                <li><a href="/WebAppCollection/L7Servlet">Lucky Sevens</a></li>
                <li><a href="/WebAppCollection/FloorCalcServlet">Floor Calculator</a></li>
                <li><a href="/WebAppCollection/TipCalcServlet">Tip Calculator</a></li>
            </ul>
        </div>
    </body>
</html>
