<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">Inicio</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
				aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class=" navbar-nav ml-auto">
					<!-- COMPROBAMOS SI ESTA LOGEADO Y SI ES ADMIN PARA MOSTRAR LOS DISTINTOS MENUS -->
					<c:choose>  
					    <c:when test="${isAdmin}">  
					    	<li class="nav-item active">
								<a class="nav-link text-success" href="Saldo">
									Saldo: $
									<c:out value="${saldo}"></c:out>
								</a>
							</li>
					       	<li class="nav-item active">
								<a class="nav-link" href="Admin_panel">Admin Panel</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="Profile">Mi Cuenta</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="Login?salir=1">Salir</a>
							</li>
					    </c:when>  
					    <c:when test="${!isAdmin and logeado}">  	
							<li class="nav-item active">
								<a class="nav-link text-success" href="Saldo">
									Saldo: $
									<c:out value="${saldo}"></c:out>
								</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="Carrito">Carrito</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="Profile">Mi Cuenta</a>
							</li>
					       	<li class="nav-item active">
								<a class="nav-link" href="Login?salir=1">Salir</a>
							</li>
					    </c:when>
					    <c:otherwise>    	
							<li class="nav-item active">
								<a class="nav-link" href="Register">Registrarse</a>
							</li>
							<li class="nav-item active">
								<a class="nav-link" href="Login">Login</a>
							</li>
					    </c:otherwise>  
					</c:choose>  
				</ul>
			</div>
		</div>
	</nav>