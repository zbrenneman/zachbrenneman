/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $('#createAddressButton').on('click', function (e) {

        e.preventDefault();

        var myAddress = {
            firstName: $('#firstNameCreate').val(),
            lastName: $('#lastNameCreate').val(),
            street: $('#streetCreate').val(),
            city: $('#cityCreate').val(),
            state: $('#stateCreate').val(),
            zip: $('#zipCreate').val(),
        }

        var myAddressData = JSON.stringify(myAddress);
        console.log(contextRoot);

        $.ajax({
            
            url: contextRoot + "/address",
            type: "POST",
            data: myAddressData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {


                var tableRow = buildAddressRow(data);



                $('#addressTable').append($(tableRow));

                $('#firstNameCreate').val('');
                $('#lastNameCreate').val('');
                $('#streetCreate').val('');
                $('#cityCreate').val('');
                $('#stateCreate').val('');
                $('#zipCreate').val('');
                $('#add-address-validation-errors').text('');



            },
            error: function (data, status) {
                
                var errors = data.responseJSON.errors;
                
                $.each(errors, function(index, error){
                    
                    $('#add-address-validation-errors').append(error.message + "<br/>");
                    
                });

            }
            
        });


    });
    
    $('#showAddressModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#showFirstName').text(data.firstName);
                $('#showLastName').text(data.lastName);
                $('#showStreet').text(data.street);
                $('#showCity').text(data.city);
                $('#showState').text(data.state);
                $('#showZip').text(data.zip)

            },
            error: function (data, status) {
                alert('Address not found');
            }
        });

    })
    
    $('#editAddressModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#editId').val(data.id);
                $('#editFirstName').val(data.firstName);
                $('#editLastName').val(data.lastName);
                $('#editStreet').val(data.street);
                $('#editCity').val(data.city);
                $('#editState').val(data.state);
                $('#editZip').val(data.zip);

            },
            error: function (data, status) {
                alert('Address not found');
            }
        });


    });
    
    $('#editAddressButton').on('click', function (e) {

        e.preventDefault();

        var myAddress = {
            id: $('#editId').val(),
            firstName: $('#editFirstName').val(),
            lastName: $('#editLastName').val(),
            street: $('#editStreet').val(),
            city: $('#editCity').val(),
            state: $('#editState').val(),
            zip:$('#editZip').val()
        };

        var myAddressData = JSON.stringify(myAddress);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/address/" + myAddress.id,
            type: "PUT",
            data: myAddressData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {


                var tableRow = buildAddressRow(data);
                
                $('#address-row-' + data.id).replaceWith($(tableRow));
                
                $('#editAddressModal').modal('hide');

            },
            error: function (data, status) {
                console.log("Error")

            }

        });

    });
    
    $('#deleteAddressModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var addressId = link.data('address-id');

        $.ajax({
            url: contextRoot + "/address/" + addressId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#deleteId').val(data.id);
                $('#deleteFirstName').text(data.firstName);
                $('#deleteLastName').text(data.lastName);
                $('#deleteStreet').text(data.street);
                $('#deleteCity').text(data.city);
                $('#deleteState').text(data.state);
                $('#deleteZip').text(data.zip);


            },
            error: function (data, status) {
                alert('Contact not found');
            }
        });


    });
    
    $('#deleteAddressButton').on('click', function (e) {

        e.preventDefault();

        var myAddress = {
            id: $('#deleteId').val(),
            firstName: $('#deleteFirstName').text(),
            lastName: $('#deleteLastName').text(),
            street: $('#deleteStreet').text(),
            city: $('#deleteCity').text(),
            state: $('#deleteState').text(),
            zip: $('#deleteZip').text()
        };

        var myAddressData = JSON.stringify(myAddress);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/address/" + myAddress.id,
            type: "DELETE",
            data: myAddressData,
            //dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {


                $('#address-row-' + myAddress.id).remove();
                
                $('#deleteAddressModal').modal('hide');

            },
            error: function (data, status) {
                console.log("Error")

            }

        });

    });
    
    function buildAddressRow(data) {
        var tableRow = '\
                        <tr id="address-row-' + data.id + '"> \n\
                            <td><a data-address-id="' + data.id + '" data-toggle="modal" data-target="#showAddressModal">' + data.firstName + ' ' + data.lastName + '</a></td> \n\
                            <td>' + data.city + '' + data.state + '</td> \n\
                            <td><a data-address-id="' + data.id + '" data-toggle="modal" data-target="#editAddressModal">Edit</a></td> \n\
                            <td><a data-address-id="' + data.id + '" data-toggle="modal" data-target="#deleteAddressModal">Delete</a></td> \n\
                        </tr>';

        return tableRow;
    }

});

