package registros;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import productos.Producto;
import usuarios.Usuario;
import usuarios.Usuarios;
import util.DBConnection;

public class Pedidos {
	public static Pedidos Pedidos = null;
	Pedidos pedidos = null;
	
	private Pedidos() {	
	}
	
	public static Pedidos getInstance() {
		if (Pedidos==null) {
			Pedidos = new Pedidos();
		}
		return Pedidos;
	}
	
	/**
	 * Trae todos los pedidos de la tabla Pedidos y devuelve un arraylist de Pedido
	 * @return Arraylist del objeto Pedido.
	 * @throws SQLException
	 */
	public ArrayList<Pedido> getListaPedido() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM PEDIDOS",con);
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		try {
			while(pedidos.next()) {
				int nPedido = pedidos.getInt(1);
				int nCliente = pedidos.getInt(2);
				Date fecha = pedidos.getDate(3);
				String estado = pedidos.getString(4);	
				Pedido pedido = new Pedido(nPedido,nCliente,fecha,estado);
				listaPedidos.add(pedido);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPedidos;
	}
	
	
	
	/**
	 * Trae todos los pedidos de la tabla Pedidos correspondientes a un ID y devuelve un arraylist de Pedido.
	 * @return Arraylist del objeto Pedido.
	 * @throws SQLException
	 */
	public ArrayList<Pedido> getListaPedido(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM PEDIDOS WHERE NCLIENTE="+id,con);
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		try {
			while(pedidos.next()) {
				int nPedido = pedidos.getInt(1);
				int nCliente = pedidos.getInt(2);
				Date fecha = pedidos.getDate(3);
				String estado = pedidos.getString(4);	
				Pedido pedido = new Pedido(nPedido,nCliente,fecha,estado);
				listaPedidos.add(pedido);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPedidos;
	}
	
	
	public ArrayList<Producto> getDetallesPedido(int nPedido) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet pedidos = DBConnection.sqlSelect("SELECT * FROM Pedidos_detalles WHERE NPedido="+nPedido,con);
		ArrayList<Producto> pedido = new ArrayList<Producto>();
		try {
			while(pedidos.next()) {
				int codigo = pedidos.getInt(3);
				String nombre = pedidos.getString(4);
				int cantidad = pedidos.getInt(5);
				double precio = pedidos.getDouble(6);

				Producto producto = new Producto(codigo,nombre,precio,cantidad);
				pedido.add(producto);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pedido;
	}
	
	
	
	
	
}
