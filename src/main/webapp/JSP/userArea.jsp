<%@ page import="model.service.user.UserData" %>
<%@ page import="model.entity.User" %>
<%@ page import="model.entity.PersonalInfo" %>
<%@ page import="model.service.user.UserRegistry" %>
<!DOCTYPE html>
<html lang="it" style="background-color:#199A8E">

<head>
    <title>TalkAId - User Area</title>
    <link rel="icon" href="../images/siteIco.png" type="image/png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
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

        // Se l'utente non è valido o non esiste, reindirizza alla pagina di login
        if (utente == null) {
            response.sendRedirect("/TalkAID_war_exploded/JSP/login.jsp");
        }
    } else {
        // Se la sessione non è attiva, reindirizza alla pagina di login
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
                    <span class="text-black-50">Dott.assegnato: <%= infotherapist.getFirstname()%> <%= infotherapist.getLastname()%></span>
                    <span class="text-black-50">Email.Dott: <%= therapist.getEmail()%></span>
                    <span class="text-black-50">Inizio trattamento: <%= giorno[0]%>/<%= giorno[1]%>/<%= giorno[2]%></span>
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
                            <input required pattern="^[a-zA-Zàáâäãå????èéêë??ìíîï??òóôöõøùúûü??ÿý??ñç???ÀÁÂÄÃÅ?????ÈÉÊËÌÍÎÏ???ÒÓÔÖÕØÙÚÛÜ???Ý??ÑßÇ?Æ????ð ,.'-]+$" title="Il nome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." type="text" class="form-control" placeholder="<%= infoutente.getFirstname()%>" value="<%= infoutente.getFirstname()%>" name="firstname">
                        </div>
                        <div class="col-md-6">
                            <label class="labels">Cognome</label>
                            <input required pattern="^[a-zA-Zàáâäãå????èéêë??ìíîï??òóôöõøùúûü??ÿý??ñç???ÀÁÂÄÃÅ?????ÈÉÊËÌÍÎÏ???ÒÓÔÖÕØÙÚÛÜ???Ý??ÑßÇ?Æ????ð ,.'-]+$" title="Il cognome deve contenere solo lettere, spazi, trattini, apostrofi e accenti." type="text" class="form-control" placeholder="<%= infoutente.getLastname()%>" value="<%= infoutente.getLastname()%>" name = "lastname">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12">
                            <label class="labels">Telefono</label>
                            <input required title="inserisci un numero di telefono valido" pattern="^((\+|00)39)?\s?3[0-9]{2}[-.\s]?[0-9]{6,7}$" type="text" class="form-control" placeholder="<%= infoutente.getPhone()%>" value="<%= infoutente.getPhone()%>" name="phonenumber">
                        </div>
                        <div class="col-md-12">
                            <label class="labels">Indirizzo</label>
                            <input required title="inserisci un indirizzo valido" pattern="^[a-zA-Z\d\.\s,-]{5,}$" type="text" class="form-control" placeholder="<%= infoutente.getAddress()%>" value="<%= infoutente.getAddress()%>" name="address">
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
        e.printStackTrace();
    }
%>


<script>
    function controllaPopup() {
        var urlParams = new URLSearchParams(window.location.search);
        var errorMessage = urlParams.get('errorMessage');
        var contenuto = document.getElementById("Contenuto");


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

</script>
</body>
</html>