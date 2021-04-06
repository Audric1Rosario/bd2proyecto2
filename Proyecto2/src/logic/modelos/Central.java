package logic.modelos;

public class Central {
	private int id_central;
	private int id_direccion;
	private String nombre;
	private int numero;
	private String telefono;
	
	public Central() {
		super();
	}

	public Central(int id_direccion, String nombre, int numero, String telefono) {
		super();
		this.id_direccion = id_direccion;
		this.nombre = nombre;
		this.numero = numero;
		this.telefono = telefono;
	}

	public Central(int id_central, int id_direccion, String nombre, int numero, String telefono) {
		super();
		this.id_central = id_central;
		this.id_direccion = id_direccion;
		this.nombre = nombre;
		this.numero = numero;
		this.telefono = telefono;
	}

	public int getId_central() {
		return id_central;
	}

	public void setId_central(int id_central) {
		this.id_central = id_central;
	}

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
