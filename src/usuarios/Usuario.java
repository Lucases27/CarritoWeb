package usuarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Usuario implements Comparable<Usuario>, Iterable{


	
	private int id;
	private String nombreUsuario;
	private String password;
	private String nick;
	private String email;
	private boolean activo;
	private boolean isAdmin;
	private Double saldo;
	private Date fechaAlta;

	public Date getFechaAlta() {
		return fechaAlta;
	}

	protected Usuario(String nombreUsuario, String password, String nick, String email) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.nick = nick;
		this.email = email;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getId() {
		return id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	
	public void save() {
		//guardar el objeto en la base de datos
		
	}

	public int compareTo(Usuario o) {
		Usuario usuarioAComparar = (Usuario) o;
		return this.getNombreUsuario().toLowerCase()
				.compareTo(usuarioAComparar.getNombreUsuario().toLowerCase());
	}
	
	public String toString() {
		return "ID: "+id+" Usuario: "+nombreUsuario+" Nick: "+nick+" Password: "+password+ " Email: "+email+
				" Activo = "+activo+ " isAdmin: "+isAdmin+" Saldo: "+saldo;
	}
	
	public boolean validarContrasenia(String contrasenia) {
		return this.getPassword().equals(contrasenia);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public Iterator<Object> iterator() {
		ArrayList<Object> userIterable = new ArrayList<Object>();
		userIterable.add(id);
		userIterable.add(nombreUsuario);
		userIterable.add(password);
		userIterable.add(nick);
		userIterable.add(email);
		userIterable.add(activo);
		userIterable.add(isAdmin);
		userIterable.add(saldo);
		return userIterable.iterator();
	}
	
	
	
}
