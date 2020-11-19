package util;

import usuarios.Usuario;
import usuarios.Usuarios;

public class Monedero {
	public static Monedero Monedero = null;
	
	private Monedero() {	
	}
	
	public static Monedero getInstance() {
		if (Monedero==null) {
			Monedero = new Monedero();
		}
		return Monedero;
	}
	
	/**
	 * Carga el saldo en la cuenta del usuario. Aqui se supone que se deberia implementar la verificacion del pago.
	 * @param monto
	 * @param id
	 * @return
	 */
	public boolean cargarSaldo(double monto, int id, String metodoPago) {
		DBConnection db = new DBConnection();
		db.sqlUpdate("update usuarios set saldo=saldo+"+monto+" where ID='"+id+"'");
		return true;
	}
	
	/**
	 * Resta el saldo en la cuenta del usuario. Aqui se supone que se deberia implementar la verificacion del pago.
	 * @param monto
	 * @param id
	 * @return
	 */
	public boolean restarSaldo(double monto, int id) {
		DBConnection db = new DBConnection();
		db.sqlUpdate("update usuarios set saldo=saldo-"+monto+" where ID='"+id+"'");
		return true;
	}
	
	
	
	
	
	
	
}
