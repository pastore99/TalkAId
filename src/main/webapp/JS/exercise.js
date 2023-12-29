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
  if(type === READTEXT){
    loadReadText();
  }else if(type === READIMAGES){
    loadReadImages();
  }else if(type === IMAGESTOTEXT){
    loadImagesToText();
  }else if(type === TEXTTOIMAGES){
    loadTextToImages();
  }else if(type === CROSSWORD){
    loadCrossword();
  }else if(type === COMPLETETEXT){
    loadCompleteText();
  }else if(type === RIGHTTEXT){
    loadRightText();
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
  let div = $("<div>").attr("class", "row");

  images.forEach((imagePath) => {
    number = number + 1;
    if (index === IMAGESINAROW){
      index = 0;
    }
    let i = $("<img>").attr({
      src: imagePath,
      alt: "image "+number,
      class: "imageClass"
    });
    div.append(i);
    if (index === IMAGESINAROW-1){
      exerciseDiv.append(div);
      //Create a new Div
      div = $("<div>").attr("class", "row");
    }
    index = index + 1;
  });
  let button = $("<button>").click(/*TODO: inizia la registrazione*/);
  let buttonDiv = $("<div>").attr("id", "buttonDiv").append(button);
  exerciseDiv.append(buttonDiv);
}

function loadImagesToText(){

}

function loadTextToImages(){

}

function loadCrossword(){

}

function loadCompleteText(){

}

function loadRightText(){

}