<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="usuarios.*" %>
<%@ page import="productos.*" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!logeado}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-6 ml-n3 mt-n1">
<h5>Confirme su compra</h5>
	<div class="container navbar-light mr-2 p-0" style="background-color: #e3f2fd;"> 
	    <div class="table-responsive">
	        <table class="table table-bordered table-sm table-hover mb-0">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>Producto</td>
	                    <td>Precio</td>
	                    <td>Cantidad</td>
	                    <td>Total $</td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${carrito.values()}" var="producto">
	                	<tr>
	                 	<td>${producto.getNombre()}</td>
	                 	<td>${producto.getPrecio()}</td>
	                 	<td>${producto.getCantidad()}</td>
	                 	<td>${producto.getTotal()}</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
	<div class="text-right mr-3">
		<p>Tolal: $${total}</p>
	</div>
	<!-- MENSAJE DE EXITO/ERROR -->
	<div class="text-center mb-3">
		<h5 class='text-danger'>
			<c:out value="${errores}"></c:out>
		</h5>
		<h5 class='text-success'>
			<c:out value="${success}"></c:out>
		</h5>
	</div>
	<div>
		<a class="btn btn-outline-primary btn-sm" href="Carrito?confirmarCompra=1">Confirmar compra</a>
		<a class="btn btn-outline-primary btn-sm text-right" href="Profile?menu=3">Volver</a>
	</div>
</div>