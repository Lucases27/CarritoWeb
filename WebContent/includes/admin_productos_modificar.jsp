<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="usuarios.*" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!isAdmin}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-3 ml-n3 mt-n1">
	<div class="container navbar-light mr-2 row d-flex justify-content-center align-items-center" style="background-color: #e3f2fd;">
		<h5 class="mt-3 text-center">Modificar producto.</h5> 
		<!-- MENSAJE DE EXITO/ERROR AL CARGAR SALDO -->
		<div class="col text-center form-group mb-0 mt-2">
			<h5 class="text-danger">
				<c:out value="${errores}"></c:out>
			</h5>
			<h5 class="text-success">
				<c:out value="${success}"></c:out>
			</h5>
		</div>
		<!-- FORMULARIO  -->
		<form action="Admin_panel" method="POST">
			<div class="form-group mt-2">
				<label>Codigo:<input class="form-control" readonly type="text" name="codigo" placeholder="${prod}"/></label>
			</div>
			<div class="form-group">
				<label>Nombre: <input class="form-control" type="text" name="nombre" placeholder="Nombre del producto"/></label>
			</div>
			<div class="form-group">
				<label>Precio<input class="form-control" type="text" name="precio" placeholder="Precio del producto"/></label>
			</div>
			<div class="form-group">
				<label>Cantidad:<input class="form-control" type="text" name="cantidad" placeholder="Cantidad del producto"/></label>
			</div>
			<input type="hidden" value="modificar" name="producto">
			<input type="hidden" value="${prod}" name="codigoProducto">
			<button type="submit" class="btn btn-primary form-control mb-4">Modificar</button>
		</form>
	</div>
</div>