package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import productos.Producto;
import registros.Pedidos;

/**
 * Servlet implementation class HistorialCompras
 */
@WebServlet("/HistorialCompras")
public class HistorialCompras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistorialCompras() {
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
			return;
		}
		if(request.getParameter("detalles") != null) {
			int nPedido = Integer.parseInt(request.getParameter("detalles"));
			double totalCompra = 0;
			for (Producto prod : Pedidos.getInstance().getDetallesPedido(nPedido)) {
				totalCompra += prod.getTotal();
			}
			request.setAttribute("pedidoN", Pedidos.getInstance().getDetallesPedido(nPedido));
			request.setAttribute("nPedido", nPedido);
			request.setAttribute("totalCompra", totalCompra);
			if(request.getParameter("admin") != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin_panel.jsp?menu=6");
				dispatcher.forward(request, response);
				return;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=6");
			dispatcher.forward(request, response);
		}
		
		/*
		if((boolean) session.getAttribute("isAdmin")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Admin_panel?menu=3");
			dispatcher.forward(request, response);
			return;
		}*/
		int id = (int)session.getAttribute("id");
		request.setAttribute("pedidos", Pedidos.getInstance().getListaPedido(id));
		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp?menu=4");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
