<%-- 
    Document   : parc
    Created on : Jan 8, 2016, 11:43:57 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="beanReservation" class="BeanUtil.BeanDisponibilite" scope="request"/>
<jsp:setProperty name="beanReservation" property="dateReservation" value="<%=request.getParameter("dateReservation")%>"/>

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
        <form method="POST" action="parc.jsp">
            <input type="date" name="dateReservation">
            <input type="submit" value="Vérifier les disponibilités">
        </form>
        <p><jsp:getProperty name="beanReservation" property="nbrReservation" /></p>
    </body>
</html>
