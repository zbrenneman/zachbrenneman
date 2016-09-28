<%-- 
    Document   : delete
    Created on : Sep 14, 2016, 7:57:20 PM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
         <jsp:include page="navbar.jsp"></jsp:include>
        
        <div class="container">
            <h1>Delete Dvd</h1>

            <form action="${pageContext.request.contextPath}/dvd/delete" method="POST">

                <input type="hidden" name ="id" value="${dvd.id}"/>
                <div class="form-group">
                    <label>Title:</label>
                    <input class="form-control" value="${dvd.title}" type="text" name="title" />
                </div>
                <div class="form-group">
                    <label>Rating:</label>
                    <input class="form-control" value="${dvd.rating}" type="text" name="rating" />
                </div>
                <div class="form-group">
                    <label>Release:</label>
                    <input class="form-control" value="${dvd.release}" type="text" name="release" />
                </div>
                <div class="form-group">
                    <label>Studio:</label>
                    <input class="form-control" value="${dvd.studio}" type="text" name="studio" />
                </div>
                <div class="form-group">
                    <label>Director:</label>
                    <input class="form-control" value="${dvd.director}" type="text" name="director" />
                </div>
                

                <input type="submit" value="Confirm Delete" /> 


            </form>
        </div>
    </body>
        
    </body>
</html>
