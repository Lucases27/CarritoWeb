package productos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import util.DBConnection;
import util.Monedero;

public class Cart {
	
	private static Cart Carrito = null;
	private HashMap<Integer, Producto> carrito = new HashMap<Integer, Producto>();
	private ArrayList<Integer> listaProductos = new ArrayList<Integer>();
	private Double total = 0.0;
	
	private Cart() {
	}
	
	public static Cart getInstance() {
		if(Carrito == null){
			Carrito = new Cart();
		}
		return Carrito;
	}
	
	/**
	 * Agrega un nuevo producto al carrito.
	 * @param id
	 */
	public void agregarACarrito(int id, int cantidad) {
		Producto producto = Stock.getInstance().getProducto(id);
		producto.setCantidad(cantidad);
		if (existeEnCarrito(id)==false) {
			carrito.put(id, producto);
		}
		else {
			int carritoCantidad = carrito.get(id).getCantidad();
			carritoCantidad = cantidad;
			carrito.get(id).setCantidad(carritoCantidad);
		}
	}
	
	/**
	 * Busca si existe un producto en el carrito.
	 * @param id
	 * @return true si ya existe.
	 */
	public boolean existeEnCarrito(int id) {
		return carrito.containsKey(id);
	}
	
	/**
	 * Quita un item de la lista carrito y hace el descuento correspondiente al total de la factura a pagar.
	 * @param id
	 * @return
	 */
	public boolean quitarDeCarrito(int id) {
		if (existeEnCarrito(id)) {
			Producto producto = carrito.remove(id);
			total -= producto.getPrecio()*producto.getCantidad();
			return true;
		}
		else return false;
	}
	
	/**
	 * Devuelve un arraylist de Productos, desde el hashmap carrito.
	 * @return
	 */
	public ArrayList<Producto> getListaProductos(){
		ArrayList<Producto> lista = new ArrayList<Producto>();
		for (Producto producto : carrito.values()) {
			lista.add(producto);
		}
		return lista;
	}
	
	
	/**
	 * Agrega un item (codigo de producto) a una lista temporal de items sobre los que el usuario aun
	 * no selecciono la cantidad.
	 * @param codigo
	 */
	public void agregarALista(int codigo) {
		listaProductos.add(codigo);
	}
	
	/**
	 * Elimina un item de la lista de codigos de productos. 
	 * @param codigo
	 */
	public void quitarDeLista(int codigo) {
		listaProductos.remove((Object)codigo);
	}
	
	/**
	 * devuelve un arraylist de enteros, es decir solo los codigos de los productos seleccionados por el usuario.
	 * Se utiliza para obtener solamente los productos que se van a cargar y no su precio y cantidad.
	 * A partir de esta lista, se genera la siguiente lista con todos los datos.
	 * @return
	 */
	public ArrayList<Integer> getListaCarrito(){
		return listaProductos;
	}
	
	
	public boolean existeEnLista(int codigo){
		return listaProductos.contains((int)codigo);
	}
	
	/**
	 * Devuelve el total 
	 * @return
	 */
	public double getTotal() {
		total = 0.0;
		for (Producto prod : carrito.values()) {
			total += prod.getTotal();
		}
		return total;
	}
	
	/**
	 * Hace un clear a la lista de productos, para que cuando toquemos comprar muchas veces, no se sumen incorrectamente.
	 */
	public void clearList() {
		carrito.clear();
		total = 0.0;
		listaProductos.clear();
	}
	
	public boolean crearPedido(int id) {
		int npedido = 0;
		DBConnection db = new DBConnection();
		Connection con = db.getConexion();
		try {
			db.sqlUpdate("INSERT INTO Pedidos (NCliente,Estado)"
								+ " VALUES ('"+id+"','Solicitado')",con);
			ResultSet rs = db.sqlSelect("SELECT NPedido from Pedidos where NCliente="+id+" order by NPedido ASC",con);
			while(rs.next()) {
				npedido = rs.getInt("NPedido");
			}
			for (Producto prod : carrito.values()) {
				db.sqlUpdate("INSERT INTO Pedidos_detalles (NPedido,Codigo,Producto,Cantidad,PrecioUnidad,Total)"
						+ " VALUES ('"+npedido+"','"+prod.getCodigo()+"','"+prod.getNombre()+"','"+prod.getCantidad()+"','"+prod.getPrecio()+"','"+prod.getTotal()+"')",con);
				db.sqlUpdate("UPDATE Productos SET Cantidad = Cantidad - '"+prod.getCantidad()+"' WHERE Codigo='"+prod.getCodigo()+"'",con);
				
			}
			Monedero.getInstance().restarSaldo(total, id);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	

}
