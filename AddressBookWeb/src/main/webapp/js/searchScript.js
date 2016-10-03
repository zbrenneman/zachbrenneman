/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $('#searchButton').on('click', function (e) {

        e.preventDefault();
        
        $('.searchResults').remove();
        
        var searchObject = {
            keyword: $('#keywordChoice').val(),
            searchTerm: $('#searchChoice').val()
        };

        var mySearchData = JSON.stringify(searchObject);
        console.log(contextRoot);

        $.ajax({
            url: contextRoot + "/search",
            type: "POST",
            data: mySearchData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {


                $.each(data.addresses, function (key, value) {

                    var tableRow = '\
                        <tr class="searchResults"> \n\
                            <td>' + value.firstName + '' + value.lastName + '</td> \n\
                            <td>' + value.city + ' ' + value.state + '</td> \n\
                        </tr>';

                    $('#searchTable').append(tableRow);

                });



            },
            error: function (data, status) {

                console.log('error');

            }

        });


    });



});
