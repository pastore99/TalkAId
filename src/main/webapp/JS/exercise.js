//Costanti ed elementi
const READTEXT = "READTEXT"
const READIMAGES = "READIMAGES"
const IMAGESTOTEXT = "IMAGESTOTEXT"
const TEXTTOIMAGES = "TEXTTOIMAGES"
const CROSSWORD = "CROSSWORD"
const RIGHTTEXT = "RIGHTTEXT"
const IMAGESINAROW = 2

const EXERCISETYPE = $("#exerciseInfo").data("type");
const USERTYPE = $("#exerciseInfo").data("user");

const exerciseDiv = $("#exerciseDiv");

//Funzione di Startup, gestisce redirect dell'header e carica l'esercizio corretto
function startUp(exerciseIS){
  $(document).ready(()=>{
    const EXERCISEINITIALSTATE = parseJSON(exerciseIS);
    $("#backDiv").click(() => redirect("home"));
    $("#notificationDiv").click(() => redirect("messageCenter.jsp"));
    loadExercise(EXERCISETYPE, EXERCISEINITIALSTATE);
  })
}

//Gestione JSON dal DB
function parseJSON(json) {
  try {
    const jsonData = JSON.parse(json);
    return jsonData;
  } catch (jsonError) {
    try {
      const jsObject = eval("(" + json + ")");
      return jsObject;
    } catch (objectError) {
      return json;
    }
  }
}

//Gestione redirect
function redirect(where){
  if (where === "home"){
    if (USERTYPE === "patient"){
      window.location.href = "homepagepatient.jsp";
    }else if (USERTYPE === "therapist"){
      window.location.href = "homepagelogopedist.jsp";
    }
  }
  else{
    window.location.href = where;
  }
}

//Caricamento dinamico dell'esercizio e relativo css
function loadExercise(type, initialState){
  switch (type.toUpperCase()) {
    case READTEXT:
      loadCSS("../CSS/exerciseSpecificCss/"+READTEXT.toLowerCase()+".css");
      loadReadText(initialState);
      break;
    case READIMAGES:
      loadCSS("../CSS/exerciseSpecificCss/"+READIMAGES.toLowerCase()+".css");
      loadReadImages(initialState);
      break;
    case IMAGESTOTEXT:
      loadCSS("../CSS/exerciseSpecificCss/"+IMAGESTOTEXT.toLowerCase()+".css");
      loadImagesToText(initialState);
      break;
    case TEXTTOIMAGES:
      loadCSS("../CSS/exerciseSpecificCss/"+TEXTTOIMAGES.toLowerCase()+".css");
      loadTextToImages(initialState);
      break;
    case CROSSWORD:
      loadCSS("../CSS/exerciseSpecificCss/"+CROSSWORD.toLowerCase()+".css");
      loadCrossword(initialState);
      break;
    case RIGHTTEXT:
      loadCSS("../CSS/exerciseSpecificCss/"+RIGHTTEXT.toLowerCase()+".css");
      loadRightText(initialState);
      break;
    default:
      setTimeout(()=> {
        alert("Ci scusiamo del disagio! Le consigliamo di riprovare tra pochi minuti");
        redirect("home");
      }, 3000);
  }
}

//Caricamento dinamico del CSS relativo all'esercizio
function loadCSS(cssPath) {
  let link = document.createElement("link");
  link.rel = "stylesheet";
  link.type = "text/css";
  link.href = cssPath;
  document.head.appendChild(link);
}

//----------------------------------------------------------------------------------------------------------------------
//                                            Creazione degli esercizi
//----------------------------------------------------------------------------------------------------------------------

//Leggi testo
function loadReadText(initialState){

  let text = $("<p>").text(initialState).addClass("textToRead");
  let textDiv = $("<div>").attr("id", "textDiv").append(text);

  let mic = $("<img>").attr({
    id: "mic",
    src: "../images/exercise/microphone.svg",
    alt: "microphoneIcon",
    class: "micIcons"
  });

  let micActive = $("<img>").attr({
    id: "micActive",
    src: "../images/exercise/microphoneActive.svg",
    alt: "microphoneActiveIcon",
    class: "micIcons"
  });
  let startButton = $("<button>").click(/*TODO: inizia la registrazione, toggle bottoni*/).addClass("recordButton").attr("id", "startRecord");
  let stopButton = $("<button>").click(/*TODO: termina la registrazione e la salva, toggle bottoni*/).addClass("recordButton").attr("id", "stopRecord");
  startButton.append(mic);
  stopButton.append(micActive);
  stopButton.hide();
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(startButton, stopButton);


  let mainDiv = $("<div>").attr("id", "readTextDiv").append(textDiv, buttonDiv);

  exerciseDiv.append(mainDiv);
}

