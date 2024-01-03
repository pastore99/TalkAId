const READTEXT = "READTEXT"
const READIMAGES = "READIMAGES"
const IMAGESTOTEXT = "IMAGESTOTEXT"
const TEXTTOIMAGES = "TEXTTOIMAGES"
const CROSSWORD = "CROSSWORD"
const COMPLETETEXT = "COMPLETETEXT"
const RIGHTTEXT = "RIGHTTEXT"
const IMAGESINAROW = 2

const EXERCISETYPE = $("#exerciseInfo").data("type");
const USERTYPE = $("#exerciseInfo").data("user");

const exerciseDiv = $("#exerciseDiv");

function startUp(exerciseIS){
  $(document).ready(()=>{
    const EXERCISEINITIALSTATE = parseJSON(exerciseIS);
    $("#backDiv").click(() => redirect("home"));
    $("#notificationDiv").click(() => redirect("messageCenter.jsp")); //TODO: mettere i redirect giusti
    loadExercise(EXERCISETYPE, EXERCISEINITIALSTATE);
  })
}

function parseJSON(json) {
  try {
    const jsonData = JSON.parse(json);
    return jsonData;
  } catch (jsonError) {
    try {
      const jsObject = eval('(' + json + ')');
      return jsObject;
    } catch (objectError) {
      return json;
    }
  }
}

function redirect(where){
  if (where === "home"){
    //TODO: se logopedista -> dashboard, se paziente -> homepage
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
    case COMPLETETEXT:
      loadCSS("../CSS/exerciseSpecificCss/"+COMPLETETEXT.toLowerCase()+".css");
      loadCompleteText(initialState);
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

function loadCSS(cssPath) {
  let link = document.createElement("link");
  link.rel = "stylesheet";
  link.type = "text/css";
  link.href = cssPath;
  document.head.appendChild(link);
}

function loadReadText(initialState){

  let text = $("<p>").text(initialState).addClass("textToRead");
  let textDiv = $("<div>").attr("id", "textDiv").append(text);

  let mic = $("<img>").attr({
    id: 'mic',
    src: '../images/exercise/microphone.svg',
    alt: 'microphoneIcon',
    class: 'micIcons'
  });

  let micActive = $("<img>").attr({
    id: 'micActive',
    src: '../images/exercise/microphoneActive.svg',
    alt: 'microphoneActiveIcon',
    class: 'micIcons'
  });
  micActive.hide();
  let button = $("<button>").click(/*TODO: inizia la registrazione e cambia icone*/).addClass("recordButton");
  button.append(mic, micActive);
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);


  let mainDiv = $("<div>").attr("id", "readTextDiv").append(textDiv, buttonDiv);

  exerciseDiv.append(mainDiv);
}

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
    id: 'mic',
    src: '../images/exercise/microphone.svg',
    alt: 'microphoneIcon',
    class: 'micIcons'
  });

  let micActive = $("<img>").attr({
    id: 'micActive',
    src: '../images/exercise/microphoneActive.svg',
    alt: 'microphoneActiveIcon',
    class: 'micIcons'
  });
  micActive.hide();
  let button = $("<button>").click(/*TODO: inizia la registrazione e cambia icone*/).addClass("recordButton");
  button.append(mic, micActive);

  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);
  let mainDiv = $("<div>").attr("id", "readImagesDiv").append(imagesDiv, buttonDiv);
  exerciseDiv.append(mainDiv);
}

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
      id: "image "+number
    });
    div.append(i);
    div.append($("<input>").attr({
      type: "text",
      id: "textInput "+number,
      class: "textInput",
      placeholder: "Cos'è?"
    }))
    mainDiv.append(div);
    div = $("<div>").addClass("row");
  });

  exerciseDiv.append(mainDiv);
}

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
      id: "input" + number,
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
  exerciseDiv.append(mainDiv);
}

function loadCrossword(initialState){
  let matrix = initialState[0];
  let vertical = initialState[1];
  let horizontal = initialState[2];
  let verticalTextDiv = $("<div>").attr("id", "verticalDiv");
  let horizontalTextDiv = $("<div>").attr("id", "horizontalDiv");
  let mainDiv =  $("<div>").attr("id", "crosswordDiv");

  let index = 0;

  for (let i = 0; i < matrix.length; i++){
    let row = $("<div>").addClass("row");
    for (let j= 0; j < matrix[i].length; j++){
      let element = $("<div>")
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
        let inputText = $("<input>").attr("type", "text");
        inputText.blur(() => {
          oneChar(inputText);
        });
        textDiv.append(inputText);
        element.append(indexDiv, textDiv)
      }
      else{
        element.addClass("textBlock");
        let spaceDiv = $("<div>").addClass("indexDiv");
        let textDiv = $("<div>").addClass("textDiv");
        let inputText = $("<input>").attr("type", "text");
        inputText.blur(() => {
          oneChar(inputText);
        });
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

function oneChar(input){
  input.val(input.val().slice(0,1));
}

function loadCompleteText(initialState){
  let words = initialState;
  let div=$("<div>").addClass("row");
  let mainDiv = $("<div>").attr("id", "completeText");
  words.forEach((word, index)=>{
    //-----------------------------------
    //TODO: Rimuovere, quando si inserisce nel db si tolgono le lettere
    const randomIndex = Math.floor(Math.random() * word.length);
    tempWordArray = word.split('');
    tempWordArray[randomIndex] = "#";
    word = tempWordArray.join('');
  //-------------------------------------
    div.attr("id", "word"+index);
    for (let i=0; i < word.length; i++){
      if(word[i]==="#"){
        div.append($("<div>").append($("<input>").attr({
          type: "text",
          maxLength: 1,
          class: "textInput"
        })));
      }else{
        div.append($("<div>").append($("<p>").text(word[i])));
      }
    }
    mainDiv.append(div);
    div=$("<div>").addClass("row");
  })
  exerciseDiv.append(mainDiv);
}

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
    if(i % 2 === 0){
      div.append($("<p>").text(firstSetOfWords[i]), radio1, radio2, $("<p>").text(secondSetOfWords[i]));
    }else{
      div.append($("<p>").text(secondSetOfWords[i]), radio2, radio1, $("<p>").text(firstSetOfWords[i]));
    }

    mainDiv.append(div);
    div = $("<div>").addClass("row");
  }
  exerciseDiv.append(mainDiv);
}