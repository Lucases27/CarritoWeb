<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="usuarios.*" %>
<%@ page import="productos.*" %>
<%@ page import="util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="CSS/styles.css">
	<title>Registrarse</title>
</head>
<body>
<!-- SI ESTA LOGEADO, REDIRIGE A INDEX. -->
	<c:choose>  
	    <c:when test="${logeado}">  
	       <c:redirect url="index.jsp"/>
	    </c:when>
	</c:choose>
	<header>
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning mb-3"> 
			<h5 class="text-center">Registrar nueva cuenta.</h5>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row d-flex mt-3 mb-2">
			<div class="col-12">			
				<!-- MENSAJE DE EXITO/ERROR -->
				<div class="text-center mb-3">
					<h5 class='text-danger'>
						<c:out value="${errores}"></c:out>
					</h5>
					<h5 class='text-success'>
						<c:out value="${success}"></c:out>
					</h5>
				</div>
			</div>
			<div class="col-4"></div>
				<div class="col-4 d-flex justify-content-center">
					<form action="Register" method="POST" id="registro1">
						<div class="form-group">
							<label>Usuario:<input class="form-control" type="text" name="user" min="5" max="10"/></label>
						</div>
						<div class="form-group">
							<label>Nick: <input class="form-control" type="text" name="nick" min="5" max="10"/></label>
						</div>
						<div class="form-group">
							<label>Email: <input class="form-control" type="email" name="email" max="60"/></label>
						</div>
						<div class="form-group">
							<label>Contraseña: <input class="form-control" type="password" name="pass" min="5" max="10"/></label>
						</div>
						<div class="form-group">
							<label>Repetir Contraseña: <input class="form-control" type="password" name="pass2" min="5" max="10"/></label> 
						</div>
						<button type="submit" class="btn btn-primary form-control">Registrarse</button>
					</form>
						</div>
					<div class="col-4"></div>
				<div>
			</div>
		</div>
	</div>
</body>
</html>