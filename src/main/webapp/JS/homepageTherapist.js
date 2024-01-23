$(document).ready(function () {
    $("#iaIconSelected").hide();
    $("#homeIcon").hide();

    $("#home").click(() => redirect("home"));
    $("#message").click(() => redirect("messageCenter.jsp"));
    $("#agenda").click(() => redirect("schedule.jsp"));
    $("#ia").click(() => redirect("AI"));
    $("#docInfo").click(() => redirect("userArea.jsp"));


    let tableContainer = $("#tableContainer");

    // Aggiungi un gestore di eventi all'input di ricerca
    $('#searchInput').on('input', function () {
        let searchText = $(this).val().toLowerCase();

        // Nascondi tutte le righe
        tableContainer.find('tbody').css('marginTop', '0');  // Using jQuery methods

        // Filtra e mostra solo le righe che corrispondono alla ricerca
        $('tbody tr').each(function () {
            let name = $(this).find('td:eq(1)').text().toLowerCase();
            let lastName = $(this).find('td:eq(2)').text().toLowerCase();

            if (name.includes(searchText) || lastName.includes(searchText)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
});

function showAI(){
    $("#homeSelectedIcon").hide();
    $("#homeIcon").show()
    $("#aiContainer").show();
    $("#tableContainer").hide();
    $("#iaIcon").hide();
    $("#iaIconSelected").show();
}

function hideAI(){
    $("#homeSelectedIcon").show();
    $("#homeIcon").hide()
    $("#aiContainer").hide();
    $("#tableContainer").show();
    $("#iaIcon").show();
    $("#iaIconSelected").hide();
}



function openInvitePopup() {
    $("#invitePopup").show();
}

function viewPatient(i){
    window.location.href = "viewPatientInfo.jsp?patientID="+i;
}


function redirect(where){
    if(where==="AI" || where === "home"){
        let path = window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);
        console.log(path);
        if(path!=="homepageTherapist.jsp"){
            window.location.href = "homepageTherapist.jsp";
        }else if(where==="AI"){
            showAI();
        }else if(where === "home"){
            hideAI();
        }
    }else{
        window.location.href = where;
    }
}