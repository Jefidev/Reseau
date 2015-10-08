<%-- 
    Document   : index
    Created on : Oct 8, 2015, 7:54:10 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Réservation : connexion</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <body>
        <h1>Connexion</h1>
            <form method = "POST" action = "formulaire">
		<label for = "login">Login </label><input type = "text" name = "login" id = "login" required/></br>
		<label for = "mdp">Mot de passe </label><input type = "password" name = "mdp" id = "mdp" required/></br>
		<input type = "checkbox" name = "nouveau" id = "nouveau"/><label for = "nouveau">Je suis un nouvel utilisateur</label></br>
		<input type = "submit" value = "Connexion"/>
            </form>
            <%
                if(session.getAttribute("erreur") != null)
                {
            %>
            <p><%=session.getAttribute("erreur")%></p>
            <%}%>
    </body>
</html>
