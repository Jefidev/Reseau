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
        <p>Attention le nombre d'entrée est limitée à 20 par jours.</p>
        <p>Pour quelle date voulez vous réserver vos tikets ?</p>
        <form method="POST" action="parc.jsp">
            <input type="date" name="dateReservation">
            <p style="color: red"><jsp:getProperty name="beanReservation" property="erreur" /></p>
            <input type="submit" value="Vérifier les disponibilités">
        </form>
        
        <% if(curDate != null){%>
        
        <p>Pour l'instant <jsp:getProperty name="beanReservation" property="nbrReservation" /> entrées ont été réservées</p>
        <p>réserver des entrées (999€/entrées) : </p>
        
        <form method="POST" action="parc.jsp">
            <input type="number" name="reservation">
            <input type="submit" value="Ajouter au caddie">
        </form>
        
        <% } %>      
    </body>
</html>
