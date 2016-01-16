<%-- 
    Document   : payemment
    Created on : Jan 14, 2016, 7:09:45 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>payemment ok</title>
    </head>
    <body>
        <h1>La commande a bien été effectuee</h1>
        <form action="Controler" method="POST">
            <input type="submit" value="Retour à l'accueil"/>
            <input type="hidden" name="action" value="retourAccueil"/>
        </form>
    </body>
</html>
