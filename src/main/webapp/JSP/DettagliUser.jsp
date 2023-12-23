<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page import="model.entity.User" %> <!--import dell'user-->
<%/*
	User userpage = (User) session.getAttribute("utente");
    if(userpage == null) {
        response.sendRedirect("logIn.jsp");    //send redirect per il controllo dell'accesso in un area riservata
        return;
    }*/
%>
<!DOCTYPE html>
<html lang="it" style="background-color:#199A8E">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Profile</title>
	<link href="../css/lib/BootstrapV5_0_0-alpha1.css" rel="stylesheet">
	<link href="../css/dettagliUser.css" rel="stylesheet">
	<link href="#" rel="stylesheet">
	<script type="text/javascript" src="./js/lib/jQueryUser.js"></script>
</head>
<body>
	<!-- include file="header.jsp"   per agguingere un possibile header-->
	<script src="dettagliuser.js"></script>
	<form action="<%= request.getContextPath() %>/profile?action=mod" method="post" style="background-color:#199A8E">
	<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
            	<img class="rounded-circle mt-5" width="150px" alt="profile_img" src="https://cdn-icons-png.flaticon.com/512/21/21104.png">
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
                    	<input required title="inserisci un nome valido" pattern="^[a-zA-Z\d\.]{5,}$" type="text" class="form-control" placeholder="getNome" value="getNome" name="nome">
                    </div>
                    <div class="col-md-6">
                    	<label class="labels">Cognome</label>
                    	<input required title="inserisci un cognome valido" pattern="^[a-zA-Z\d\.\s,-]{5,}$" type="text" class="form-control" placeholder="getCognome" value="getCognome" name="cognome">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12">
                    	<label class="labels">Telefono</label>
                    	<input required title="inserisci un numero di telefono valido" pattern="(\d{3}\s?){2}(\d{4}\s?){1}" type="text" class="form-control" placeholder="getTelefono" value="getTelefono" name="telefono">
                    </div>
                    <div class="col-md-12">
	                    <label class="labels">Indirizzo</label>
	                    <input required title="inserisci un indirizzo valido" pattern="^[a-zA-Z\d\.\s,-]{5,}$" type="text" class="form-control" placeholder="getIndirizzo" value="getIndirizzo" name="ind">
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
                	<input required title="inserisci una mail valida" type="email" class="form-control" placeholder="getEmail" value="getEmail" name="email">
                </div>
                <div class="col-md-12">
                	<label class="labels">Password</label>
                	<input required pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="inserisci minimo 8 caratteri con maiuscole, minuscole e numeri." type="password" class="form-control" placeholder="insert new password" value="getPassword" name="passw">
                </div>
            </div>
            	
                <div class="mt-5 text-center" style="margin-top: 0px !important; margin-bottom: 30px !important;">
                <%
				// Check user credentials
					/*
				Boolean adminRoles = (Boolean) session.getAttribute("logopedistRole");
				if (adminRoles.booleanValue())
				{	*/
					%>
                	<a class="btn btn-primary profile-button" href="./admin/adminPage.jsp" style="margin-bottom: 10px !important;">Vai alla pagina di Gestione Pazienti</a>
                	<br>
					<% 
				//}
				%>
				<button class="btn btn-primary profile-button" type="submit" value="Submit"style="margin-bottom: 10px !important;">Salva Profilo</button>
				<br>
                </div>
        </div>
    </div>
</div>
</form>
</body>
</html>