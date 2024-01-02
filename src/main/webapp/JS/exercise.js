const READTEXT = "READTEXT"
const READIMAGES = "READIMAGES"
const IMAGESTOTEXT = "IMAGESTOTEXT"
const TEXTTOIMAGES = "TEXTTOIMAGES"
const CROSSWORD = "CROSSWORD"
const COMPLETETEXT = "COMPLETETEXT"
const RIGHTTEXT = "RIGHTTEXT"
const IMAGESINAROW = 2
const exercise = $("#exerciseInfo")

const EXERCISETYPE = exercise.data("type");

const exerciseDiv = $("#exerciseDiv");

function startUp(exerciseIS){
  $(document).ready(()=>{
    const EXERCISEINITIALSTATE = parseJSON(exerciseIS);
    $("#backDiv").click(() => redirect("home"));
    $("#notificationDiv").click(() => redirect("message.jsp")); //TODO: mettere i redirect giusti
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
    /*if (user === logopedista){
      window.location.href = homepagePaziente
    }else {
      window.location.href = homepageLogopedista
    }*/
  }
  else{
    window.location.href = where;
  }
}

function loadExercise(type, initialState){
  switch (type.toUpperCase()) {
  case READTEXT:
    loadReadText(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case READIMAGES:
    loadReadImages(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case IMAGESTOTEXT:
    loadImagesToText(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case TEXTTOIMAGES:
    loadTextToImages(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case CROSSWORD:
    loadCrossword(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case COMPLETETEXT:
    loadCompleteText(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  case RIGHTTEXT:
    loadRightText(initialState);
    //loadCSS("path/al/tuo/readtext.css");
    break;
  default:
    setTimeout(()=> {
      alert("Ci scusiamo del disagio! Le consigliamo di riprovare tra pochi minuti");
      window.location.href = "homepagepatient" //TODO:mettere la homepage del paziente
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

  let text = $("<p>").text(initialState);
  let textDiv = $("<div>").attr("id", "textDiv").append(text);

  let button = $("<button>").click(/*TODO: inizia la registrazione*/);
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);

  let div = $("<div>").attr("id", "readTextDiv").append(textDiv, buttonDiv);

  exerciseDiv.append(div);
}

function loadReadImages(initialState){
  let images = initialState;
  let index = 0;
  let number = 0
  let div = $("<div>").addClass("row");

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
      exerciseDiv.append(div);
      //Create a new Div
      div = $("<div>").addClass("row");
    }
    index = index + 1;
  });
  let button = $("<button>").click(/*TODO: inizia la registrazione*/);
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);
  exerciseDiv.append(buttonDiv);
}

function loadImagesToText(initialState){
  let images = initialState;
  let index = 0;
  let number = 0
  let div = $("<div>").addClass("row");

  images.forEach((imagePath) => {
    number = number + 1;
    if (index === IMAGESINAROW){
      index = 0;
    }
    let i = $("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass",
      id: "image "+number
    });
    div.append(i);
    div.append($("<input>").attr({
      type: "text",
      id: "textInput "+number
    }))
    if (index === IMAGESINAROW-1){
      exerciseDiv.append(div);
      //Create a new Div
      div = $("<div>").addClass("row");
    }
    index = index + 1;
  });
}

function loadTextToImages(initialState){
  let images = initialState[0]
  let texts = initialState[1]
  let index = 0;
  let number = 0
  let div = $("<div>").addClass("row");

  images.forEach((imagePath) => {
    number = number + 1;
    if (index === IMAGESINAROW){
      index = 0;
    }
    let i = $("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass",
      id: "image"+number
    });
    div.append(i);
    div.append($("<p>").attr("id", "text"+number).text(texts[number]));
    div.append($("<input>").attr({
      type: "number",
      id: "input " + number,
      max: images.length
    }));
    if (index === IMAGESINAROW-1){
      exerciseDiv.append(div);
      //Create a new Div
      div = $("<div>").addClass("row");
    }
    index = index + 1;
  });
}

function loadCrossword(initialState){
  let matrix = initialState;
  let vertical = [];
  let horizontal = [];
  let crosswordDiv = $("<div>");
  let verticalTextDiv = $("<div>");
  let horizontalTextDiv = $("<div>");

  for (let i = 0; i < matrix.length; i++){
    let row = $("<div>")
    for (let j=0; j < matrix[i].length; j++){
      let div = $("<div>")
      div.attr("id", i+"_"+j);
      if (matrix[i][j]==="#"){
        row.append(div)
      }
      else if (typeof matrix[i][j] === 'number'){
        div.addClass("indexCrossword");
        row.append(div);
      }
      else{
        div.addClass("borderCrossword");
        row.append(div);
        //TODO: Provare a mettere qui input text per renderlo dinamico
      }
    }
    crosswordDiv.append(row);
  }
  exerciseDiv.append(crosswordDiv);
  vertical.forEach((phrase)=>{
    let p = $("<p>").text(phrase).addClass("phrases");
    verticalTextDiv.append(p);
  });
  horizontal.forEach((phrase)=>{
    let p = $("<p>").text(phrase).addClass("phrases");
    horizontalTextDiv.append(p);
  });
  exerciseDiv.append(verticalTextDiv);
  exerciseDiv.append(horizontalTextDiv);

}

function loadCompleteText(initialState){
  let words = initialState;
  words.forEach((word, index)=>{
    //TODO: Rimuovere, quando si inserisce nel db si tolgono le lettere
    const randomIndex = Math.floor(Math.random() * word.length);
    tempWordArray = word.split('');
    tempWordArray[randomIndex] = "#";
    word = tempWordArray.join('');

    let div=$("<div>")
    div.attr("id", "word"+index);
    for (let i=0; i < word.length; i++){
      if(word[i]==="#"){
        div.append($("<div>").append($("<input>").attr({
          type: "text",
          maxLength: 1
        })));
      }else{
        div.append($("<div>").append($("<p>").text(word[i])));
      }
    }
    exerciseDiv.append(div);
  })
}

function loadRightText(initialState){
  let firstSetOfWords = [];
  let secondSetOfWords = [];
  initialState.forEach((pair)=>{
    firstSetOfWords.push(pair[0]);
    secondSetOfWords.push(pair[1]);
  });

  for(let i=0; i<firstSetOfWords.length; i++){
    let div = $("<div>");
    div.attr("id", i);
    let radio1 = $("<input>").attr({
      type: "radio",
      value: firstSetOfWords[i]
    });
    let radio2 = $("<input>").attr({
      type: "radio",
      value: secondSetOfWords[i]
    });
    div.append($("<p>").text(firstSetOfWords[i]), radio1, radio2, $("<p>").text(secondSetOfWords[i]));
    exerciseDiv.append(div);
  }
}
