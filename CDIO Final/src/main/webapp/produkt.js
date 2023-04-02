$(document).ready(function () {

    $('#product').hide();

    $('#show-pbatch-create').click(function () {
        $('#pbatch-div').slideToggle();
    });

    $('#show-pkomp-create').click(function () {
        $('#pkomp-div').slideToggle();
    });

    $('#pbatch-div').hide();
    $('#pkomp-div').hide();

    $('#status-input').val(0);

getProductBatch();
createProductBatch();
createProductKomp();
updateKompStatus();

});

function addBatchInfo(batch) {
    var $info = $('#batch-info');
    var $status = $('#produkt-status');
    var d = new Date();
    var day = d.getDate();
    var month = d.getMonth() + 1;
    var year = d.getFullYear();

    $info.append('<li>Udskrevet: '+day+'-'+month+'-'+year+' </li>' +
        '<li>Produkt Batch nr: '+ batch.pbId +' </li>' +
        '<li>Recept nr: '+ batch.receptId +' </li>');

    $status.append('<li id = "pstatus">Produktion Status:  </li>');


}

function addProductBatch(batch) {
    var $batch = $('#pbatch-data');

    $batch.append('<tr>' +
        '<td>'+batch.raavareId+'</td>' +
        '<td id = "itemname'+batch.raavareId+'"></td>' +
        '<td>' + batch.nonNetto + '</td>' +
        '<td>' + batch.tolerance + '</td>' +
        '<tr>');
}

function addProductBatch2(batch) {
    var $batch = $('#pbatch-data2');
    $batch.append('<tr>' +
        '<td>'+batch.tara+'</td>' +
        '<td>' +batch.netto+'</td>' +
        '<td>' +batch.rbId + '</td>' +
        '<td>'+batch.oprId+'</td>' +
        '<tr>');
}


function getProductBatch() {
    $('#pbatch-btn').click(function () {
        $('tbody').empty();
       /// $('#table-pbatch-form tbody').empty();
        $('ul li').remove();

        var id = $('#pbatch-input').val();

        $.ajax({
            url: "system/pbatch/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (batch) {
                addBatchInfo(batch);
                getAll(batch.receptId);
                getProductComp(id);

                if(batch.status === 0) {
                    $('#pstatus').append("Startet");
                }

                if(batch.status === 1) {
                    $('#pstatus').append("Underproduktion");
                }

                if(batch.status === 2) {
                    $('#pstatus').append("Afsluttet");
                }

                $('#product').show();
            },
            error: function () {
                alert("ID eksisterer ikke");
                $('#product').hide();
            }
        });
    });
}

function getAll(id) {
        $.ajax({
            url: "system/rkomp/komp/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (batch) {
                $.each(batch, function (i, data) {
                    addProductBatch(data);
                    getRaavare(data.raavareId);
                });
            },
            error: function () {
                alert("Fejl");
            }
        });
}

function getRaavare(id) {
    $.ajax({
        url: "system/raavare/" + id,
        method: "GET",
        contentType: "application/json",
        success: function (item) {
            $('#itemname' + item.raavareId).append(item.raavareNavn);

        },
        error: function () {
            alert("Fejl")
        }
    });
}

function getProductComp(id) {
    $.ajax({
        url: "system/pkomp/komp/" + id,
        method: "GET",
        contentType: "application/json",
        success: function (komp) {
            $.each(komp, function (i, data) {
                addProductBatch2(data);
            });
        },
        error: function () {
            alert("Ingen produktkomponent værdier, tilføj gerne");


        }
    });
}

function createProductBatch() {
    $('#opret-pbatch').on('click', function (e) {
        e.preventDefault();
        var komp = $('#create-pbatch-form').serializeJSON();
        $.ajax({
            url: "system/pbatch",
            method: 'POST',
            contentType: "application/json",
            data: komp,
            success: function () {
                alert("Produktbatch oprettet");
                $('#pbatch-div').slideToggle();
            },
            error: function () {
                alert("Kan ikke oprette");
            }
        });
    });
}

function createProductKomp() {
    $('#opret-pkomp').on('click', function (e) {
        e.preventDefault();
        var komp = $('#create-pkomp-form').serializeJSON();
        $.ajax({
            url: "system/pkomp",
            method: 'POST',
            contentType: "application/json",
            data: komp,
            success: function () {
                alert("Produktkomponent oprettet");
                $('#pkomp-div').slideToggle();
            },
            error: function () {
                alert("Kan ikke oprette");
            }
        });
    });
}

function updateKompStatus() {
    $('#edit-status-btn').on('click', function (e) {
        e.preventDefault();
        var komp = $('#edit-status-form').serializeJSON();
        $.ajax({
            url: "system/pbatch",
            method: 'PUT',
            contentType: "application/json",
            data: komp,
            success: function () {
                alert("Status opdateret");
            },
            error: function () {
                alert("Kan ikke ændre status");
            }
        });
    });
}


