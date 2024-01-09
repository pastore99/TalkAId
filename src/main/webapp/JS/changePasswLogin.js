var currentSquare = 0;

function focusInput(squareIndex) {
    currentSquare = squareIndex;
    updateSquares();
}

document.addEventListener('keydown', function(event) {
    var key = event.key;
    if (key >= '0' && key <= '9') {
        var squareId = 'square' + currentSquare;
        var square = document.getElementById(squareId);
        square.textContent = key;

        currentSquare++;
        if (currentSquare > 3) {
            currentSquare = 0;
        }
        updateSquares();
    }
});

function updateSquares() {
    for (var i = 0; i < 4; i++) {
        var squareId = 'square' + i;
        var square = document.getElementById(squareId);
        square.style.backgroundColor = (i === currentSquare) ? '#c0c0c0' : '';
    }
}

function b1() {
    document.querySelector("#c1").style.display = "none";
    document.querySelector("#c2").style.display = "";
}

function b2() {
    var data = '';
    for (var i = 0; i < 4; i++) {
        var squareId = 'square' + i;
        var square = document.getElementById(squareId);
        data += square.textContent || '0';
    }
    alert('Dati inseriti: ' + data);
    document.querySelector("#c2").style.display = "none";
    document.querySelector("#c3").style.display = "";
}

function b3() {

    document.querySelector("#c3").style.display = "none";
    document.querySelector("#c4").style.display = "";
}

function b4() {
    window.location.href = 'login.jsp';
}