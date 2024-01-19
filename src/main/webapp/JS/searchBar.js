//search bar for name and surname
$(document).ready(function () {
    let tableContainer = document.getElementById('tableContainer');

    // Aggiungi un gestore di eventi all'input di ricerca
    $('#searchInput').on('input', function () {
        let searchText = $(this).val().toLowerCase();

        // Nascondi tutte le righe
        tableContainer.getElementsByTagName('tbody')[0].style.marginTop = '0';

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