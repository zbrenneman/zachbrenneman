<%-- 
    Document   : home
    Created on : Sep 14, 2016, 7:18:33 PM
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
        <title>Home</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


    </head>
    <body>

        <h1>Address Book</h1>

        <jsp:include page="navbar.jsp"></jsp:include>

            <div class ="container"> 

                <div class="col-xs-6">
                    <h2>My Address Book</h2>
                    <table id="addressTable" class ="table table-responsive">
                        <tr>
                            <th>Name</th>
                            <th>Location</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    <c:forEach items="${addressList}" var="address">
                        <tr id="address-row-${address.id}">
                            <td><a data-toggle="modal" data-target="#showAddressModal" data-address-id="${address.id}">${address.firstName} ${address.lastName}</a></td>
                            <td>${address.city}  ${address.state}</td>
                            <td><a data-toggle="modal" data-target="#editAddressModal" data-address-id="${address.id}">Edit</a></td>
                            <td><a data-toggle="modal" data-target="#deleteAddressModal" data-address-id="${address.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>

            </div>

            <div class ="col-xs-6">
                <h2>Add New Address</h2>

                <sf:form method="POST" commandName= "address" action="${pageContext.request.contextPath}/address/create">
                    <div class="form-group">
                        <label>First Name:</label>
                        <sf:input class="form-control" type="text" path="firstName" id="firstNameCreate" />
                    </div>
                    <div class="form-group">
                        <label>Last Name:</label>
                        <sf:input class="form-control" type="text" path="lastName" id="lastNameCreate"/>
                    </div>
                    <div class="form-group">
                        <label>Street:</label>
                        <sf:input class="form-control" type="text" path="street" id="streetCreate"/>
                    </div>
                    <div class="form-group">
                        <label>City:</label>
                        <sf:input class="form-control" type="text" path="city" id="cityCreate"/>
                    </div>
                    <div class="form-group">
                        <label>State:</label>
                        <sf:input class="form-control" type="text" path="state" id="stateCreate"/>
                    </div>
                    <div class="form-group">
                        <label>Zip code:</label>
                        <sf:input class="form-control" type="text" path="zip" id="zipCreate"/>
                    </div>

                    <div id="add-address-validation-errors"></div>

                    <input id="createAddressButton" class ="btn" type="submit" value="Submit" /> 
                </sf:form>

            </div>

            <div class="modal fade" id="showAddressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Address Info</h4>
                        </div>
                        <div class="modal-body">

                            <table>
                                <tr>
                                    <th>First Name:</th>
                                    <td id="showFirstName"></td>
                                </tr>
                                <tr>
                                    <th>Last Name:</th>
                                    <td id="showLastName"></td>
                                </tr>
                                <tr>
                                    <th>Street:</th>
                                    <td id="showStreet"></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td id="showCity"></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td id="showState"></td>
                                </tr>
                                <tr>
                                    <th>Zip Code:</th>
                                    <td id="showZip"></td>
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

            <div class="modal fade" id="editAddressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Edit Address Info</h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="editId" />

                            <table>
                                <tr>
                                    <th>First Name:</th>
                                    <td><input type="text" id="editFirstName" /></td>
                                </tr>
                                <tr>
                                    <th>Last Name:</th>
                                    <td><input type="text" id="editLastName" /></td>
                                </tr>
                                <tr>
                                    <th>Street:</th>
                                    <td><input type="text" id="editStreet" /></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td><input type="text" id="editCity" /></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td><input type="text" id="editState" /></td>
                                </tr>
                                <tr>
                                    <th>Zip Code:</th>
                                    <td><input type="text" id="editZip" /></td>
                                </tr>
                            </table>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="editAddressButton">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="deleteAddressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Delete Address Info</h4>
                        </div>
                        <div class="modal-body">

                            <input type="hidden" id="deleteId" />

                            <table>
                                <tr>
                                    <th>First Name:</th>
                                    <td id="deleteFirstName" ></td>
                                </tr>
                                <tr>
                                    <th>Last Name:</th>
                                    <td id="deleteLastName" ></td>
                                </tr>
                                <tr>
                                    <th>Street:</th>
                                    <td id="deleteStreet" ></td>
                                </tr>
                                <tr>
                                    <th>City:</th>
                                    <td id="deleteCity" ></td>
                                </tr>
                                <tr>
                                    <th>State:</th>
                                    <td id="deleteState" ></td>
                                </tr>
                                <tr>
                                    <th>Zip:</th>
                                    <td id="deleteZip" ></td>
                                </tr>
                            </table>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" id="deleteAddressButton">Confirm Delete</button>
                        </div>
                    </div>
                </div>
            </div>



        </div>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addressApp.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


    </body>
</html>
