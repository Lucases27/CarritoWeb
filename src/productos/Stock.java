package productos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import util.DBConnection;


public class Stock{
	//Guarda codigos como keys y productos como value.
	private HashMap<Integer, Producto> productosLista = new HashMap<Integer, Producto>();
	private static Stock Stock = null;
	
	private Stock() {
	}
	
	public static Stock getInstance() {
		if(Stock == null){
			Stock = new Stock();
		}
		return Stock;
	}
	
	
	/**
	 * Agrega un nuevo producto a la base de datos. Previamente comprueba que no exista el codigo unico para cada producto.
	 * @param codigo
	 * @param nombre
	 * @param precio	
	 * @param cantidad
	 * @return true si el producto fue agregado, false en caso de que ya exista.
	 * @throws SQLException
	 */
	public boolean addProducto(String nombre, double precio, int cantidad) {
		if(existeProducto(nombre)) {
			return false;
		}
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("INSERT INTO Productos (nombre,precio,cantidad)"
								+ " VALUES ('"+nombre+"','"+precio+"','"+cantidad+"')",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Modifica los datos del producto, no devuelve nada.
	 * @param user
	 * @param nick
	 * @param pass
	 * @param email
	 * @throws SQLException
	 */
	public boolean updateProducto(int codigo, String nombre, double precio, int cantidad) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE Productos SET Nombre='"+nombre+"', Precio ='"+precio+"', Cantidad ='"+cantidad+"' WHERE Codigo='"+codigo+"'",con);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Borra un producto segun su codigo unico. no devuelve nada
	 * @param codigo
	 * @throws SQLException
	 */
	public boolean deleteProducto(int codigo) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("DELETE FROM Productos WHERE Codigo='"+codigo+"'",con);
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	/**
	 * Trae todos los productos de la tabla Productos y devuelve un arraylist de Producto
	 * @return Arraylist del objeto Producto.
	 * @throws SQLException
	 */
	public ArrayList<Producto> getProductoLista() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet productos = DBConnection.sqlSelect("SELECT * FROM PRODUCTOS",con);
		ArrayList<Producto> prodList = new ArrayList<Producto>();
		try {
			while(productos.next()) {
				int codigo = productos.getInt(1);
				String nombre = productos.getString(2);
				Double precio = productos.getDouble(3);
				int cantidad = productos.getInt(4);	
				Producto producto = new Producto(codigo,nombre,precio,cantidad);
				prodList.add(producto);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prodList;
	}
	
	/**
	 * Obtiene los datos de un producto segun su codigo unico.
	 * @param codigo
	 * @return El objeto Producto con todos sus datos.
	 * @throws SQLException
	 */
	public Producto getProducto(int codigoProducto) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet productos = DBConnection.sqlSelect("SELECT * FROM PRODUCTOS WHERE Codigo='"+codigoProducto+"'", con);
		Producto producto = null;
		try {
			while(productos.next()) {
				int codigo = productos.getInt(1);
				String nombre = productos.getString(2);
				Double precio = productos.getDouble(3);
				int cantidad = productos.getInt(4);	
				producto = new Producto(codigo,nombre,precio,cantidad);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	
	/**
	 * Busca el codigo unico de un producto dado.
	 * @param id del producto a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeProducto(int codigo) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Codigo from Productos where Codigo='"+codigo+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca el nombre de un producto dado.
	 * @param id del producto a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeProducto(String nombre) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Nombre from Productos where Nombre='"+nombre.trim().toLowerCase()+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	

	/**
	 * Setea la cantidad de un producto, segun su codigo.
	 * @param codigo.
	 * @param cantidad. 
	 */
	public void setCantidad(int codigo, int cantidad) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		
		try {
			DBConnection.sqlUpdate("UPDATE Productos SET Cantidad='"+cantidad+"' WHERE Codigo='"+codigo+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public HashMap<Integer,Producto> getStock() {
		return productosLista;
	}
	
	/**
	 * Setea el hashmap temporal de productos desde otro hashmap.
	 * @param productos
	 */
	public void setStock(HashMap<Integer, Producto> productos) {
		productosLista.clear();
		for (Integer key : productos.keySet()) {
			productosLista.put(key, productos.get(key));
		}
	}
	
	/**
	 *  Busca la key, si existe devuele true 
	 *  
	 */
	public boolean isCod(int codigo) {
		return productosLista.containsKey(codigo);
	}
	
	/**
	 * borra un objecto producto de la lista.
	 * @param codigo
	 * @return true si se ha borrado, false en caso que no exista.
	 */
	public boolean delProd(int codigo) {
		boolean bool=false;
		if(isCod(codigo)) {
			productosLista.remove(codigo);
			bool=true;
		}
		return bool;
	}
	
	/**
	 * En realidad no hace el update, solo comprueba de que no exista un nombre en la DB sin tener en cuenta el que ya tengo con mi propia id.
	 * @param nombre
	 * @param codigo
	 * @return true si existe, false si no.
	 */
	public boolean updateNombre(String nombre, int codigo) {
		DBConnection DBConnection = new DBConnection();
		boolean ok = DBConnection.sqlExist("Select Nombre from Productos where Nombre='"+nombre.trim().toLowerCase()+"' and Codigo<>"+codigo);
		return ok;
	}
}
