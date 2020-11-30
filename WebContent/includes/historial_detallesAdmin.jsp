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
		   	<div class="font-italic text-center">
				<h6>Detalle del pedido Nº: ${nPedido}</h6>
			</div>
	<div class="container navbar-light mr-2 p-0" style="background-color: #e3f2fd;"> 
	    <div class="table-responsive">
	        <table class="table table-bordered table-sm table-hover mb-0 text-center">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>Codigo Producto</td>
	                    <td>Nombre Producto</td>
                	    <td>Cantidad</td>
	                    <td>Precio unidad</td>
	                    <td>Total</td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${pedidoN}" var="prod">
	                	<tr>
	                	<td>${prod.getCodigo()}</td>
	                 	<td>${prod.getNombre()}</td>
	                 	<td>${prod.getCantidad()}</td>
	                 	<td>$${prod.getPrecio()}</td>
	                 	<td>$${prod.getTotal()}</td>
	                	</tr>
	            	</c:forEach>
	            	<tr>
	                	<td></td>
	                 	<td></td>
	                 	<td></td>
	                 	<td></td>
	                 	<td>$${totalCompra}</td>
	                	</tr>
	            </tbody>
	        </table>
	    </div>
	</div>
	<a class="btn btn-outline-primary btn-sm mt-2 mb-3" href="Admin_panel?menu=3">Volver</a>
</div>