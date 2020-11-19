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
	<title>Index.jsp</title>
</head>
<body>
	<!-- SI NO ESTA LOGEADO, REDIRIGE A INDEX. -->
	<c:choose>  
	    <c:when test="${!logeado}">  
	       <c:redirect url="index.jsp"/>
	    </c:when>
	</c:choose>
	<header class="container-fluid p-0">
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning" >
			<h5 class="text-center">Perfil de Usuario.</h5>
		</div>
	</div>
	<section class="container-fluid">
		<div class="row d-flex">
			<div class="col-4">
				<div class="container navbar-light" style="background-color: #e3f2fd;"> 
					<h5>Menu de Usuario</h5>
					<a class="nav-link" href="Profile?menu=1">Modificar Datos</a>
					<a class="nav-link" href="Profile?menu=2">Cargar Saldo</a>
					<a class="nav-link" href="Profile?menu=3">Mi carrito</a>
					<a class="nav-link" href="HistorialCompras">Historial de Compras</a>
				</div>			
			</div>
			<c:choose>
				<c:when test="${param.menu == 1}">
					<jsp:include page="includes/modificar_datos.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu.equals('2')}">
					<jsp:include page="includes/saldo.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu.equals('3')}">
					<jsp:include page="includes/carrito_productos.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu.equals('4')}">
					<jsp:include page="includes/historial_comprasU.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu.equals('5')}">
					<jsp:include page="includes/carrito_confirmacion.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu.equals('6')}">
					<jsp:include page="includes/historial_detalles.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<div>
						<h5>Bienvenido ${Validaciones.capitalize(user)} a tu perfil de usuario</h5>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</body>
</html>