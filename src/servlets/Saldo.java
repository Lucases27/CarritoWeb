package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usuarios.Usuarios;
import util.Monedero;
import util.Validaciones;

/**
 * Servlet implementation class Saldo
 */
@WebServlet("/Saldo")
public class Saldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Saldo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("Profile?menu=2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		String monto, metodoPago, user, pass, errores = "", errores2 = "", success = "", success2 = "";
		monto = request.getParameter("monto").replace(",", ".").trim();
		
		if(request.getParameter("cargar") != null) {
			if(Validaciones.validaNum(monto)) {
				double montoOk = Double.parseDouble(monto);
				metodoPago = request.getParameter("metodoPago");
				if(Monedero.getInstance().cargarSaldo(montoOk, id, metodoPago)) {
					success = "Operación realizada con exito.";
					session.setAttribute("saldo", (double)session.getAttribute("saldo")+montoOk);
				}
				else {
					errores = "No se pudo realizar la carga.";
				}
			}
			else {
				errores = "El monto ingresado es incorrecto.";
			}
		}
		else {
			if(Validaciones.validaNum(monto)) {
				double montoOk = Double.parseDouble(monto);
				user = request.getParameter("user").trim().toUpperCase();
				pass = request.getParameter("pass").trim();
				id = Usuarios.getInstance().getUser(user).getId();
				if(Usuarios.getInstance().existeUsuario(user)) {
					if(session.getAttribute("user").equals(user) == false) {
						if(Usuarios.getInstance().validaUser((String)session.getAttribute("user"), pass)) {
							double saldoFinal = (double)session.getAttribute("saldo") - montoOk;
							if(saldoFinal >= 0) {
								if(Monedero.getInstance().cargarSaldo(montoOk, id, "transferencia")) {
									Monedero.getInstance().restarSaldo(montoOk, (int)session.getAttribute("id"));
									success2 = "Operación realizada con exito.";
									session.setAttribute("saldo", saldoFinal);
								}
								else {
									errores2 = "No se pudo realizar la carga.";
								}
							}
							else {
								errores2 = "Saldo insuficiente.";
							}
						}
						else {
							errores2 = "La contraseña es invalida.";
						}
					}
					else {
						errores2 = "No tiene sentido intentar transferir dinero a ti mismo.";
					}
				}
				else {
					errores2 = "El usuario ingresado no existe.";
				}			
			}
			else {
				errores2 = "El monto ingresado no es correcto.";
			}
		}
		
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		request.setAttribute("errores2", errores2);
		request.setAttribute("success2", success2);
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=2");
		dispatcher.forward(request, response);
	}
}
