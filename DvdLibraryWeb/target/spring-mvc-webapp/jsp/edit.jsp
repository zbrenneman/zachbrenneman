<%-- 
    Document   : edit
    Created on : Sep 14, 2016, 7:46:23 PM
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
        <title>Edit</title>
    </head>
    <body>

        <jsp:include page="navbar.jsp"></jsp:include>


            <div class="container">

                <div class="col-sm-6">
                    <h1>Edit Dvd</h1>

                <sf:form commandName="dco" action="${pageContext.request.contextPath}/dvd/edit" method="POST">

                    <sf:input type="hidden" path="dvdId" value="${dco.dvdId}"/>

                    <div class="form-group">
                        <label>Title:</label>
                        <sf:input class="form-control" value="${dco.title}" type="text" path="title" /><sf:errors path="title"/>
                    </div>
                    <div class="form-group">
                        <label>Rating:</label>
                        <sf:select class="form-control" path="rating">
                            <option value="R">R</option>
                            <option value="PG-13">PG-13</option>
                            <option value="PG">PG</option>
                            <option value="G">G</option>
                        </sf:select>
                    </div>
                    <div class="form-group">
                        <label>Release:</label>
                        <sf:input class="form-control" value="${dco.release}" type="text" path="release" /><sf:errors path="release"/>
                    </div>
                    <div class="form-group">
                        <label>Director:</label>
                        <sf:input class="form-control" value="${dco.director}" type="text" path="director" /><sf:errors path="director"/>
                    </div>
                    <div class="form-group">
                        <label>Studio:</label>
                        <sf:input class="form-control" value="${dco.studio}" type="text" path="studio" /><sf:errors path="studio"/>
                    </div>


                    <input type="submit" value="Save" /> 

                </sf:form>

            </div>

            <div class="col-sm-6">

                <h2>List of Notes</h2>

                <table class ="table table-responsive">
                    <tr>
                        <th>Notes</th>

                    </tr>
                    <c:forEach items="${noteList}" var="note">
                        <tr>
                            <td>${note.userNote}</td>
                            <td><a href="${pageContext.request.contextPath}/note/edit/${note.noteId}">Edit</a></td>
                            <td><a href="${pageContext.request.contextPath}/note/delete/${note.noteId}">Delete</a></td>
                        </tr>
                    </c:forEach>

            </div>    

            <div class="col-sm-6">
                
                <h2>Add Note</h2>
                
                <sf:form commandName="dco" action="${pageContext.request.contextPath}/note/create" method="POST">

                    <sf:input type="hidden" path="dvdId" value="${dco.dvdId}"/>

                    <div class="form-group">
                        <label>Note:</label>
                        <sf:input class="form-control" value="${dco.note}" type="text" path="note" />
                    </div>
                    
                    <input type="submit" value="Save" /> 
                </sf:form>
                
            </div>
                
        </div>

    </body>
</html>
