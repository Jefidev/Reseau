<%-- 
    Document   : magasin
    Created on : Jan 9, 2016, 11:07:32 PM
    Author     : Jerome
--%>

<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
<%//Recuperation du resource bundle
    ResourceBundle bundle = (ResourceBundle)session.getAttribute("langue");%>
<html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=bundle.getString("Magasin") %></title>
    </head>
    
        <h1><%=bundle.getString("Magasin") %></h1>
        <% String date = bundle.getString("Date"); %>
        <outil:currentDate langue="<%=date %>"/>
        
        <form action="Controler" method="POST">
            <input type="submit" value="<%=bundle.getString("Accueil") %>"/>
            <input type="hidden" name="action" value="retourAccueil"/>
        </form>
        
        <%
            if(request.getAttribute("erreurCommande") ==  null)
            {
        %>
        <outil:displayProducts>
            select * from produits
        </outil:displayProducts>
        <%
            }
            else
            {   
                String message = request.getAttribute("erreurCommande").toString();
                int id = Integer.parseInt(request.getAttribute("idProduit").toString());
        %>
        <!-- On va demander d'afficher un message d'erreur pour le produit indiqué -->
        <outil:displayProducts erreurCommmande="<%=message%>" idItem="<%=id%>">
            select * from produits
        </outil:displayProducts>
        <%}%>
    
</html>
