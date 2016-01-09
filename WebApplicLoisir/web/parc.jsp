<%-- 
    Document   : parc
    Created on : Jan 8, 2016, 11:43:57 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parc</title>
    </head>
    <body>
        <h1>Réserver vos tikets pour le parc</h1>
        <p>Session <%=session.getAttribute("login")%></p>
        <p>Attention le nombre d'entrée est limitée à 20 par jours.</p>
        <p>Pour quelle date voulez vous réserver vos tikets ?</p>
        <form>
            <input type="date" name="date">
            <input type="submit" value="Vérifier les disponibilités">
        </form>
    </body>
</html>
