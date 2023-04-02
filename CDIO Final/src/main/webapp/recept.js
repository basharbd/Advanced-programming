$(document).ready(function () {

    getAllRecipes();
    getOneRecipe();
    createRecipe();
    getAllRecipeKomp();
    createRecipeKomp();

    $('#overskrift').hide();
});


function getAllRecipes() {
    $('#recipe-table-form tbody').empty();
    $.ajax({
        url: "system/recept",
        method: "GET",
        contentType: "application/json",
        success: function (recipes) {
            $.each(recipes, function (i, recept) {
                addRecipe(recept);
            });
        },
        error: function () {
            alert("No recipes to load");
        }
    });
}

function addRecipe(recipe) {
    var $recipe = $('#recipe-data');

    $recipe.append('<tr>' +
        '<td id = "recipeId'+recipe.receptId+'">' + recipe.receptId + '</td>' +
        '<td id="recipeName'+recipe.receptId+'">' + recipe.receptNavn + '</td>' +
        '<td id = "edit-recipe-btn'+recipe.receptId+'"><button onclick="showUpdateForm('+recipe.receptId+')" class = "update-recipe">Opdater</button></td>' +
        '</tr>');


    $('.update-recipe').css({"background-color": "#219CE8", "color":
            "white", "border-width": "1px", "cursor": "pointer"});

    $('#recipeName' + recipe.receptId).hover(function () {
        $(this).css({"color": "#219CE8", "cursor": "pointer"});
    },
        function () {
            $(this).css({"color": "black"});
        });


}

function getOneRecipe() {
    $('#find-recipe').on('click', function () {
        $('#recipe-table-form tbody').empty();
        var id = $('#find-recipe-input').val();

        $.ajax({
            url: "system/recept/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (recipes) {
                addRecipe(recipes);
            },
            error: function () {
                getAllRecipes();
                alert("Ingen recepter med denne ID")
            }
        });
    });
}

function showUpdateForm(recipeId) {
        $("#recipeId"+ recipeId).html("<input id = 'd"+recipeId+"' name = 'receptId' type='hidden'>");
        $("#recipeName"+ recipeId).html("<input id = 'o"+recipeId+"' name = 'receptNavn' type = 'text'>");
        $("#edit-recipe-btn" + recipeId).html("<button onclick='updateRecipe()'>Bekræft</button>");

        $.ajax({
            url: "system/recept/" + recipeId,
            method: "GET",
            contentType: "application/json",
            success: function (data) {
                $('#d'+recipeId).val(data.receptId);
                $('#o'+recipeId).val(data.receptNavn);
            },
            error: function () {
                alert("Fejl");
            }
        });
}

function updateRecipe() {

    event.preventDefault();

    var recipe = $('#test').serializeJSON();

    $.ajax({
        url: "system/recept",
        method: 'PUT',
        contentType: "application/json",
        data: recipe,
        success: function () {
            alert("Recept opdateret");
            console.log(recipe);
            loadPage('recept.html');
        },
        error: function () {
            alert("FAIL");
            console.log(recipe);
        }
    });
}

function loadPage(page) {
    window.location.href = page;
}

function createRecipe() {
    $('#create-recipe-btn').on('click', function (e) {
        e.preventDefault();
        var recipe = $('#create-recipe-form').serializeJSON();
        console.log(recipe);
        $.ajax({
            url: "system/recept",
            method: 'POST',
            contentType: "application/json",
            data: recipe,
            success: function () {
                alert("Recept oprettet");
                loadPage('recept.html');
            },
            error: function () {
                alert("Allerede en recept med dette ID eller ugyldig input");
            }
        });
    });
}

function addRecipeKomp(komp) {
    var $komp = $('#rkomp');

    $komp.append('<li>ReceptID: '+ komp.receptId +' </li>' +
        '<li>RåvareID: '+ komp.raavareId +' </li>' +
        '<li id="raavareNavn'+komp.raavareId+'"></li>' +
        '<li>NonNetto: '+ komp.nonNetto +' </li>' +
        '<li>Tolerance: '+ komp.tolerance +' </li>' +
        '<hr>')
}

function getAllRecipeKomp() {
    $('#find-rkomp').click(function () {
        $('ul li').remove();
        $('hr').remove();

        var id = $('#rkom-input').val();

        $.ajax({
            url: "system/rkomp/komp/" + id,
            method: "GET",
            contentType: "application/json",
            success: function (rkomp) {
                $.each(rkomp, function (i, komp) {
                    addRecipeKomp(komp);
                    getRaavare(komp.raavareId);
                    getRecept(id);

                });
            },
            error: function () {
                alert("ID eksisterer ikke");
                $('#overskrift').hide();
            }
        });
    });
}

function getRecept(id) {
    $.ajax({
        url: "system/recept/" + id,
        method: "GET",
        contentType: "application/json",
        success: function (recept) {
            $('#overskrift').html(recept.receptNavn).show();
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
            $('#raavareNavn' + item.raavareId).html("Råvarenavn: " + item.raavareNavn);

        },
        error: function () {
            alert("Fejl")
        }
    });
}


function createRecipeKomp() {
    $('#opret-rkom').on('click', function (e) {
        e.preventDefault();
        var komp = $('#rkomp-form').serializeJSON();
        $.ajax({
            url: "system/rkomp",
            method: 'POST',
            contentType: "application/json",
            data: komp,
            success: function () {
                alert("Recept Komponent oprettet");
                loadPage('rkom.html');
            },
            error: function () {
                alert("Fejl");
            }
        });
    });
}