package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import productos.Cart;
import productos.Producto;
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
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Double miSaldo = (Double)session.getAttribute("saldo");
		int codigo;
		String success="", errores="";
		
		if(request.getParameter("agregar") != null) {
			codigo = Integer.parseInt(request.getParameter("agregar")); 
			((ArrayList<Integer>) session.getAttribute("listaProductos")).add(codigo);
			success = "Producto agregado al carrito!";
			request.setAttribute("success", success);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if(request.getParameter("comprar") != null) {
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
		if(request.getParameter("eliminar") != null) {
			
			codigo = Integer.parseInt(request.getParameter("eliminar"));
			((ArrayList<Integer>)session.getAttribute("listaProductos")).remove((Object)codigo);
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
		if(request.getParameter("confirmarCompra") != null) {
			if((double)session.getAttribute("totalCompra")>miSaldo) {
				errores ="Tu saldo es insuficiente para realizar la compra. Debe realizar una recarga.";
			}
			else {
				if(((ArrayList<Integer>)session.getAttribute("listaProductos")).isEmpty()) {
					errores = "No hay productos para en el pedido.";
				}
				else {
					if(Cart.getInstance().crearPedido(session)) {
						success = "Pedido creado con exito!";
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
			return;
		}
		if(session.getAttribute("logeado") != null) {
			response.sendRedirect("profile.jsp?menu=3");
			return;
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String success="", errores="";
		double total = 0.0;
		HashMap<Integer, Producto> carrito = new HashMap<Integer, Producto>();
		HttpSession session = request.getSession();
		
		if(((ArrayList<Integer>)session.getAttribute("listaProductos")).size()==0) {
			errores = "Lista vacia, primero agrege algun producto!";
			request.setAttribute("errores", errores);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=3");
			dispatcher.forward(request, response);
			return;
		}
		
		for (int codigo : (ArrayList<Integer>)session.getAttribute("listaProductos")) {
			if(Validaciones.validaNumInt(request.getParameter("cantidadDe"+codigo))) {
				int cantidad = Integer.parseInt(request.getParameter("cantidadDe"+codigo));
				if(cantidad>=1) {
					if(Stock.getInstance().getProducto(codigo).getCantidad()>=cantidad) {
						Producto producto = Stock.getInstance().getProducto(codigo);
						producto.setCantidad(cantidad);
						total += producto.getTotal();
						carrito.put(codigo,producto);
						//Cart.getInstance().agregarACarrito(codigo, cantidad);
						success = "Cantidad agregada.";			
						errores = "";
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
			session.setAttribute("totalCompra", total);
			session.setAttribute("carritoCompra", carrito);
			request.setAttribute("total", total);
			request.setAttribute("carrito", carrito);
			request.setAttribute("errores", errores);
			request.setAttribute("success", success);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=5");
			dispatcher.forward(request, response);
		}
	}
}
