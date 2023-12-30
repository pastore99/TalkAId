const READTEXT = "READTEXT"
const READIMAGES = "READIMAGES"
const IMAGESTOTEXT = "IMAGESTOTEXT"
const TEXTTOIMAGES = "TEXTTOIMAGES"
const CROSSWORD = "CROSSWORD"
const COMPLETETEXT = "COMPLETETEXT"
const RIGHTTEXT = "RIGHTTEXT"
const IMAGESINAROW = 2

const exerciseDiv = $("#exerciseDiv");

$(document).ready(()=>{
  $("#backDiv").click(() => redirect("home"));
  $("#notificationDiv").click(() => redirect("message.jsp")); //TODO: mettere i redirect giusti
  loadExercise(/*TODO: mettere qui la tipologia di esercizio*/);
})

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

function loadExercise(type){
  switch (type) {
  case READTEXT:
    loadReadText();
    break;
  case READIMAGES:
    loadReadImages();
    break;
  case IMAGESTOTEXT:
    loadImagesToText();
    break;
  case TEXTTOIMAGES:
    loadTextToImages();
    break;
  case CROSSWORD:
    loadCrossword();
    break;
  case COMPLETETEXT:
    loadCompleteText();
    break;
  case RIGHTTEXT:
    loadRightText();
    break;
  default:
    //TODO: Errore e torna indietro
  }
}

function loadReadText(){

  let text = $("<p>").text(/*TODO: inserire il testo*/);
  let textDiv = $("<div>").attr("id", "textDiv").append(text);

  let button = $("<button>").click(/*TODO: inizia la registrazione*/);
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);

  let div = $("<div>").attr("id", "readTextDiv").append(textDiv, buttonDiv);

  exerciseDiv.append(div);
}

function loadReadImages(){
  let images = []; //TODO: prendere le varie immagini
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

function loadImagesToText(){
  let images = []; //TODO: prendere le varie immagini
  let textInput = $("<input>").attr("type", "text");
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
    div.append(textInput.attr("id", "textInput"+number));
    if (index === IMAGESINAROW-1){
      exerciseDiv.append(div);
      //Create a new Div
      div = $("<div>").addClass("row");
    }
    index = index + 1;
  });
}

function loadTextToImages(){
  let images = []; //TODO: prendere le varie immagini
  let texts = []; //TODO: Prendere i vari testi
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

function loadCrossword(){
  let matrix = [[]]; //TODO: Prendere la matrice
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

function loadCompleteText(){
  let words = [];
  words.forEach((word, index)=>{
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

function loadRightText(){
  let firstSetOfWords = [];
  let secondSetOfWords = [];

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
  }
}
