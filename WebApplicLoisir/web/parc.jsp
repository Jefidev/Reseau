<%-- 
    Document   : parc
    Created on : Jan 8, 2016, 11:43:57 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
<!DOCTYPE html>

<jsp:useBean id="beanReservation" class="BeanUtil.BeanDisponibilite" scope="request"/>
<%! String curDate;%>
<% curDate = request.getParameter("dateReservation");%>
<jsp:setProperty name="beanReservation" property="dateReservation" value="<%= curDate %>"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parc</title>
    </head>
    <body>
        <h1>Réserver vos tikets pour le parc</h1>
        <p>Session de <%=session.getAttribute("login")%></p>
        <outil:currentDate langue="UK"/>
        
        <form action="Controler" method="POST">
            <input type="submit" value="Retour à l'accueil"/>
            <input type="hidden" name="action" value="retourAccueil"/>
        </form>
        
        <p>Attention le nombre d'entrée est limitée à 20 par jours.</p>
        <p>Pour quelle date voulez vous réserver vos tikets ?</p>
        <form method="POST" action="parc.jsp">
            <input type="date" name="dateReservation">
            <!-- Récupération du message d'erreur dans le bean pour l'afficher (si chaine vide rien n'apparait à l'ecran) -->
            <p style="color: red"><jsp:getProperty name="beanReservation" property="erreur" /></p>
            <input type="submit" value="Vérifier les disponibilités">
        </form>
        
        <!-- Une recherche a été demandée pour la réservation -->
        <% if(beanReservation.getdateReservation() != null){%>
        
        <p>Pour l'instant <jsp:getProperty name="beanReservation" property="nbrReservation" /> entrées ont été réservées
        pour le <jsp:getProperty name="beanReservation" property="dateReservation" /></p>
        <p>réserver des entrées (5€/entrées) : </p>
        
        <!-- Si il y a eu une erreur à la réservation de la place on va l'afficher ici -->
        
        
        <form method="POST" action="Controler">
            <input type="number" name="nbrPlace">
            <input type="submit" value="Ajouter au caddie">
            <!-- Action à accomplir dans le controler -->
            <input type="hidden" name="action" value="reserverParc"/>
            <!--Récupération de la date de la réservation dans un champ caché -->
            <input type="hidden" name="date" value="<jsp:getProperty name="beanReservation" property="dateReservation" />"/>
        </form>
        
        <% } %>      
    </body>
</html>
