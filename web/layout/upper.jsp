<%@page import="helpers.ViewUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="config.Config"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page import="helpers.RequestUtils"%>

<!DOCTYPE html>
<html lang="<%=Config.LANGUAGE%>">
    <head>
        <title>T�tulo de la p�gina</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        
        <!--Hoja de estilos del layout. Se importa independientemente de la p�gina.-->
        <link rel="stylesheet" type="text/css" href="/mycv_app/assets/css/base-layout.css"/>
        <%=ViewUtils.printStylesheets(request)%>
        

    </head>
    <body>
        <!--Si hay sesi�n de usuario, carga la navbar-signed, si no, la unsigned-->
        <%
            if(RequestUtils.userSessionExists(request)){
                %><%@include file="nav-signed.jsp"%><%
            }
            else{
                %><%@include file="nav-unsigned.jsp"%>
            <%}
        %>
        
        <% if (request.getAttribute("notification-error") != null) {%>
        <div class="alert alert-danger text-center" role="alert"><%= request.getAttribute("notification-error")%></div>
        <%}%>

        <% if (request.getAttribute("notification-success") != null) {%>
        <div class="alert alert-success text-center" role="alert"><%= request.getAttribute("notification-success")%></div>
        <%}%>
        <div class="container contenedor-global">
