<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="usuarios.*" %>
<%@ page import="productos.*" %>
<%@ page import="util.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="CSS/styles.css">
	<title>Cuenta Bloqueada</title>
</head>
<body>  
	<!-- SI NO ESTA LOGEADO, REDIRIGE A INDEX. -->
	<c:choose>  
	    <c:when test="${activo}">  
	       <c:redirect url="index.jsp"/>
	    </c:when>
	</c:choose>  
	<header class="container-fluid p-0">
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning" >
			<h5 class="text-center">Bienvenido al Sistema.</h5>
		</div>
	</div>
	<section class="container">
		<h5 class="text-danger">Su cuenta ha sido bloqueada, pongase en contacto con un administrador.</h5>
	</section>

</body>
</html>