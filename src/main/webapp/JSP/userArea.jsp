<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="it" style="background-color:#199A8E">

<head>
    <title>TalkAId - User Area</title>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link href="../CSS/dettagliUser.css" rel="stylesheet">
    <link href="#" rel="stylesheet">
</head>
<body onload="controllaPopup();">
<%
    UserData userDate= new UserData();
    User utente=null;
    if (session != null && session.getId() != null) {
        // Ottieni l'utente dalla sessione
        utente = userDate.getUser(session.getAttribute("id"));

        // Se l'utente non � valido o non esiste, reindirizza alla pagina di login
        if (utente == null) {
            response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
        }
    } else {
        // Se la sessione non � attiva, reindirizza alla pagina di login
        response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
    }
    try
    {
        String[] data_di_inizio = String.valueOf(utente.getActivationDate()).split(" ");
        String[] giorno = data_di_inizio[0].split("-");
        PersonalInfo infoutente = new UserRegistry().getPersonalInfo(utente.getId());
        User therapist = userDate.getUser(utente.getIdTherapist());
        PersonalInfo infotherapist = new UserRegistry().getPersonalInfo((therapist.getId()));

%>
<div style="background-color:#199A8E">
    <div class="container rounded bg-white mt-5 mb-5">
        <form  action="<%=request.getContextPath()%>/changeDate" method="post">
        <div class="row">
            <div class="col-md-3 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <img class="rounded-circle mt-5" width="150px" alt="profile_img" src="https://cdn-icons-png.flaticon.com/512/21/21104.png">
                    <%
                        if (session.getAttribute("type") != "therapist"){
                    %>
                    <span class="font-weight-bold">Info Terapia</span>
                    <span class="text-black-50">Medico curante: <span class="font-weight-bold"><%= infotherapist.getFirstname()%> <%= infotherapist.getLastname()%></span></span>
                    <span class="text-black-50">Email del medico: <span class="font-weight-bold"><%= therapist.getEmail()%></span></span>
                    <span class="text-black-50"> Data inizio trattamento: <span class="font-weight-bold"><%= giorno[0]%>/<%= giorno[1]%>/<%= giorno[2]%></span></span>
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="col-md-5 border-right">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Profilo</h4>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <label class="labels">Nome</label>
                            <input required pattern="^[A-Z][a-zA-Z' ]{1,}$" title="Il nome deve contenere solo lettere, spazi, trattini e apostrofi." type="text" class="form-control" placeholder="<%= infoutente.getFirstname()%>" value="<%= infoutente.getFirstname()%>" name="firstname">
                        </div>
                        <div class="col-md-6">
                            <label class="labels">Cognome</label>
                            <input required pattern="^[A-Z][a-zA-Z' ]{1,}$" title="Il cognome deve contenere solo lettere, spazi, trattini e apostrofi." type="text" class="form-control" placeholder="<%= infoutente.getLastname()%>" value="<%= infoutente.getLastname()%>" name = "lastname">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12">
                            <label class="labels">Telefono</label>
                            <input required title="Il numero deve essere obbligatoriamente di 10 cifre" pattern="^\d{10}$" type="text" class="form-control" placeholder="<%= infoutente.getPhone()%>" value="<%= infoutente.getPhone()%>" name="phonenumber">
                        </div>
                        <div class="col-md-12">
                            <label class="labels">Indirizzo</label>
                            <input required title="Massimo 30 caratteri" pattern=".{1,30}" type="text" class="form-control" placeholder="<%= infoutente.getAddress()%>" value="<%= infoutente.getAddress()%>" name="address">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center experience">
                        <span>Private Settings</span>
                    </div>
                    <br>
                    <div class="col-md-12">
                        <label class="labels">Email ID</label>
                        <input required title="inserisci una mail valida" type="email" class="form-control" placeholder="<%= utente.getEmail()%>" value="<%= utente.getEmail()%>" name ="email">
                    </div>
                </div>

                <div class="mt-5 text-center" style="margin-top: 0px !important; margin-bottom: 30px !important;">
                    <a class="btn btn-primary profile-button" href="../JSP/changePassw.jsp" style="margin-bottom: 10px !important;">Cambia Password</a>
                    <br>
                    <button class="btn btn-primary profile-button" type="submit" value="Submit"style="margin-bottom: 10px !important;">Salva Profilo</button>
                    <br>
                </div>
            </div>
        </div>
        </form>

        <div class="mt-5 text-center" style="margin-top: 0px !important; margin-bottom: 30px !important;">
            <div id="popup" >
                <div id="popup-uscita">
                    <p id="Contenuto"></p>
                    <div class="input">
                        <button id="cancella" class="btn btn-primary profile-button" onclick="chiudiPopup()">Chiudi</button>
                    </div>
                </div>
            </div>
            <br>
            <form action="<%=request.getContextPath()%>/logout" method="post">
                <button class="btn btn-primary profile-button" type="submit" style="margin-bottom: 10px !important; background-color: red;">LogOut</button>
            </form>
        </div>
        <br>

        <div id="navbarContainer">
            <jsp:include page="navbar.jsp"></jsp:include>
            <div id="userInfo" hidden
                 data-type = "<%= session.getAttribute("type")%>"
            ></div>
        </div>
    </div>
</div>
<%
    }
    catch (Exception e)
    {
        System.err.println(e.getMessage());
    }
%>


<script>
    function controllaPopup() {
        let urlParams = new URLSearchParams(window.location.search);
        let errorMessage = urlParams.get('errorMessage');
        let contenuto = document.getElementById("Contenuto");


        if (errorMessage && errorMessage.trim() !== "") {
            contenuto.innerHTML=errorMessage;
            document.getElementById("popup").style.display = "block";
        } else {
            document.getElementById("popup").style.display = "none";
        }
    }
    function chiudiPopup() {
        document.getElementById('popup').style.display = 'none';
    }
    document.querySelector('form').addEventListener('submit', function(event) {
        let form = event.target;
        if (!form.checkValidity()) {
            event.preventDefault();
            alert('Il form contiene errori. Correggi gli errori e prova di nuovo.');
        }
    });

</script>
</body>
</html>