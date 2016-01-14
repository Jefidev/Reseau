<%-- 
    Document   : magasin
    Created on : Jan 9, 2016, 11:07:32 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>Consomme !</h1>
        <outil:currentDate langue="UK"/>
        
        <form action="Controler" method="POST">
            <input type="submit" value="Retour à l'accueil"/>
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
        <outil:displayProducts erreurCommmande="<%=message%>" idItem="<%=id%>">
            select * from produits
        </outil:displayProducts>
        <%}%>
    
</html>
