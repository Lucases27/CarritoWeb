<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="usuarios.*" %>
<%@ page import="productos.*" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!isAdmin}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-6 ml-n3 mt-n1">
	<div class="container navbar-light mr-2 p-0" style="background-color: #e3f2fd;"> 
	    <div class="table-responsive">
	        <table class="table table-bordered table-sm table-hover mb-0">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>Codigo</td>
	                    <td>Nombre</td>
	                    <td>Precio</td>
	                    <td>Cantidad</td>
	                    <td><a class="btn btn-warning btn-sm" href="Admin_panel?menu=2&opt=1">Nuevo Producto</a></td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${Stock.getInstance().getProductoLista()}" var="producto">
	                	<tr>
	                 	<td>${producto.getCodigo()}</td>
	                 	<td>${producto.getNombre()}</td>
	                 	<td>${producto.getPrecio()}</td>
	                 	<td>${producto.getCantidad()}</td>
	                 	<c:set var="codigo" value="${producto.getCodigo()}"/>
	                 	<c:set var="nom" value="${producto.getNombre()}"/>
	                 	<c:set var="precio" value="${producto.getPrecio()}"/>
	                 	<c:set var="cant" value="${producto.getCantidad()}"/>
						<td class="text-center">
							<a class="btn btn-outline-primary btn-sm" href="Admin_panel?menu=2&opt=2&prod=${codigo}&nom=${nom}&precio=${precio}&cant=${cant}">Modificar</a>
							<a class="btn btn-outline-primary btn-sm" href="Admin_panel?menu=2&opt=2&prod=${codigo}&eliminar=1">Eliminar</a>
						</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
</div>