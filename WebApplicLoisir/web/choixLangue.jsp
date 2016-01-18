<%-- 
    Document   : choixLangue
    Created on : Jan 18, 2016, 3:43:06 PM
    Author     : Jerome
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/outil.tld" prefix="outil"%>
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
        <title>Langue</title>
    </head>
    <body>
        <h1>Choix de la langue</h1>
        <outil:ChoixLangue/>
    </body>
</html>
