$(document).ready(function () {
    $("#identifierbatch-btn").click(function () {
        $("#raavarebatch").fadeOut("slow");
        setTimeout(function () {$("#updatebatch").fadeIn("slow")}, 300);
        $("h1").fadeOut().fadeIn().text("Opdater en råvarebatch");
    });



    $("#bekræft-raavarebatch").click(function () {
        $("#updatebatch").fadeOut("slow");
        setTimeout(function () {$("#raavarebatch").fadeIn("slow")}, 300);
        $("h1").fadeOut().fadeIn().text("Opret din nye råvarebatch");
        $("#identifier-batch").slideUp();
    });



    $("#update-raavarebatch").click(function () {
        $("#identifier-batch").slideToggle();
    });

    $('#identifierbatch-btn').on('click', function () {
        var input = $('#identifierbatch-input').val();

        $.ajax({
            url: "system/rbatch/" + input,
            method: "GET",
            contentType: "application/json",
            success: function (data) {
                $('#raavarebatch-id').val(data.rbId);
                $('#raavare-id').val(data.raavareId);
                $('#maengde').val(data.maengde);
                $('#leverandoer').val(data.leverandoer);
            },
            error: function () {
                alert("Fejl");
            }
        });
    });


    getOneItemBatch();
    getAllItemBatches();
    checkItemID();
    createItemBatch();
    updateItemBatch();

});

function getAllItemBatches() {
    $('#itembatch-table-form tbody').empty();
    $.ajax({
        url: "system/rbatch",
        method: "GET",
        contentType: "application/json",
        success: function (batches) {
            $.each(batches, function (i, batch) {
                addItemBatch(batch);
            });
        },
        error: function () {
            alert("Fejl");
        }
    });
}

function addItemBatch(batch) {
    var $batch = $('#itembatch-data');
    $batch.append('<tr>' +
        '<td>' + batch.rbId + '</td>' +
        '<td>' + batch.raavareId + '</td>' +
        '<td>' + batch.maengde+ '</td>' +
        '<td>' + batch.leverandoer+ '</td>' +'</tr>');
}

function getOneItemBatch() {
    $('#find-itembatch').on('click', function () {
        $('#itembatch-table-form tbody').empty();
        var id = $('#find-itembatch-input').val();

        $.ajax({
            url: "system/rbatch/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (batches) {
                addItemBatch(batches);
            },
            error: function () {
                getAllItemBatches();
                alert("Ingen råvarebatch med denne ID")
            }
        });
    });
}

function checkItemID() {
    $('#itemid-tjek-btn').on('click', function () {
        var id = $('#itemid-tjek').val();

        $.ajax({
            url: "system/raavare/itemname/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (item) {
                alert(item);
            },
            error: function () {
                alert("Ingen råvarer med denne ID");
            }
        });
    });
}


function createItemBatch() {
    $('#opret-raavarebatch').on('click', function (e) {
        e.preventDefault();
        var batch = $('#create-itembatch-form').serializeJSON();
        $.ajax({
            url: "system/rbatch",
            method: 'POST',
            contentType: "application/json",
            data: batch,
            success: function () {
                alert("Råvarebatch oprettet");
                getAllItemBatches();
            },
            error: function () {
                alert("Allerede en råvarebatch med dette ID eller ugyldig input");
            }
        });
    });
}

function updateItemBatch() {
    $('#bekræft-raavarebatch').on('click', function (e) {
        e.preventDefault();
        var batch = $('#update-itembatch-form').serializeJSON();
        $.ajax({
            url: "system/rbatch",
            method: 'PUT',
            contentType: "application/json",
            data: batch,
            success: function () {
                alert("Råvarebatch opdateret");
            },
            error: function () {
                alert("Du kan ikke ændre et ID");
            }
        });
    });
}
