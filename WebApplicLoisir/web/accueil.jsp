<%-- 
    Document   : accueil
    Created on : Jan 5, 2016, 10:46:12 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if(request.getParameter("login") == null && session.getAttribute("login") == null)
            response.sendRedirect("index.html");
        else if(request.getParameter("login") != null)
            session.setAttribute("login", request.getParameter("login"));
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1>Bienvenue <%=session.getAttribute("login")%></h1>
        <h2>Que voulez-vous faire ?</h2>
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
    </body>
</html>
