package productos;
import java.io.Serializable;

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	
	
	public Producto(int cod, String nombre, double precio, int cantidad) {
		this.codigo = cod;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public Producto(String nombre, double precio, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	//copiamos el contenido de otro objeto tipo Producto
	public Producto(Producto prodCopia) {
		this.codigo = prodCopia.codigo;
		this.nombre = prodCopia.nombre;
		this.cantidad = prodCopia.cantidad;
		this.precio = prodCopia.precio;
	}
	
	public Producto() {
	}

	public int getCantidad() {
		return cantidad;
	}

	public boolean setCantidad(int cantidad) {
		this.cantidad = cantidad;
		return true;
	}

	public int getCodigo() {
		return codigo;
	}

	public boolean setCodigo(int cod) {
		this.codigo = cod;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean setNombre(String descripcion) {
		this.nombre = descripcion;
		return true;
	}

	public double getPrecio() {
		return precio;
	}

	public boolean setPrecio(double precio) {
		this.precio = precio;
		return true;
	}
	
	public double getTotal() {
		return precio*cantidad;
	}
	
	public String toString() {
		return "Codigo: "+codigo+" Descripcion: "+nombre+" Precio: $"+precio+" Cantidad: "+cantidad;
	}
}
