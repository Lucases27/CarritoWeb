package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import productos.Stock;
import registros.Pedidos;
import usuarios.Usuarios;
import util.Validaciones;

/**
 * Servlet implementation class Admin_panel
 */
@WebServlet("/Admin_panel")
public class Admin_panel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin_panel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("logeado") == null) {
			response.sendRedirect("index.jsp");
		}
		else {
			if((boolean)session.getAttribute("isAdmin") == true) {
				if (request.getParameter("menu") == null) {
					if(request.getParameter("bloquear") != null) {
						int id = Integer.parseInt(request.getParameter("bloquear"));
						Usuarios.getInstance().setEstado(id,0);
						response.sendRedirect("admin_panel.jsp?menu=1");
					}
					else {
						if(request.getParameter("desbloquear") != null) {
							int id = Integer.parseInt(request.getParameter("desbloquear"));
							Usuarios.getInstance().setEstado(id,1);
							response.sendRedirect("admin_panel.jsp?menu=1");
						}
						else {
							response.sendRedirect("admin_panel.jsp");
						}
					}
				}
				else {
					if(request.getParameter("menu").equals("2")) {
						if (request.getParameter("opt") != null) {
							if (request.getParameter("opt").equals("2")) {
								if(request.getParameter("eliminar") != null) {
									String errores = "";
									int codigo = Integer.parseInt(request.getParameter("prod"));
									if(Stock.getInstance().deleteProducto(codigo)) {
										errores = "Producto eliminado.";
									}
									else {
										errores = "No se pudo eliminar el producto.";
									}
									request.setAttribute("errores", errores);
									RequestDispatcher dispatcher = request.getRequestDispatcher("admin_panel.jsp?menu=2&opt=3");
									dispatcher.forward(request, response);
								}
								else {
									request.setAttribute("prod", request.getParameter("prod"));
									request.setAttribute("nom", request.getParameter("nom"));
									request.setAttribute("precio", request.getParameter("precio"));
									request.setAttribute("cant", request.getParameter("cant"));
								}
							}
						}
					}
					else{
						if(request.getParameter("menu").equals("3")) {
							request.setAttribute("pedidos", Pedidos.getInstance().getListaPedido());
							RequestDispatcher dispatcher = request.getRequestDispatcher("admin_panel.jsp?menu=3");
							dispatcher.forward(request, response);		
							return;
						}
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("admin_panel.jsp");
					dispatcher.forward(request, response);			
				}
			}
			else {
				response.sendRedirect("index.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errores="", success="", nombre, precio, cantidad;
		int codigo, dsptch = 0;
		
		if(request.getParameter("producto").equals("nuevo")) {
			dsptch = 1;
			nombre = request.getParameter("nombre").trim().toLowerCase();
			precio = request.getParameter("precio").trim();
			cantidad = request.getParameter("cantidad").trim();
			if(Validaciones.validaCaracteres(nombre)) {
				if(Validaciones.validaNum(precio)) {
					if(Validaciones.validaNumInt(cantidad)) {
						Double precioOk = Double.parseDouble(precio);
						int cantidadOk = Integer.parseInt(cantidad);
						if(cantidadOk >= 0 & precioOk >= 0) {
							if(!Stock.getInstance().existeProducto(nombre)) {
								if(Stock.getInstance().addProducto(nombre, precioOk, cantidadOk)) {
									success = "Producto agregado a la base de datos.";
								}
								else errores = "Error al momento de agregar a la base de datos.";
							}
							else errores = "El producto ya existe.";
						}
						else errores = "El precio y la cantidad no pueden ser negativos.";
					}
					else errores = "Cantidad invalida, verifique.";
				}
				else errores = "Precio invalido, verifique.";
			}
			else errores = "El nombre es invalido.";
		}
		if(request.getParameter("producto").equals("modificar")) {
			dsptch = 2;
			nombre = request.getParameter("nombre").trim().toLowerCase();
			precio = request.getParameter("precio").trim();
			cantidad = request.getParameter("cantidad").trim();
			if(Validaciones.validaNumInt(request.getParameter("codigoProducto").trim())){
				codigo = Integer.parseInt(request.getParameter("codigoProducto").trim());
				if(Validaciones.validaCaracteres(nombre)) {
					if(Validaciones.validaNum(precio)) {
						if(Validaciones.validaNumInt(cantidad)) {
							Double precioOk = Double.parseDouble(precio);
							int cantidadOk = Integer.parseInt(cantidad);
							if(cantidadOk >= 0 & precioOk > 0) {
								if(!Stock.getInstance().updateNombre(nombre, codigo)) {
									if(Stock.getInstance().updateProducto(codigo, nombre, precioOk, cantidadOk)) {
										success = "Producto modificado.";
									}
									else errores = "Error al momento de modificar en la base de datos.";
								}
								else errores = "El producto ya existe.";
							}
							else errores = "El precio y la cantidad no pueden ser negativos. Y el precio no puede ser 0";
						}
						else errores = "Cantidad invalida, verifique.";
					}
					else errores = "Precio invalido, verifique.";
				}
				else errores = "El nombre es invalido.";
			}
			else errores = "Seleccione un producto primero.";
		}
		
		request.setAttribute("errores", errores);
		request.setAttribute("success", success);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_panel.jsp?menu=2&opt="+dsptch);
		dispatcher.forward(request, response);
	}
}
