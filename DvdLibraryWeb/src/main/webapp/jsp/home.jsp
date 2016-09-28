<%-- 
    Document   : home
    Created on : Sep 14, 2016, 10:01:39 PM
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
    </head>
    <body>
        <h1>Dvd Library</h1>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class ="container"> 

                <div class="col-sm-6">
                    <h2>My Dvd Library</h2>
                    <table id="dvdTable" class ="table table-responsive">
                        <tr>
                            <th>Title</th>
                            <th>Rating</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    <c:forEach items="${dvdList}" var="dvd">
                        <tr id="dvd-row-${dvd.id}">
                            <td><a data-toggle="modal" data-target="#showDvdModal" data-dvd-id="${dvd.id}">${dvd.title}</a></td>
                            <td>${dvd.rating}</td>
                            <td><a data-toggle="modal" data-target="#editDvdModal" data-dvd-id="${dvd.id}">Edit</a></td>
                            <td><a data-toggle="modal" data-target="#deleteDvdModal" data-dvd-id="${dvd.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>

            <div class ="col-sm-6">

                <h2>Add Dvd</h2>

                <table class="table table-responsive">
                    <tr>
                        <th>Title:</th>
                        <td><input type="text" id="createTitle" /></td>
                    </tr>
                    <tr>
                        <th>Release:</th>
                        <td><input type="text" id="createRelease" /></td>
                    </tr>
                    <tr>
                        <th>Rating:</th>
                        <td>
                            <select class="form-control"  id="createRating">
                                <option value="R">R</option>
                                <option value="PG-13">PG-13</option>
                                <option value="PG">PG</option>
                                <option value="G">G</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>Studio:</th>
                        <td><input type="text" id="createStudio" /></td>
                    </tr>
                    <tr>
                        <th>Director:</th>
                        <td><input type="text" id="createDirector" /></td>
                    </tr>
                    <tr>
                        <th>Note:</th>
                        <td><input type="text" id="createNote" /></td>
                    </tr>

                </table>       


                <input id="createDvdButton" class ="btn" type="submit" value="Submit" /> 

                <div id="add-dvd-validation-errors"></div>


            </div>

            <div class="modal fade" id="showDvdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Dvd Info</h4>
                        </div>
                        <div class="modal-body">

                            <table>
                                <tr>
                                    <th>Title:</th>
                                    <td id="showTitle"></td>
                                </tr>
                                <tr>
                                    <th>Rating:</th>
                                    <td id="showRating"></td>
                                </tr>
                                <tr>
                                    <th>Director</th>
                                    <td id="showDirector"></td>
                                </tr>
                                <tr>
                                    <th>Studio:</th>
                                    <td id="showStudio"></td>
                                </tr>
                                <tr>
                                    <th>Release:</th>
                                    <td id="showRelease"></td>
                                </tr>

                            </table>

                            <table class="table table-responsive" id="noteTable">
                                <tr>
                                    <th>Notes</th>
                                </tr>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="editDvdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Edit Dvd Info</h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="editId" />

                            <table>
                                <tr>
                                    <th>Title:</th>
                                    <td><input type="text" id="editTitle" /></td>
                                </tr>
                                <tr>
                                    <th>Release:</th>
                                    <td><input type="text" id="editRelease" /></td>
                                </tr>
                                <tr>
                                    <th>Rating</th>
                                    <td>
                                        <select class="form-control"  id="editRating">
                                            <option value="R">R</option>
                                            <option value="PG-13">PG-13</option>
                                            <option value="PG">PG</option>
                                            <option value="G">G</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Studio:</th>
                                    <td><input type="text" id="editStudio" /></td>
                                </tr>
                                <tr>
                                    <th>Director:</th>
                                    <td><input type="text" id="editDirector" /></td>
                                </tr>

                            </table>


                            <table id="editNoteTable" class="table table-responsive">

                                <th>Notes</th>

                            </table>

                            <a data-toggle="modal" data-target="#createNoteModal"/>Add Note</a>
                            
                            <div id="edit-dvd-validation-errors"></div>


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="editDvdButton">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="deleteDvdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Delete Dvd Info</h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="deleteId" />

                            <table>
                                <tr>
                                    <th>Title:</th>
                                    <td id="deleteTitle" /></td>
                                </tr>
                                <tr>
                                    <th>Release:</th>
                                    <td id="deleteRelease" /></td>
                                </tr>
                                <tr>
                                    <th>Rating:</th>
                                    <td id="deleteRating" /></td>
                                </tr>
                                <tr>
                                    <th>Studio:</th>
                                    <td id="deleteStudio" /></td>
                                </tr>
                                <tr>
                                    <th>Director:</th>
                                    <td id="deleteDirector" /></td>
                                </tr>

                            </table>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteDvdButton">Delete Movie</button>
                        </div>
                    </div>
                </div>
            </div>    

            <div class="modal fade" id="editNoteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Edit Note </h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="editId" />

                            <input type="text" id="editNote" />

                            <input type="hidden" id="dvdId"/>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="editNoteButton">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="deleteNoteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Delete Note </h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="deleteNoteId" />

                            <table>
                                <tr>
                                    <td id="deleteNote" /></td>
                                </tr>
                            </table>

                            <input type="hidden" id="deleteDvdId"/>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteNoteButton">Confirm Delete</button>
                        </div>
                    </div>
                </div>

            </div>

            <div class="modal fade" id="createNoteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Add Note </h4>
                        </div>
                        <div class="modal-body">


                            <input type="text" id="newNote" />


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="createNoteButton">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <script>
                var contextRoot = "${pageContext.request.contextPath}";
            </script>

            <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/dvdApp.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>



    </body>
</html>
