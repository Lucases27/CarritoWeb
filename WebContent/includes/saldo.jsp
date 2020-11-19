<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- SI NO ESTA LOGEADO, REDIRIGE A LA PAGINA DE ERROR. -->
<c:choose>  
    <c:when test="${!logeado}">  
       <c:redirect url="../error404.jsp"/>
    </c:when>
</c:choose>
<div class="col-4">
    <div class="container" style="background-color: #e3f2fd;">
        <h4 class="text-center p-4 "> Tu Saldo </h4>
        <!-- MENSAJE DE EXITO/ERROR AL CARGAR SALDO -->
		<div class="col text-center form-group mb-4">
			<h5 class="text-danger">
				<c:out value="${errores}"></c:out>
			</h5>
			<h5 class="text-success">
				<c:out value="${success}"></c:out>
			</h5>
		</div>
        <div class="mt-3">
            <p>Tu saldo actual es $: <span class="text-success"><% out.print(session.getAttribute("saldo")); %></span></p>
        </div>
        <form action="Saldo" method="POST">
            <div class="col form-group mt-3 mb-0">
                <label>Cargar saldo: 
                    <input class="form-control" type="text" name="monto"/>
                </label>
            </div>
            <div class="col form-group  mb-0">
                <label>Metodo de pago:
                    <select name="metodoPago">
                        <option value="mercadopago">Mercado Pago</option>
                        <option value="paypal">PayPal</option>
                        <option value="credito">Tarjeta de credito</option>
                        <option value="efectivo">Efectivo/Debito</option>
                    </select>
                </label>
            </div>
            <input type="hidden" value="1" name="cargar"/>
            <button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Cargar Saldo</button>
        </form>
    </div>
</div>
<div class="col-4">
	<div class="container navbar-light" style="background-color: #e3f2fd;">
        <div class="">
            <h4 class="text-center p-4"> Transferir Saldo </h4>				
        </div>
        <div>
            <h5 class="p-3">En esta seccion usted podrá transferir saldo a otros usuarios.</h5>
        </div>
        <div class="row d-flex justify-content-center pb-3 ">
            <!-- MENSAJE DE EXITO/ERROR AL TRANSFERIR -->
			<div class="col-12 text-center form-group mb-4">
				<h5 class="text-danger">
					<c:out value="${errores2}"></c:out>
				</h5>
				<h5 class="text-success">
					<c:out value="${success2}"></c:out>
				</h5>
			</div>
            <form action="Saldo" method="POST">
                <div class="col form-group mt-3 mb-0 d-flex">
                    <label class="align-items-center">Usuario:
                        <input class="form-control " type="text" name="user" placeholder="Usuario a transferir dinero"/>
                    </label>
                </div>
                <div class="col form-group  mb-0 d-flex">
                    <label class="align-items-center">Contraseña: 
                        <input class="form-control" type="password" name="pass" placeholder="Ingresa tu contraseña" 
                        required min="5" max="10"/>
                    </label>
                </div>
                <div class="col form-group  mb-0 d-flex">
                    <label class="align-items-center"> Cantidad: 
                        <input class="form-control" type="text" name="monto" placeholder="Saldo a transferir" required/>
                    </label>
                </div>
                <button type="submit" class="btn btn-primary mt-2 mb-3 form-control">Transferir dinero</button>
            </form>
        </div>
    </div>
</div>