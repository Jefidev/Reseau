<%-- 
    Document   : accueil
    Created on : Jan 5, 2016, 10:46:12 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1>Bienvenue <%=session.getAttribute("login")%></h1>
        <form action="Controler" method="POST">
            <input type="submit" value="Réserver des entrées pour le parc"/>
            <input type="hidden" name="action" value="parc"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="Acheter des guides ou des objets \"nature\""/>
            <input type="hidden" name="action" value="magasin"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="Afficher le caddie"/>
            <input type="hidden" name="action" value="caddie"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="Déconnexion"/>
            <input type="hidden" name="action" value="deconnexion"/>
        </form>
    </body>
</html>
