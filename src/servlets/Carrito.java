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
import util.Validaciones;

/**
 * Servlet implementation class Carrito
 */
@WebServlet("/Carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Double miSaldo = (Double)session.getAttribute("saldo");
		int codigo;
		String success="", errores="";
		
		
		if(request.getParameter("agregar") != null) {
			codigo = Integer.parseInt(request.getParameter("agregar")); 
			Cart.getInstance().agregarALista(codigo);
			Cart.getInstance().agregarACarrito(codigo, 0);
			success = "Producto agregado al carrito!";
			request.setAttribute("success", success);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		if(request.getParameter("comprar") != null) {
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
		if(request.getParameter("eliminar") != null) {
			Cart.getInstance().quitarDeLista(Integer.parseInt(request.getParameter("eliminar")));
			Cart.getInstance().quitarDeCarrito(Integer.parseInt(request.getParameter("eliminar")));
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
		if(request.getParameter("confirmarCompra") != null) {
			if(Cart.getInstance().getTotal()>miSaldo) {
				errores ="Tu saldo es insuficiente para realizar la compra. Debe realizar una recarga.";
			}
			else {
				if(Cart.getInstance().getListaProductos().isEmpty()) {
					errores = "No hay productos para en el pedido.";
				}
				else {
					if(Cart.getInstance().crearPedido((int)session.getAttribute("id"))) {
						success = "Pedido creado con exito!";
						Cart.getInstance().clearList();
					}
					else {
						errores = "Error al cargar en la base de datos.";
					}
				}
			}
			request.setAttribute("errores", errores);
			request.setAttribute("success", success);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=5");
			dispatcher.forward(request, response);
		}
		if(session.getAttribute("logeado") != null) {
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String success="", errores="";
		
		if(Cart.getInstance().getListaCarrito().size()==0) {
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
		
		for (int codigo : Cart.getInstance().getListaCarrito()) {
			if(Validaciones.validaNumInt(request.getParameter("cantidadDe"+codigo))) {
				int cantidad = Integer.parseInt(request.getParameter("cantidadDe"+codigo));
				if(cantidad>=1) {
					if(Stock.getInstance().getProducto(codigo).getCantidad()>=cantidad) {
						Cart.getInstance().agregarACarrito(codigo, cantidad);
						success = "Cantidad agregada.";						
					}
					else {
						String producto = Stock.getInstance().getProducto(codigo).getNombre();
						errores = "No hay stock suficiente para la cantidad solicitada de "+producto+" en estos momentos.";
					}
				}
				else {
					errores = "Ingrese una cantidad minima de 1.";
					break;
				}
			}
			else {
				errores = "Cantidad invalida";
				break;
			}
		}
		if(errores != "") {
			request.setAttribute("errores", errores);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=3");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("errores", errores);
			request.setAttribute("success", success);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=5");
			dispatcher.forward(request, response);
		}
	}
}
