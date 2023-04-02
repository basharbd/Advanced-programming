$(document).ready(function () {

    $(".admin-btn").click(function () {
        $(".administrator").slideToggle();
    });

    $(".pharma-btn").click(function () {
        $(".pharmaceut").slideToggle();
    });

    $(".produkt-btn").click(function () {
        $(".produktionsleder").slideToggle();
    });

    $(".lab-btn").click(function () {
        $(".laborant").slideToggle();
    });

    $('#admin-tjek').click(function () {
        var page = 'admin.html';
         var cpr = $('#admin-input').val();
        checkRole("Administrator", cpr, page);
    });

    $('#lab-tjek').click(function () {
        var page = 'laborant.html';
        var cpr = $('#lab-input').val();
        checkRole("Laborant", cpr, page);
    });

    $('#produkt-tjek').click(function () {
        var page = 'produktleder.html';
        var cpr = $('#produkt-input').val();
        checkRole("Produktionsleder", cpr, page);
    });

    $('#pharma-tjek').click(function () {
        var page = 'farmaceut.html';
        var cpr = $('#pharma-input').val();
        checkRole("Farmaceut", cpr, page);
    });


    addHyphenToClass("login-box");

});

function checkForCPR(className, page){
    $("." + className).click(function () {
        var str = $("." + className +"-login-box").val();
        var number = [str.charAt(1), str.charAt(10)];
        if (str.length === 11 && !isNaN(number[0]) && !isNaN(number[1])){
            loadPage(  page + ".html")
        } else {
            alert("Dette er ikke et CPR-nummer")}
    });
}

function addHyphenToClass(className) {
    $('.' + className).keyup(function (event) {
        addHyphen (this);
    });
}

function addHyphen (element) {
    let val = $(element).val().split('-').join('');   // Remove dash (-) if mistakenly entered.

    let finalVal = val.match(/.{1,6}/g).join('-');    // Add (-) after 6th every char.
    $(element).val(finalVal);		// Update the input box.
}

function loadPage(page) {
    window.location.href = page;
}

function checkRole(rolle, cpr, page) {
        $.ajax({
            url: "system/bruger/cpr/'" + cpr + "'",
            method: 'GET',
            contentType: "application/json",
            success: function (user) {
                if (user.cpr == null) {
                    alert("Ugyldig CPR");
                    console.log(rolle);
                    console.log(cpr);
                } else if (user.rolle !== rolle) {
                    alert("Du er en " + user.rolle + " og har derfor ikke adgang");
                    console.log(rolle);
                } else if(user.status === "Inaktiv") {
                    alert("Din bruger er deaktiveret");
                } else {
                    loadPage(page);
                }
            },
            error: function () {
                alert("Fail");
            }
        });
}