//Leggi immagini
function loadReadImages(initialState){
  let images = initialState;
  let index = 0;
  let number = 0
  let div = $("<div>").addClass("row");
  let imagesDiv = $("<div>").attr("id", "imagesDiv");

  images.forEach((imagePath) => {
    number = number + 1;
    if (index === IMAGESINAROW){
      index = 0;
    }
    let i = $("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass",
      id: "image " + number
    });
    div.append(i);
    if (index === IMAGESINAROW-1){
      imagesDiv.append(div);
      //Create a new Div
      div = $("<div>").addClass("row");
    }
    index = index + 1;
  });

  let mic = $("<img>").attr({
    id: "mic",
    src: "../images/exercise/microphone.svg",
    alt: "microphoneIcon",
    class: "micIcons"
  });

  let micActive = $("<img>").attr({
    id: "micActive",
    src: "../images/exercise/microphoneActive.svg",
    alt: "microphoneActiveIcon",
    class: "micIcons"
  });
  let startButton = $("<button>").click(/*TODO: inizia la registrazione, toggle bottoni*/).addClass("recordButton").attr("id", "startRecord");
  let stopButton = $("<button>").click(/*TODO: termina la registrazione e la salva, toggle bottoni*/).addClass("recordButton").attr("id", "stopRecord");
  startButton.append(mic);
  stopButton.append(micActive);
  stopButton.hide();

  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(startButton, stopButton);
  let mainDiv = $("<div>").attr("id", "readImagesDiv").append(imagesDiv, buttonDiv);
  exerciseDiv.append(mainDiv);
}

//Descrivi Immagini
function loadImagesToText(initialState){
  let images = initialState;
  let number = 0;
  let div = $("<div>").addClass("row");
  let mainDiv = $("<div>").attr("id", "imagesToTextDiv");

  images.forEach((imagePath) => {
    number = number + 1;
    let i = $("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass",
      id: "image"+number
    });
    div.append(i);
    div.append($("<input>").attr({
      type: "text",
      id: "input"+number,
      class: "textInput",
      placeholder: "Cos'è?"
    }))
    mainDiv.append(div);
    div = $("<div>").addClass("row");
  });
  let buttonDiv = $("<div>").attr("id", "buttonDiv");
  let submitButton = $("<button>").click(()=>{saveITT(number)}).addClass("submitButton").html("Completa Esercizio");
  buttonDiv.append(submitButton);
  exerciseDiv.append(mainDiv, buttonDiv);
}

//Associa testo ad immagini
function loadTextToImages(initialState){
  let images = initialState[0];
  let texts = initialState[1];
  let number = 0;
  let div = $("<div>").addClass("row");
  let inputDiv = $("<div>").addClass("inputDiv");
  let imageDiv = $("<div>").addClass("imageDiv");
  let mainDiv = $("<div>").attr("id", "textToImagesDiv");

  images.forEach((imagePath) => {
    number = number + 1;

    imageDiv.append($("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass",
      id: "image"+number
    }));
    imageDiv.append($("<p>").text(number).addClass("imageNumber"));

    inputDiv.append($("<p>").attr("id", "text"+number).text(texts[number-1]).addClass("text"));
    inputDiv.append($("<input>").attr({
      type: "number",
      id: "input"+ number,
      max: images.length,
      class: "numberInput"
    }));
    div.append(inputDiv);
    div.append(imageDiv);

    mainDiv.append(div);
    div = $("<div>").addClass("row");
    inputDiv = $("<div>").addClass("inputDiv");
    imageDiv = $("<div>").addClass("imageDiv");
  });
  let buttonDiv = $("<div>").attr("id", "buttonDiv");
  let submitButton = $("<button>").click(() => {saveTTI(number)}).addClass("submitButton").html("Completa Esercizio");
  buttonDiv.append(submitButton);
  exerciseDiv.append(mainDiv, buttonDiv);
}

//Cuciverba
function loadCrossword(initialState){
  let matrix = initialState[0];
  let vertical = initialState[1];
  let horizontal = initialState[2];
  let verticalTextDiv = $("<div>").attr("id", "verticalDiv");
  let horizontalTextDiv = $("<div>").attr("id", "horizontalDiv");
  let mainDiv =  $("<div>").attr("id", "crosswordDiv");

  let buttonDiv = $("<div>").attr("id", "buttonDiv");
  let submitButton = $("<button>").click(()=> {saveCW(matrix.length, matrix[1].length)}).addClass("submitButton").html("Completa Esercizio");
  buttonDiv.append(submitButton);
  exerciseDiv.append(buttonDiv);



  let index = 0;

  for (let i = 0; i < matrix.length; i++){
    let row = $("<div>").addClass("row");
    for (let j= 0; j < matrix[i].length; j++){
      let element = $("<div>");
      let inputText = $("<input>").attr("type", "text");
      inputText.blur(() => {
        inputText.val(inputText.val().slice(0,1));
      });

      element.attr("id", i+"_"+j);
      if (matrix[i][j]==="#"){
        let blackBox = $("<div>").addClass("blackBox");
        element.addClass("blackBlock");
        element.append(blackBox);
      }
      else if (matrix[i][j] === "*"){
        index++;
        element.addClass("indexBlock");
        let indexDiv = $("<div>").addClass("indexDiv").text(index);
        let textDiv = $("<div>").addClass("textDiv");
        textDiv.append(inputText);
        element.append(indexDiv, textDiv)
      }
      else{
        element.addClass("textBlock");
        let spaceDiv = $("<div>").addClass("indexDiv");
        let textDiv = $("<div>").addClass("textDiv");
        textDiv.append(inputText);
        element.append(spaceDiv, textDiv);
      }
      row.append(element);
    }
    mainDiv.append(row);
  }
  exerciseDiv.append(mainDiv);

  verticalTextDiv.append($("<h2>").text("Parole verticali:"));
  vertical.forEach((phrase)=>{
    let p = $("<p>").text(phrase).addClass("phrases");
    verticalTextDiv.append(p);
  });

  horizontalTextDiv.append($("<h2>").text("Parole Orizzontali:"));
  horizontal.forEach((phrase)=>{
    let p = $("<p>").text(phrase).addClass("phrases");
    horizontalTextDiv.append(p);
  });
  exerciseDiv.append(verticalTextDiv);
  exerciseDiv.append(horizontalTextDiv);
}

