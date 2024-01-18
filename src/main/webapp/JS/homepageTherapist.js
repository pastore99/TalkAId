$(document).ready(function () {
    $("#home").click(() => redirect("homepageTherapist.jsp"));
    $("#message").click(() => redirect("messageCenter.jsp"));
    $("#agenda").click(() => redirect("schedule.jsp"));
    $("#profile").click(() => redirect("userArea.jsp"));


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



function openInvitePopup() {
    $("#invitePopup").show();
}

function viewPatient(i){
    window.location.href = "viewPatientInfo.jsp?patientID="+i;
}


function redirect(where){
    window.location.href = where;
}