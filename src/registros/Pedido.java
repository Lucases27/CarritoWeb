package registros;

import java.sql.Date;

public class Pedido {
	private int nPedido;
	private int nCliente;
	private Date fecha;
	private String estado;
	
	public Pedido(int nPedido, int nCliente, Date fecha, String estado) {
		this.nPedido = nPedido;
		this.nCliente = nCliente;
		this.fecha = fecha;
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getnCliente() {
		return nCliente;
	}
	public void setnCliente(int nCliente) {
		this.nCliente = nCliente;
	}
	public int getnPedido() {
		return nPedido;
	}
	public void setnPedido(int nPedido) {
		this.nPedido = nPedido;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