//Parola corretta
function loadRightText(initialState){
  let firstSetOfWords = [];
  let secondSetOfWords = [];
  initialState.forEach((pair)=>{
    firstSetOfWords.push(pair[0]);
    secondSetOfWords.push(pair[1]);
  });

  let div = $("<div>").addClass("row");
  let mainDiv = $("<div>").attr("id", "righttextdiv");
  for(let i=0; i<firstSetOfWords.length; i++){
    div.attr("id", i);
    let radio1 = $("<input>").attr({
      type: "radio",
      value: firstSetOfWords[i],
      id: "radio"+i,
      name: "word"+i
    });
    let radio2 = $("<input>").attr({
      type: "radio",
      value: secondSetOfWords[i],
      id: "radio"+i,
      name: "word"+i
    });
    if(Math.random() < 0.5){
      div.append($("<p>").text(firstSetOfWords[i]), radio1, radio2, $("<p>").text(secondSetOfWords[i]));
    }else{
      div.append($("<p>").text(secondSetOfWords[i]), radio2, radio1, $("<p>").text(firstSetOfWords[i]));
    }

    mainDiv.append(div);
    div = $("<div>").addClass("row");
  }
  let buttonDiv = $("<div>").attr("id", "buttonDiv");
  let submitButton = $("<button>").click(()=>{saveCT(secondSetOfWords.length)}).addClass("submitButton").html("Completa Esercizio");
  buttonDiv.append(submitButton);
  exerciseDiv.append(mainDiv, buttonDiv);
}

//----------------------------------------------------------------------------------------------------------------------
//                                            Salvataggio dell'esecuzione
//----------------------------------------------------------------------------------------------------------------------

//Read Text -> Prendi l'audio
//Read Images -> Prendi l'audio
function saveReadExercise(){

}

//Images to text -> Prendi path immagine e testo
function saveITT(n) {
  let execution = {};
  for (let i = 1; i < n + 1; i++) {
    execution[$("#image" + i).attr("src")] = $("#textInput" + i).val();
  }
  //Lo salva bene > va messo nel db e fare redirect
}

//Text to Images -> Prendi path immagine e testo
function saveTTI(n) {
  let execution = {};
  for (let i = 1; i < n + 1; i++) {
    execution[$("#image" + $("#input"+i).val()).attr("src")] = $("#text" + i).text();
  }
  //Lo salva bene > va messo nel db e fare redirect
}

//Crossword -> Crea la matrice
function saveCW(r, c){
  let execution = createMatrix(r, c);

  for(let i = 0; i < r; i++){
    for(let k = 0; k < c; k++){
      let element = $("#"+i+"_"+k);
      if (element.find("input[type='text']").length > 0){
        execution[i][k] = element.find("input[type='text']").val().toLowerCase();
      }else if(element.find(".blackBox").length > 0){
        execution[i][k] = "#";
      }
    }
  }
  saveExecution(execution)
}
function createMatrix(r, c) {
  //Crea una riga, in una riga crea una colonna tutta vuota, ripeti per tutte le righe
  return Array.from({ length: r }, () => Array.from({ length: c }, () => null));
}

//Complete text -> prendi le parole scelte
function saveCT(n){
  let execution = [];
  for(let i= 0; i < n; i++){
    execution.push($("input[name='word" + i + "']:checked").val());
  }
  saveExecution(execution)
}

function saveExecution(execution){
  $("#buttonDiv > button").prop("disabled", true).text("Esercizio Inviato!");
  $.post({
    url: "../exerciseLogger",
    contentType: "application/json",
    data: JSON.stringify(execution)
  }).done(() => {
    redirect("home");
  }).fail((error) => {
    console.log("Errore durante la chiamata Post:"+error);
    redirect("500.html")
  })
}