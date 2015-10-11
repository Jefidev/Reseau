<%-- 
    Document   : reponseReservation
    Created on : Oct 12, 2015, 12:27:41 AM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation reponse</title>
    </head>
    <body>
        <%
            if(session.getAttribute("ID") == null)
            {
        %>
        <h1>Désolé</h1></br>
        <p>Tous nos emplacements sont déjà réservés</p>
        <%
            }
            else
            {
        %>
            <h1>Emplacement réservé</h1></br>
            <p>Votre demande de réservation a été enregistrée. Voici votre numéros de réservation :</p><br>
            <p><%=session.getAttribute("ID")%></p><br>
            <br>
            <p>Les coordonnées de l'emplacement sont : X = <%=session.getAttribute("X")%> Y = <%=session.getAttribute("Y")%></p>
        <%
            }
            
            session.invalidate();
        %>
    </body>
</html>
