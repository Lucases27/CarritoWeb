package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

public class DBConnection {
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DBNAME = "tecnojava_tp1";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	
	public Connection getConexion() {
		Connection conexion = null;
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL+DBNAME, USUARIO, CLAVE);
			System.out.println("Se logró conectar perfectamente");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: No se encontró la clase.");
		} catch (SQLException e) {
			System.out.println("Error: No se pudo conectar.");
		}
		return conexion;
	}
	
	/**
	 * Se le pasa una query que puede ser INSERT, UPDATE o DELETE y ademas una conección. No retorna nada.
	 * @param Query
	 * @throws SQLException
	 */
	public void sqlUpdate(String Query, Connection con) throws SQLException {
		Statement stmt =  con.createStatement();
		//insert update o delete
		int resultado = stmt.executeUpdate(Query);
		System.out.println(resultado); //borrar despues
	}
	
	/**
	 * Se le pasa una query que puede ser INSERT, UPDATE o DELETE. No retorna nada.
	 * @param Query
	 * @throws SQLException
	 */
	public void sqlUpdate(String Query) {
		Connection con = getConexion();
		Statement stmt;
		try {
			stmt = con.createStatement();
			//insert update o delete
			int resultado = stmt.executeUpdate(Query);
			con.close();
			System.out.println(resultado+"holis");//borrar despues
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Se le pasa un select y una coneccion buscando algo y devuelve un booleano.
	 * @param Select : el string del select que se va a buscar.
	 * @param Connection con
	 * @return true si existe en la tabla, false en caso contrario.
	 */
	
	public boolean sqlExist(String Select, Connection con) {
		boolean exist=false;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Select);
			exist = rs.next()?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	/**
	 * Se le pasa un select buscando algo y devuelve un booleano.
	 * @param Select : el string del select que se va a buscar.
	 * @return true si existe en la tabla, false en caso contrario.
	 */
	public boolean sqlExist(String Select) {
		boolean exist=false;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = getConexion();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Select);
			exist = rs.next()?true:false;
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
		
	
	/**
	 * Se le pasa un select, y devuelve un resultset que usted deberá procesar.
	 * @param Select 
	 * @return El object ResultSet del select en cuestion. O false si no contiene nada.
	 */
	public ResultSet sqlSelect(String Select, Connection con) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Select);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	public ResultSet sqlSelect(String Select) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = getConexion();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(Select);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
