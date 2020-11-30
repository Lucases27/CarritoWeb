package usuarios;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import productos.Producto;
import util.*;

public class Usuarios {
	
	public static Usuarios Usuarios = null;
	Usuario usuario = null;
	
	private Usuarios() {	
	}
	
	public static Usuarios getInstance() {
		if (Usuarios==null) {
			Usuarios = new Usuarios();
		}
		return Usuarios;
	}
	
	/**
	 * Agrega un nuevo usuario a la base de datos. Previamente comprueba que no exista el usuario.
	 * @param user
	 * @param pass
	 * @param nick
	 * @param email
	 * @return true si el usuario fue agregado, false en caso de que ya exista.
	 * @throws SQLException
	 */
	public boolean addUser(String user, String pass, String nick, String email) {
		if(existeUsuario(user)) {
			return false;
		}
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("INSERT INTO Usuarios (Usuario,Password,Nick,Email,Activo,Saldo,isAdmin)"
								+ " VALUES ('"+user+"','"+pass+"','"+nick+"','"+email+"',1,0,0)",con);
			System.out.println("se agrego supuestamente");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("> " +user+" - "+ pass+ " - "+ nick+" - "+email+" <" );
		}
		
		return true;
	}
	
	/**
	 * Modifica los datos del usuario, no devuelve nada.
	 * @param user
	 * @param nick
	 * @param pass
	 * @param email
	 * @throws SQLException
	 */
	public void updateUser(String user, String nick, String pass, String email) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE Usuarios SET Nick='"+nick+"', Password='"+pass+"', Email ='"+email+"' WHERE Usuario='"+user+"'",con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Borra un usuario segun su nombre de usuario. no devuelve nada
	 * @param user
	 * @throws SQLException
	 */
	public void deleteUser(String user) throws SQLException {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		DBConnection.sqlUpdate("DELETE FROM Usuarios WHERE Usuario='"+user+"'",con);
		con.close();
	}
	
	/**
	 * Trae todos los usuarios de la tabla usuarios y devuelve un arraylist de Usuario
	 * @return Arraylist del objeto Usuario.
	 * @throws SQLException
	 */
	public ArrayList<Usuario> getUserList() {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM USUARIOS",con);
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nick = users.getString(4);
				String email = users.getString(5);
				boolean activo = users.getBoolean(6);
				Double saldo = users.getDouble(7);
				boolean isAdmin = users.getBoolean(8);
				
				Usuario usuario = new Usuario(user,pass,nick,email);
				usuario.setId(id);
				usuario.setAdmin(isAdmin);
				usuario.setSaldo(saldo);
				usuario.setActivo(activo);
				userList.add(usuario);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	/**
	 * Obtiene los datos de un usuario segun su nombre de usuario
	 * @param userName
	 * @return El objeto Usuario con todos sus datos.
	 * @throws SQLException
	 */
	public Usuario getUser(String userName) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM USUARIOS WHERE Usuario='"+userName+"'", con);
		
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nick = users.getString(4);
				String email = users.getString(5);
				boolean activo = users.getBoolean(6);
				Double saldo = users.getDouble(7);
				boolean isAdmin = users.getBoolean(8);
				
				usuario = new Usuario(user,pass,nick,email);
				usuario.setId(id);
				usuario.setAdmin(isAdmin);
				usuario.setSaldo(saldo);
				usuario.setActivo(activo);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	/**
	 * Obtiene los datos de un usuario segun su ID de usuario
	 * @param userName
	 * @return El objeto Usuario con todos sus datos.
	 * @throws SQLException
	 */
	public Usuario getUser(int idUsuario) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		ResultSet users = DBConnection.sqlSelect("SELECT * FROM USUARIOS WHERE ID='"+idUsuario+"'", con);
		
		try {
			while(users.next()) {
				int id = users.getInt(1);
				String user = users.getString(2);
				String pass = users.getString(3);
				String nick = users.getString(4);
				String email = users.getString(5);
				boolean activo = users.getBoolean(6);
				Double saldo = users.getDouble(7);
				boolean isAdmin = users.getBoolean(8);
				
				usuario = new Usuario(user,pass,nick,email);
				usuario.setId(id);
				usuario.setAdmin(isAdmin);
				usuario.setSaldo(saldo);
				usuario.setActivo(activo);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
		
	
	/**
	 * Este metodo retorna que tipo de usuario es segun su ID.
	 * @param id (int): ID del user que queremos saber que tipo de ususario es.
	 * @return True si es administrador. False si no lo es.
	 */
	public Boolean isAdmin(int id){
		DBConnection dbConnection = new DBConnection();
		Connection con = dbConnection.getConexion();
		ResultSet rs = dbConnection.sqlSelect("Select isAdmin from Usuarios where ID="+id,con);
		boolean isAdmin=false;
		try {
			while(rs.next()){
				isAdmin=rs.getBoolean(1);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAdmin;
	}
	
	/**
	 * Setea el parametro booleano de isAdmin a True.
	 * @param id (int): El id del user que quiero que sea administrador.
	 */
	public void setAdmin(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE Usuarios SET isAdmin='1' WHERE ID='"+id+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setea el parametro booleano de isAdmin a False.
	 * @param id (int): El id del user que NO quiero que sea administrador.
	 */
	
	public void removeAdmin(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		try {
			DBConnection.sqlUpdate("UPDATE Usuarios SET isAdmin='0' WHERE ID='"+id+"'",con);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprueba que un usuario y contraseña se correspondan.
	 * @param user
	 * @param pass
	 * @return True si encuentra coincidencia, False de lo contrario.
	 */
	public boolean validaUser(String user, String pass) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from Usuarios where Usuario='"+user+"' and Password='"+pass+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca un usuario mediante su nombre de usuario unico.
	 * @param usuario string del nombre de usuario a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeUsuario(String user) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from Usuarios where Usuario='"+user+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	/**
	 * Busca un usuario mediante su id unico.
	 * @param id int del usuario a buscar.
	 * @return true si existe, false si no.
	 */
	public boolean existeUsuario(int id) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		boolean ok = DBConnection.sqlExist("Select Usuario from Usuarios where Usuario='"+id+"'",con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	
	public boolean existeEmail(String email) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Email from Usuarios where email='"+email+"'");
	}
	
	public boolean updateEmail(String email, int id) {
		DBConnection dbConnection = new DBConnection();
		return dbConnection.sqlExist("SELECT Email from Usuarios where email='"+email+"' and id<>"+id);
	}
	
	/**
	 * Setea el estado Activo/No activo a un usuario, segun su id.
	 * @param id 
	 * @param isAdmin 1 para setear el estado de activo, 0 en caso contrario. 
	 */
	public void setEstado(int id, int activo) {
		DBConnection DBConnection = new DBConnection();
		Connection con = DBConnection.getConexion();
		
		try {
			DBConnection.sqlUpdate("UPDATE Usuarios SET Activo='"+activo+"' WHERE ID='"+id+"'",con);
			System.out.println(id);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga la session del usuario. ejemplo: en el login.
	 * @param user
	 * @param pass
	 * @param session
	 */
	public void cargarSession(String user, String pass, HttpSession session) {
		ArrayList<Integer> listaProductos = new ArrayList<Integer>();
		session.setAttribute("user", user);
		session.setAttribute("pass", pass);		
		session.setAttribute("email", getInstance().getUser(user).getEmail());
		session.setAttribute("nick", getInstance().getUser(user).getNick());
		session.setAttribute("id", getInstance().getUser(user).getId());
		session.setAttribute("saldo", getInstance().getUser(user).getSaldo());
		session.setAttribute("activo", getInstance().getUser(user).getActivo());
		session.setAttribute("logeado", true);
		session.setAttribute("listaProductos", listaProductos);
		if(getInstance().isAdmin((Integer)session.getAttribute("id"))) {
			session.setAttribute("isAdmin", true);
			System.out.println(session.getAttribute("id"));
		}
		else {
			session.setAttribute("isAdmin", false);
			System.out.println(session.getAttribute("id"));
		}
	}
	
	/**
	 * Carga la session del usuario, una vez que ya se encuentra logeado. ejemplo: cada vez que volvemos al perfil de usuario.
	 * @param user
	 * @param pass
	 * @param session
	 */
	public void cargarSession(String user,HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("pass", getInstance().getUser(user).getPassword());		
		session.setAttribute("email", getInstance().getUser(user).getEmail());
		session.setAttribute("nick", getInstance().getUser(user).getNick());
		session.setAttribute("id", getInstance().getUser(user).getId());
		session.setAttribute("saldo", getInstance().getUser(user).getSaldo());
		session.setAttribute("activo", getInstance().getUser(user).getActivo());
		session.setAttribute("fechaAlta", getInstance().getUser(user).getFechaAlta());
		session.setAttribute("logeado", true);
		if(getInstance().isAdmin((Integer)session.getAttribute("id"))) {
			session.setAttribute("isAdmin", true);
			System.out.println(session.getAttribute("id"));
		}
		else {
			session.setAttribute("isAdmin", false);
			System.out.println(session.getAttribute("id"));
		}
	}
	
}
