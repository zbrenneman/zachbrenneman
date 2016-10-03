<%-- 
    Document   : search
    Created on : Oct 2, 2016, 3:14:35 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class ="col-xs-6">

                <h2>Search</h2>
            <sf:form commandName= "searchObject">

                <div class="form-group">
                    <label>Select a Keyword to Search By</label>
                    <select class="form-control"  id="keywordChoice">

                        <option value="firstName">First Name</option>
                        <option value="lastName">Last Name</option>
                        <option value="street">Street</option>
                        <option value="city">City</option>
                        <option value="state">State</option>
                        <option value="zip">Zip Code</option>

                    </select>
                </div>
                <div class="form-group">
                    <label>Enter Search Term:</label>
                    <input type="text" id="searchChoice"/>
                </div>

                <input id="searchButton" class ="btn" type="submit" value="Submit" /> 
            </sf:form>

        </div>
        <div class="col-xs-6">

            <h2>Search Results</h2>

            <table id = "searchTable" class ="table table-responsive">

                <tr>
                    <th>Name</th>
                    <th>Location</th>
                </tr>

            </table>

        </div>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/searchScript.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    </body>
</html>
