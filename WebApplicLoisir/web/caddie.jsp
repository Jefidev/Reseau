<%-- 
    Document   : caddie
    Created on : Jan 14, 2016, 3:31:41 PM
    Author     : Jerome
--%>

<%@page import="BalisesPerso.listCaddie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>caddie</title>
    </head>
    <body>
        <h1>Contenus de votre caddie</h1>
        <!--afficher le contenu-->
        
        <form action="Controler" method="POST">
            <input type="submit" value="Retour à l'accueil"/>
            <input type="hidden" name="action" value="retourAccueil"/>
        </form>
        
        <% if(session.getAttribute("caddie") == null){
            %>
            <h2>Votre caddie est vide</h2>
        <%}
        else{
            %>
        <%}%>     
    </body>
    
    
</html>
