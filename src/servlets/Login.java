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
import util.Validaciones;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//Si no salio y no esta logeado, redirecciona al login.
		//Si está logeado, redirecciona al index.
		//Y si salio borra los datos de session.
		if (request.getParameter("salir") == null) {
			if(session.getAttribute("logeado")== null) {
				response.sendRedirect("login.jsp");
			}else response.sendRedirect("index.jsp");
			
		}else {
			if(request.getParameter("salir") != null) {
				session.invalidate();
				response.sendRedirect("index.jsp");
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String user = request.getParameter("user").trim().toUpperCase();
		String pass = request.getParameter("pass").trim();
		String error = "";

		if(Usuarios.getInstance().existeUsuario(user)) {
			if(Usuarios.getInstance().validaUser(user, pass)){
				if(Validaciones.validaCampo(user) & Validaciones.validaCampo(pass)) {
					if(Usuarios.getInstance().getUser(user).getActivo()) {
						Usuarios.getInstance().cargarSession(user, pass, session);
						error = "Bienvenido "+ user +".";
					}
					else {
						response.sendRedirect("banned.jsp");
						return;						
					}
				}
				else error = "Los campos deben tener entre 5 y 10 caracteres únicamente alfanumericos.";
			}
			else {
				error = "Usuario o Contraseña incorrectos.";
				session.invalidate();
			}
		}
		else {
			error = "El usuario no existe.";
		}
		
		request.setAttribute("errores", error);
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		
	}
}
