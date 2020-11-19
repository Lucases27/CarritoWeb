<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!logeado}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-4">
	<div class="container navbar-light" style="background-color: #e3f2fd;"> 
		<div>
			<h5>Modificar Datos</h5>				
		</div>
		<div class="row d-flex justify-content-center">
			<!-- MENSAJE DE EXITO/ERROR AL MODIFICAR -->
			<div class="col-12 form-group mb-1 mt-2">
				<h5 class="text-danger">
					<c:out value="${errores}"></c:out>
				</h5>
				<h5 class="text-success">
					<c:out value="${success}"></c:out>
				</h5>
			</div>
			<!-- FORMULARIO DE MODIFICACIÓN DE DATOS DE USUARIO -->
			<form action="Profile" method="POST">
				<div class="col-12 form-group mt-3 mb-0">
					<label>Usuario:
						<input readonly class="form-control " type="text" name="user" 
							id="user" value="<%out.print(session.getAttribute("user"));%>"/>
					</label>
				</div>
				<div class="col-12 form-group  mb-0">
					<label>Nick: 
						<input class="form-control" type="text" name="nick" placeholder="Nuevo Nick"
						 required min="5" max="10" value="<%out.print(session.getAttribute("nick"));%>"/>
					</label>
				</div>
				<div class="col-12 form-group mb-0">
					<label>Email: <input class="form-control" type="email" name="email" placeholder="Email" min="8" max="60"
						value="<%out.print(session.getAttribute("email"));%>"/></label>
				</div>
				<div class="col-12 form-group  mb-0">
					<label>Contraseña Actual: 
						<input class="form-control" type="password" name="pass" placeholder="Contraseña Actual" 
						required min="5" max="10" value="<%out.print(session.getAttribute("pass"));%>"/>
					</label>
				</div>
				<div class="col-12 form-group mb-0">
					<label>Nueva Contraseña: 
						<input class="form-control " type="password" name="pass1" placeholder="Nueva Contraseña" required min="5" max="10"/>
					</label>
				</div>
				<div class="col-12 form-group mb-0">
					<label>Repetir Contraseña: 
						<input class="form-control " type="password" name="pass2" placeholder="Repetir Contraseña" required min="5" max="10"/>
					</label>
				</div>
				<button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Modificar!</button>
			</form>
		</div>
	</div>
</div>
<div class="col-4">
	<div class="container" style="background-color: #e3f2fd;">
		<h5></h5>
	</div>
</div>