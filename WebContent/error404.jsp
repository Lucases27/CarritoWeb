<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="CSS/styles.css">
	<title>Pagina no encontrada!</title>
</head>
<body>  
	<header class="container-fluid p-0">
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning" >
			<h5 class="text-center">Lo sentimos, aquí no hay nada para mostrar.</h5>
		</div>
	</div>
	<section class="container">
		<div class="text-center">
			<img src="https://www.gstatic.com/youtube/src/web/htdocs/img/monkey.png">
		</div>
	</section>

</body>
</html>