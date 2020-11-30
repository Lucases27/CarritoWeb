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
    <link href="https://http2.mlstatic.com/frontend-assets/ui-navigation/5.10.4/mercadolibre/favicon.svg" rel="icon" data-head-react="true">
    <link rel="stylesheet" href="CSS/styles.css">
	<title>Home</title>
</head>
<body>  
	<header class="container-fluid p-0">
		<c:import url="menu.jsp"></c:import>
	</header>
	<div>
		<div class="alert alert-warning" >
			<h5 class="text-center">Bienvenido al Sistema.</h5>
		</div>
	</div>
	<section class="container">
		<c:choose>
		    <c:when test="${logeado}">
			   	<div class="font-italic text-center">
					<h6>Lista de productos disponibles</h6>
				</div>
		    </c:when>  
		    <c:otherwise>
		    	<div class="font-italic text-center mt-3 mb-3">
		    		<h6>Para poder realizar una compra, debe <a href="Login">logearse</a> o <a href="Register">registrarse</a>.</h6>
		    	</div>
		    </c:otherwise> 
		</c:choose>
		<div class="container-fluid row d-flex">
			<div class="col-3"></div>
			<div class="table-responsive col-6">
	        <table class="table table-bordered table-sm table-hover mb-0">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>Productos</td>
	                    <td>Precio</td>
	                    <td>Stock</td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${Stock.getInstance().getProductoLista()}" var="prod">
	                	<c:set var = "codigo" value = "${prod.getCodigo()}"/>
	                	<c:set var = "existe" value = "${listaProductos.contains(codigo)}"/>
	                	<tr>
	                 	<td>${prod.getNombre()}</td>
	                 	<td class="text-center">${prod.getPrecio()}</td>
	                 	<c:choose>
						    <c:when test="${ prod.getCantidad() < 1 }">
						    	<td class="text-danger text-center">Sin Stock!</td>
					    	</c:when>    
						    <c:otherwise>
						    	<td class="text-info text-center">Disponible</td>
					    	</c:otherwise>
						</c:choose>
	                 	<c:choose>
						    <c:when test="${logeado and prod.getCantidad() > 0 and !existe}">
						    	<td class="text-center"><a class="btn btn-outline-primary btn-sm" href="Carrito?agregar=${codigo}">Agregar al carrito</a></td>
						    </c:when> 
						    <c:when test="${logeado and prod.getCantidad() > 0 and existe}">
						    	<td class="text-center"><a class="btn btn-outline-success btn-sm inactive" href="#">Ya Agregado</a></td>
						    </c:when>
						    <c:when test="${logeado and prod.getCantidad() < 1 }">
								<td class="text-center"><a class="btn btn-outline-danger btn-sm inactive" href="#">Sin stock</a></td>
						    </c:when>    
						    <c:otherwise>
						    </c:otherwise> 
						</c:choose>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	        <div class="text-right mt-4">
	        	<a class="btn btn-outline-primary" href="Carrito?comprar=1">Continuar con la compra!</a>
	        </div>
	    	</div>
	    	<div class="col-3"></div>
		</div>
	</section>

</body>
</html>