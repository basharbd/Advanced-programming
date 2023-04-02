$(document).ready(function () {

    $(".cpr").keyup(function () {
        addHyphen(this);
    });

    //checkForCPR("cpr","admin");

});


function checkForCPR(className, page){
    $("#add-user").click(function () {
        var str = $("." + className).val();
        var number = [str.charAt(1), str.charAt(10)];
        if (str.length === 11 && !isNaN(number[0]) && !isNaN(number[1])){
            loadPage(  page + ".html")
        } else {
            alert("Dette er ikke et CPR-nummer")}
    });
}

function addHyphen(element) {
    let val = $(element).val().split('-').join('');   // Remove dash (-) if mistakenly entered.

    let finalVal = val.match(/.{1,6}/g).join('-');    // Add (-) after 3rd every char.
    $(element).val(finalVal);		// Update the input box.
}