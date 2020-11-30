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
	<c:choose>
	    <c:when test="${pedidos.size()>0}">
		   	<div class="font-italic text-center">
				<h6>Registro de TODOS los pedidos.</h6>
			</div>
	    </c:when>  
	    <c:otherwise>
	    	<div class="font-italic text-center mt-3 mb-3">
	    		<h6>Aun no hay ningun pedido</h6>
	    	</div>
	    </c:otherwise> 
	</c:choose>
	<div class="container navbar-light mr-2 p-0" style="background-color: #e3f2fd;"> 
	    <div class="table-responsive">
	        <table class="table table-bordered table-sm table-hover mb-0 text-center">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>Nº de Pedido</td>
	                    <td>Usuario</td>
	                    <td>Fecha de compra</td>
	                    <td>Estado</td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${pedidos}" var="pedido">
	                	<tr>
	                 	<td>${pedido.getnPedido()}</td>
	                 	<td>${Usuarios.getInstance().getUser(pedido.getnCliente()).getNombreUsuario()}</td>
	                 	<td>${pedido.getFecha()}</td>
	                 	<td>${pedido.getEstado()}</td>
	                 	<td>
							<a class="btn btn-outline-primary btn-sm" href="HistorialCompras?detalles=${pedido.getnPedido()}&admin=1">Detalles</a>
						</td>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
</div>