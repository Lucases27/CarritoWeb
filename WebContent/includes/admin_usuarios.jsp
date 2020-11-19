<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="usuarios.*" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!isAdmin}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-9 ml-n5 mt-n5 p-5">
	<div class="container navbar-light  p-0" style="background-color: #e3f2fd;"> 
	    <div class="table-responsive">
	        <table class="table table-bordered table-sm table-hover mb-0">
	            <thead>
	                <tr style="background:#003325;color:white" class="text-center">
	                    <td>ID cliente</td>
	                    <td>Username</td>
	                    <td>Nick</td>
	                    <td>Email</td>
	                    <td>Saldo</td>
	                    <td>Roll</td>
	                    <td>Estado</td>
	                </tr>
	            </thead>
           		<tbody>
	                <c:forEach items="${Usuarios.getInstance().getUserList()}" var="user">
	                	<tr>
	                 	<td>${user.getId()}</td>
	                 	<td>${user.getNombreUsuario()}</td>
	                 	<td>${user.getNick()}</td>
	                 	<td>${user.getEmail()}</td>
	                 	<td>${user.getSaldo()}</td>
	                 	<c:choose>
						    <c:when test="${user.isAdmin()}"><td class="text-primary">Administrador</td></c:when>    
						    <c:otherwise><td class="text-info">Cliente</td></c:otherwise>
						</c:choose>
	                 	<c:choose>
						    <c:when test="${user.getActivo()}">
						    	<td class="text-primary text-center">Activo</td>
						    	<td><a class="btn btn-outline-danger btn-sm " href="Admin_panel?bloquear=${user.getId()}">Bloquear</a></td>
						    </c:when>    
						    <c:otherwise>
						    	<td class="text-danger text-center">No Activo</td>
						    	<td><a class="btn btn-outline-primary btn-sm"  href="Admin_panel?desbloquear=${user.getId()}">Desbloquear</a></td>
						    </c:otherwise> 
						</c:choose>
	                	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	</div>
</div>