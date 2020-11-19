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
import util.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				HttpSession session = request.getSession();
				
				if(session.getAttribute("logeado") == null) {
					response.sendRedirect("registro.jsp");
				}
				else response.sendRedirect("index.jsp");
				
				

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String user = request.getParameter("user").trim().toUpperCase();
		String nick = request.getParameter("nick").trim();
		String pass = request.getParameter("pass").trim();
		String pass2 = request.getParameter("pass2").trim();
		String email = request.getParameter("email").trim().toLowerCase();
		String errores = "";
		String success = "";
		
		
		if (Validaciones.validaCampo(user) & Validaciones.validaCampo(pass) & Validaciones.validaCampo(nick)) {
			if(Validaciones.validaEmail(email)) {
				 if(pass.equals(pass2)){
					 if(!Usuarios.getInstance().existeUsuario(user)){
						 if(!Usuarios.getInstance().existeEmail(email)) {
							 Usuarios.getInstance().addUser(user, pass, nick, email);
							 success = "El usuario se creó correctamente.";
						 }
						 else errores = "El email ingresado ya existe.";
					 }
					 else errores = "Ya existe un usuario con ese nombre.";
				 }
				 else errores = "Las contraseñas no coinciden.";
			}
			else errores = "El email ingresado no es válido.";
		}
		else errores = "Los campos deben tener entre 5 y 10 caracteres únicamente alfanumericos.";			

		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
		dispatcher.forward(request, response);
		
	}	
	
	
	
	

}
