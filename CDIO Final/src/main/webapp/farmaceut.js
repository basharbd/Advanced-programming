$(document).ready(function () {

    $("#identifier-btn").click(function () {
        $("#raavare").fadeOut("slow");
        setTimeout(function () {$("#update").fadeIn("slow")}, 300);
        $("h1").fadeOut().fadeIn().text("Opdater en råvare");
    });



    $("#bekræft-raavare").click(function () {
        $("#update").fadeOut("slow");
        setTimeout(function () {$("#raavare").fadeIn("slow")}, 300);
        $("h1").fadeOut().fadeIn().text("Opret din nye råvare");
        $("#identifier").slideUp();
    });



    $("#update-raavare").click(function () {
        $("#identifier").slideToggle();
    });

    $('#identifier-btn').on('click', function () {
        var input = $('#identifier-input').val();

        $.ajax({
            url: "system/raavare/" + input,
            method: "GET",
            contentType: "application/json",
            success: function (data) {
                $('#raavare-id').val(data.raavareId);
                $('#raavare-navn').val(data.raavareNavn);
            },
            error: function () {
                alert("Fejl");
            }
        });
    });


    getOneItem();
    getAllItems();
    createItem();
    updateItem();
});

function getAllItems() {
    $('#item-table-form tbody').empty();
    $.ajax({
        url: "system/raavare",
        method: "GET",
        contentType: "application/json",
        success: function (items) {
            $.each(items, function (i, item) {
                addItem(item);
            });
        },
        error: function () {
            alert("No items to load");
        }
    });
}

function addItem(item) {
    var $item = $('#item-data');
    $item.append('<tr>' +
        '<td>' + item.raavareId + '</td>' +
        '<td>' + item.raavareNavn + '</td>' +
        '</tr>');
}

function getOneItem() {
    $('#find-item').on('click', function () {
        $('#item-table-form tbody').empty();
        var id = $('#find-item-input').val();

        $.ajax({
            url: "system/raavare/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (items) {
                addItem(items);
            },
            error: function () {
                getAllItems();
                alert("Ingen råvarer med denne ID")
            }
        });
    });
}

function createItem() {
    $('#opret-raavare').on('click', function (e) {
        e.preventDefault();
        var item = $('#create-item-form').serializeJSON();
        $.ajax({
            url: "system/raavare",
            method: 'POST',
            contentType: "application/json",
            data: item,
            success: function () {
                    alert("Råvare oprettet");
                    getAllItems();
            },
            error: function () {
                alert("Allerede en råvare med dette ID eller ugyldig input");
            }
        });
    });
}

function updateItem() {
    $('#bekræft-raavare').on('click', function (e) {
        e.preventDefault();
        var item = $('#update-item-form').serializeJSON();
        $.ajax({
            url: "system/raavare",
            method: 'PUT',
            contentType: "application/json",
            data: item,
            success: function () {
                alert("Råvare opdateret");
            },
            error: function () {
                alert("Du kan ikke ændre et ID");
            }
        });
    });
}
