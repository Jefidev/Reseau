<%-- 
    Document   : accueil
    Created on : Jan 5, 2016, 10:46:12 PM
    Author     : Jerome
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
<!DOCTYPE html>
<%//Recuperation du resource bundle
    ResourceBundle bundle = (ResourceBundle)session.getAttribute("langue");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=bundle.getString("Accueil") %></title>
    </head>
    <body>
        <h1><%=bundle.getString("Bienvenue") %> <%=session.getAttribute("login")%></h1>
        <outil:currentDate langue="<%=bundle.getString("Date") %>"/>
        <form action="Controler" method="POST">
            <input type="submit" value="<%=bundle.getString("Reserver") %>""/>
            <input type="hidden" name="action" value="parc"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="<%=bundle.getString("Magasin") %>"/>
            <input type="hidden" name="action" value="magasin"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="<%=bundle.getString("afficherCaddie") %>"/>
            <input type="hidden" name="action" value="caddie"/>
        </form>
        <form action="Controler" method="POST">
            <input type="submit" value="<%=bundle.getString("Deconnexion") %>"/>
            <input type="hidden" name="action" value="deconnexion"/>
        </form>
    </body>
</html>
