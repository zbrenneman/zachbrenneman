/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $('#createDvdButton').on('click', function (e) {

        e.preventDefault();

        var myDco = {
            title: $('#createTitle').val(),
            rating: $('#createRating').val(),
            release: $('#createRelease').val(),
            studio: $('#createStudio').val(),
            director: $('#createDirector').val(),
            note: $('#createNote').val()

        }

        var myDcoData = JSON.stringify(myDco);
        console.log(contextRoot)

        $.ajax({
            url: contextRoot + "/dvd",
            type: "POST",
            data: myDcoData,
            dataType: "json",
            beforeSend: function (xhr) {

                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");

            },
            success: function (data, status) {

                var tableRow = buildDvdRow(data);

                $('#dvdTable').append($(tableRow));

                $('#createTitle').val(''),
                        $('#createRating').val(''),
                        $('#createRelease').val(''),
                        $('#createStudio').val(''),
                        $('#createDirector').val(''),
                        $('#createNote').val('')



            },
            error: function (data, status) {

                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#add-dvd-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }

        });


    });

    $('#showDvdModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var dvdId = link.data('dvd-id');

        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {

                $('#showTitle').text(data.dvd.title),
                        $('#showRelease').text(data.dvd.release),
                        $('#showStudio').text(data.dvd.studio),
                        $('#showDirector').text(data.dvd.director),
                        $('#showRating').text(data.dvd.rating)

                $.each(data.notes, function (key, value) {

                    var noteRow = '\
                        <tr class="noteRows"> \n\
                            <td>' + value.userNote + '</td> \n\
                        </tr>';

                    $('#noteTable').append($(noteRow));

                });

            },
            error: function (data, status) {
                alert('Dvd not found');
            }
        });

    });

    $('#showDvdModal').on('hidden.bs.modal', function (e) {

        $('#showTitle').text(''),
                $('#showRelease').text(''),
                $('#showStudio').text(''),
                $('#showDirector').text(''),
                $('#showRating').text(''),
                $('.noteRows').text('')
    });

    $('#editDvdModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var dvdId = link.data('dvd-id');

        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#editId').val(data.dvd.id);
                $('#editTitle').val(data.dvd.title);
                $('#editRating').val(data.dvd.rating);
                $('#editRelease').val(data.dvd.release);
                $('#editStudio').val(data.dvd.studio);
                $('#editDirector').val(data.dvd.director);

                $.each(data.notes, function (key, value) {

                    var noteRow = '\
                        <tr class="noteRows" id="note-row-' + value.noteId + '"> \n\
                         <td>' + value.userNote + '</td> \n\
                         <td><a data-note-id="' + value.noteId + '" data-toggle="modal" data-target="#editNoteModal">Edit</a></td> \n\
                         <td><a data-note-id="' + value.noteId + '" data-toggle="modal" data-target="#deleteNoteModal">Delete</a></td> \n\
                        </tr>';

                    $('#editNoteTable').append($(noteRow));

                });

            },
            error: function (data, status) {
                alert('Dvd not found');
            }
        });


    });

    $('#editDvdButton').on('click', function (e) {

        e.preventDefault();

        var myDvd = {
            id: $('#editId').val(),
            title: $('#editTitle').val(),
            rating: $('#editRating').val(),
            release: $('#editRelease').val(),
            studio: $('#editStudio').val(),
            director: $('#editDirector').val(),
        };

        var myDvdData = JSON.stringify(myDvd);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/dvd/" + myDvd.id,
            type: "PUT",
            data: myDvdData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {


                var tableRow = buildDvdRow(data);

                $('#dvd-row-' + data.id).replaceWith($(tableRow));


            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;

                $.each(errors, function (index, error) {

                    $('#edit-dvd-validation-errors').append(error.fieldName + ": " + error.message + "<br/>");

                });
            }

        });

    });



    $('#editDvdModal').on('hidden.bs.modal', function (e) {

        $('.noteRows').remove();
    });

    $('#deleteDvdModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var dvdId = link.data('dvd-id');

        $.ajax({
            url: contextRoot + "/dvd/" + dvdId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#deleteId').val(data.dvd.id);
                $('#deleteTitle').text(data.dvd.title);
                $('#deleteRelease').text(data.dvd.release);
                $('#deleteRating').text(data.dvd.rating);
                $('#deleteDirector').text(data.dvd.director);
                $('#deleteStudio').text(data.dvd.studio);


            },
            error: function (data, status) {
                alert('Contact not found');
            }
        });


    });

    $('#deleteDvdButton').on('click', function (e) {

        e.preventDefault();

        var myDvd = {
            id: $('#deleteId').val(),
            title: $('#deleteTitle').val(),
            rating: $('#deleteRating').val(),
            release: $('#deleteRelease').val(),
            studio: $('#deleteStudio').val(),
            director: $('#deleteDirector').val(),
        };

        var myDvdData = JSON.stringify(myDvd);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/dvd/" + myDvd.id,
            type: "DELETE",
            data: myDvdData,
            //dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#dvd-row-' + myDvd.id).remove();


            },
            error: function (data, status) {
                console.log("Error")

            }

        });

    });

    $('#editNoteModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var noteId = link.data('note-id');


        $.ajax({
            url: contextRoot + "/note/" + noteId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#editId').val(data.note.noteId);
                $('#editNote').val(data.note.userNote);
                $('#dvdId').val(data.note.dvdId);
            },
            error: function (data, status) {
                alert('Dvd not found');
            }
        });


    });

    $('#editNoteButton').on('click', function (e) {

        e.preventDefault();

        var myNote = {
            noteId: $('#editId').val(),
            dvdId: $('#dvdId').val(),
            userNote: $('#editNote').val()

        };

        var myNoteData = JSON.stringify(myNote);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/note/" + myNote.id,
            type: "PUT",
            data: myNoteData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                var noteRow = '\
                        <tr class="noteRows" id="note-row-' + data.noteId + '"> \n\
                            <td>' + data.userNote + '</td> \n\
                            <td><a data-note-id="' + data.noteId + '" data-toggle="modal" data-target="#editNoteModal">Edit</a></td> \n\
                            <td><a data-note-id="' + data.noteId + '" data-toggle="modal" data-target="#deleteNoteModal">Delete</a></td> \n\
                        </tr>';


                $('#note-row-' + data.noteId).replaceWith($(noteRow));

                $('#editNoteModal').modal('hide');

            },
            error: function (data, status) {
                console.log("Error")

            }

        });

    });

   
   

    $('#createNoteButton').on('click', function (e) {

        e.preventDefault();

        var myNote = {
            userNote: $('#newNote')
        };

        var myNoteData = JSON.stringify(myNote);
        console.log(contextRoot)

        $.ajax({
            url: contextRoot + "/note",
            type: "POST",
            data: myNoteData,
            dataType: "json",
            beforeSend: function (xhr) {

                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");

            },
            success: function (data, status) {

                var noteRow = '\
                        <tr class="noteRows" id="note-row-' + data.noteId + '"> \n\
                            <td>' + data.userNote + '</td> \n\
                            <td><a data-note-id="' + data.noteId + '" data-toggle="modal" data-target="#editNoteModal">Edit</a></td> \n\
                            <td><a data-note-id="' + data.noteId + '" data-toggle="modal" data-target="#deleteNoteModal">Delete</a></td> \n\
                        </tr>';


                $('#note-row-' + data.noteId).append($(noteRow));



            },
            error: function (data, status) {

                console.log("Error")
            }

        });


    });


    $('#deleteNoteModal').on('shown.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var noteId = link.data('note-id');


        $.ajax({
            url: contextRoot + "/note/" + noteId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json")
            },
            success: function (data, status) {
                $('#deleteNoteId').val(data.note.noteId);
                $('#deleteNote').val(data.note.userNote);
                $('#deleteDvdId').val(data.note.dvdId);
            },
            error: function (data, status) {
                alert('Dvd not found');
            }
        });


    });


    $('#deleteNoteButton').on('click', function (e) {

        e.preventDefault();

        var myNote = {
            noteId: $('#deleteNoteId').val(),
            dvdId: $('#deleteDvdId').val(),
            userNote: $('#deleteNote').val()

        };

        var myNoteData = JSON.stringify(myNote);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/note/" + myNote.noteId,
            type: "DELETE",
            data: myNoteData,
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                console.log(data.noteId);
                $('#note-row-' + myNote.noteId).remove();


            },
            error: function (data, status) {
                console.log("Error")

            }

        });

    });



    function buildDvdRow(data) {
        var tableRow = '\
                        <tr id="dvd-row-' + data.id + '"> \n\
                            <td><a data-dvd-id="' + data.id + '" data-toggle="modal" data-target="#showDvdModal">' + data.title + ' </a></td> \n\
                            <td>' + data.rating + '</td> \n\
                            <td><a data-dvd-id="' + data.id + '" data-toggle="modal" data-target="#editDvdModal">Edit</a></td> \n\
                            <td><a data-dvd-id="' + data.id + '" data-toggle="modal" data-target="#deleteDvdModal">Delete</a></td> \n\
                        </tr>';

        return tableRow;
    }



});
