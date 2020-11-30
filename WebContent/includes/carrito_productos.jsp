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
	    <c:when test="${listaProductos.size()>0}">
		   	<div class="font-italic text-center">
				<h6>Lista de productos en tu carrito</h6>
			</div>
	    </c:when>  
	    <c:otherwise>
	    	<div class="font-italic text-center mt-3 mb-3">
	    		<h6>No hay ningun producto cargado al carrito. <a href="index.jsp">Ver productos!</a>.</h6>
	    	</div>
	    </c:otherwise> 
	</c:choose>
	<!--  FORMULARIO -->
	<form action="Carrito" method="POST">
		<div class="container navbar-light mr-2 p-0" style="background-color: #e3f2fd;"> 
		    <div class="table-responsive">
		        <table class="table table-bordered table-sm table-hover mb-0">
		            <thead>
		                <tr style="background:#003325;color:white" class="text-center">
		                    <td>Producto</td>
		                    <td>Precio</td>
		                    <td>Cantidad</td>
		                </tr>
		            </thead>
	           		<tbody>
		                <c:forEach items="${Stock.getInstance().getProductoLista()}" var="prod">
		                	<c:choose>
							    <c:when test="${listaProductos.contains(prod.getCodigo())}">
				                	<tr>
					                 	<td>${prod.getNombre()}</td>
					                 	<td class="text-center">${prod.getPrecio()}</td>
					                 	<td class="text-center"><input type="text" name="cantidadDe${prod.getCodigo()}" placeholder="Cantidad" value="1"></td>
										<td class="text-center">
											<a class="btn btn-outline-primary btn-sm" href="Carrito?eliminar=${prod.getCodigo()}">Eliminar</a>
										</td>
				                	</tr>
						    	</c:when>    
							</c:choose>
		            	</c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
 		<!-- MENSAJE DE EXITO/ERROR -->
		<div class="text-center mb-3 p-0">
			<h5 class='text-danger'>
				<c:out value="${errores}"></c:out>
			</h5>
		</div>
		<div class="text-right mt-4 p-0">
	    	<button type="submit" class="btn btn-primary form-control">Comprar!</button>
	    </div>
    </form>
</div>