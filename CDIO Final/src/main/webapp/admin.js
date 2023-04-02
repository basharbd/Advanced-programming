$(document).ready(function () {
    $('#edit-form').hide();
    $('#user-edit').hide();

    getAllUsers();
    createUser();
    getUser();
    updateUser();

    $('#regret-button').on('click', function () {
        $('#edit-form').fadeOut();
    });

    $('#edit').on('click', function () {
        $('#edit-form').fadeIn();
        var id = $('#id-input').val();

        $.ajax({
            url: "system/bruger/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (data) {
                $('#user-id').val(data.oprId);
                $('#user-name').val(data.oprNavn);
                $('#user-ini').val(data.ini);
                $('#user-cpr').val(data.cpr);
                $('#user-rolle').val(data.rolle);
                $('#user-status').val(data.status);
            },
            error: function () {
                alert("Fejl");
            }
        });
    });
});

function getAllUsers() {
    $('#table-form tbody').empty();
    $.ajax({
        url: "system/bruger",
        method: "GET",
        contentType: "application/json",
        success: function (users) {
            $.each(users, function (i, user) {
                addUser(user);
            });
        },
        error: function () {
            alert("Cannot load users - refresh the page")
        }
    });
}

function addUser(user) {
    var $user = $('#user-data');

    $user.append('<tr>' +
        '<td>' + user.oprId + '</td>' +
        '<td>' + user.oprNavn + '</td>' +
        '<td>' + user.ini + '</td>' +
        '<td>' + user.cpr + '</td>' +
        '<td>' + user.rolle + '</td>' +
        '<td>' + user.status + '</td></tr>');
}

function createUser() {
    $('#add-user').on('click', function (e) {
        e.preventDefault();
        var user = $('#create-form').serializeJSON();
        $.ajax({
            url: "system/bruger",
            method: 'POST',
            contentType: "application/json",
            data: user,
            success: function () {
                    alert("Bruger oprettet");
                    loadPage('admin.html');

            },
            error: function () {
                alert("Allerede en person med dette ID");
            }
        });
    });
}

function loadPage(page) {
    window.location.href = page;
}

function addUser2(user) {
    var $user = $('#user');
    $user.append('<li>ID: '+ user.oprId +' </li>' +
                 '<li>Navn: '+ user.oprNavn +' </li>' +
                 '<li>Initial: '+ user.ini +' </li>' +
                 '<li>CPR-Nummer: '+ user.cpr +' </li>' +
                 '<li>Rolle: '+ user.rolle +' </li>' +
                 '<li>Status: '+ user.status +' </li>');
}

function getUser() {
    $('#getUserButton').on('click', function () {
        var id = $('#id-input').val();
        $('ul li').remove();
        $.ajax({
            url: "system/bruger/"+ id,
            method: "GET",
            contentType: "application/json",
            success: function (users) {
                    addUser2(users);
                    $('#user-edit').fadeIn();
            },
            error: function () {
                $('#user-edit').hide();
                alert("Ingen bruger med denne ID");
            }
        });
    });
}

function updateUser() {
    $('#confirm-button').on('click', function (e) {
        e.preventDefault();
        var user = $('#edit-input-form').serializeJSON();
        $.ajax({
            url: "system/bruger",
            method: 'PUT',
            contentType: "application/json",
            data: user,
            success: function () {
                alert("Bruger opdateret");
                loadPage('edit.html');
            },
            error: function () {
                alert("Du kan ikke ændre et ID");
            }
        });
    });
}
