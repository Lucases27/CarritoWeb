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
	<title>Admin Panel</title>
</head>

<body>
	<!-- SI NO ES ADMIN, REDIRIGE A INDEX. -->
	<c:choose>  
	    <c:when test="${!isAdmin}">  
	       <c:redirect url="index.jsp"/>
	    </c:when>
	</c:choose>  
	<header class="container-fluid p-0">
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning" >
			<h5 class="text-center">Panel de Administrador.</h5>
		</div>
	</div>
	<section class="container-fluid">
		<div class="row d-flex">
			<div class="col-3">
				<div class="container navbar-light" style="background-color: #e3f2fd;"> 
					<h5>Menu de Administrador</h5>
					<a class="nav-link" href="Admin_panel?menu=1">Ver Usuarios</a>
					<a class="nav-link" href="Admin_panel?menu=2">Ver Productos</a>
					<a class="nav-link" href="Admin_panel?menu=3">Ver Pedidos</a>
				</div>			
			</div>
			<c:choose>
				<c:when test="${param.menu == 1}">
					<jsp:include page="includes/admin_usuarios.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu == 2}">
					<jsp:include page="includes/admin_productos.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu == 3}">
					<jsp:include page="includes/admin_pedidos.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.menu == 6}">
					<jsp:include page="includes/historial_detallesAdmin.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.opt == 1}">
					<jsp:include page="includes/admin_productos_nuevo.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.opt == 2}">
					<jsp:include page="includes/admin_productos_modificar.jsp"></jsp:include>
				</c:when>
				<c:when test="${param.opt == 3}">
					<div class="col-3 ml-n3 mt-n1">
						<div class="container navbar-light mr-2 row d-flex justify-content-center align-items-center" style="background-color: #e3f2fd;">
							<h5 class="mt-3 ml-3 text-center">Eliminar Producto.</h5> 
							<!-- MENSAJE DE EXITO/ERROR -->
							<div class="col text-center form-group mb-0 mt-2">
								<h5 class="text-danger">
									<c:out value="${errores}"></c:out>
								</h5>
								<h5 class="text-success">
									<c:out value="${success}"></c:out>
								</h5>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
</body>
</html>