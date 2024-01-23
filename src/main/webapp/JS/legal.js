// Function to show the popup
function mostraPopup() {
    document.getElementById("overlay").style.display = "block";
}

// Function called when the user accepts the sharing
function accettaCondivisione() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "../register?type=analytics&accept=true", true);
    xhr.send();

    document.getElementById("overlay").style.display = "none";
}

// Show the popup when the page is fully loaded
window.onload = mostraPopup;

function impostaOrario() {
    event.preventDefault();
    let start = document.getElementById('startTime').value = '';
    let end = document.getElementById('endTime').value = '';
    let time = start + "|" + end;
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "../register?type=emailTime&time="+time, true);
    xhr.send();
}

function nonAccetto() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "../register?type=analytics&accept=false", true);
    xhr.send();

    document.getElementById("overlay").style.display = "none";
}

function validateTimes() {
    let startTime = new Date();
    let endTime = new Date();

    // Convert time strings into Date objects
    startTime.setHours(document.getElementById('startTime').value.split(':')[0]);
    startTime.setMinutes(document.getElementById('startTime').value.split(':')[1]);
    endTime.setHours(document.getElementById('endTime').value.split(':')[0]);
    endTime.setMinutes(document.getElementById('endTime').value.split(':')[1]);

    // Check if the start time is greater than the end time
    if (startTime > endTime) {
        alert("L'ora di inizio deve essere inferiore all'ora di fine");
        document.getElementById('startTime').value = '';
        document.getElementById('endTime').value = '';
    }
}