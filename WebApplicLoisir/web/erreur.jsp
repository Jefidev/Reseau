<%-- 
    Document   : erreur
    Created on : Jan 13, 2016, 4:13:56 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erreur</title>
    </head>
    <body>
        <h1>Oups !  Une erreur s'est produite lors du traitement de votre requete</h1>
        <p>Session de <%=session.getAttribute("login")%></p>
        
        <form action="Controler" method="POST">
            <input type="submit" value="Retour à l'accueil"/>
            <input type="hidden" name="action" value="retourAccueil"/>
        </form>
    </body>
</html>
