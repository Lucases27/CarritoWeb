package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import productos.Cart;
import productos.Stock;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cada vez que pasemos por el perfil, renovamos los datos de la sesion.
		HttpSession session = request.getSession();
		
		if(session.getAttribute("logeado") == null) {
			response.sendRedirect("index.jsp");
		}
		else {
			String user = (String)session.getAttribute("user");
			Usuarios.getInstance().cargarSession(user, session);
			if(Usuarios.getInstance().getUser(user).getActivo()==false) {
				session.invalidate();
				response.sendRedirect("banned.jsp");
				return;
			}
			if (request.getParameter("menu") == null) {
				response.sendRedirect("profile.jsp");
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
				dispatcher.forward(request, response);			
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String user = request.getParameter("user").trim().toUpperCase();
		String nick = request.getParameter("nick").trim();
		String pass = request.getParameter("pass").trim();
		String pass1 = request.getParameter("pass1").trim();
		String pass2 = request.getParameter("pass2").trim();
		String email = request.getParameter("email").trim().toLowerCase();
		String errores = "";
		String success = "";
		
		if(Usuarios.getInstance().validaUser(user, pass)) {
			if(pass1.equals(pass2)) {
				if(Validaciones.validaCampo(nick)) {
					if(Validaciones.validaCampo(pass1)) {
						if(Validaciones.validaEmail(email)) {
							if(!Usuarios.getInstance().updateEmail(email,(int)session.getAttribute("id"))) {
								Usuarios.getInstance().updateUser(user, nick, pass1, email);
								session.setAttribute("nick", nick);
								session.setAttribute("pass", pass1);
								session.setAttribute("email", email);
								success = "Modificación exitosa.";
							}
							else errores = "El mail ingresado ya existe!";
						}
						else errores = "El email ingresado es inválido.";
					}
					else errores = "La contraseña debe entre 5 y 10 caracteres.";
				}
				else errores = "El nick debe entre 5 y 10 caracteres.";
			}
			else errores = "Las contraseñas son diferentes.";
		}
		else errores = "La contraseña es incorrecta.";
		
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=1");
		dispatcher.forward(request, response);
	}
}
