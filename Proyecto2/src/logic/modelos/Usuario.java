package logic.modelos;

import java.sql.Date;

public class Usuario extends Persona {
	private int id_jugador;
	private int id_central;
	private String nombre_usuario;
	private String clave;
	private int monedas;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(int id_persona, String nombre, String apellido, char sexo, Date fecha_nacimiento, String correo,
			String telefono, int id_direccion) {
		super(id_persona, nombre, apellido, sexo, fecha_nacimiento, correo, telefono, id_direccion);
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String apellido, char sexo, Date fecha_nacimiento, String correo, String telefono,
			int id_direccion) {
		super(nombre, apellido, sexo, fecha_nacimiento, correo, telefono, id_direccion);
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String apellido, char sexo) {
		super(nombre, apellido, sexo);
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String apellido, Date fecha_nacimiento, String correo) {
		super(nombre, apellido, fecha_nacimiento, correo);
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String apellido, String correo) {
		super(nombre, apellido, correo);
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String apellido, String correo, int id_central, String nombre_usuario, String clave,
			int monedas) {
		super(nombre, apellido, correo);
		this.id_central = id_central;
		this.nombre_usuario = nombre_usuario;
		this.clave = clave;
		this.monedas = monedas;
	}
	public Usuario(String nombre, String apellido, char sexo, Date fecha_nacimiento, String correo, String telefono,
			int id_direccion, int id_central, String nombre_usuario, String clave, int monedas) {
		super(nombre, apellido, sexo, fecha_nacimiento, correo, telefono, id_direccion);
		this.id_central = id_central;
		this.nombre_usuario = nombre_usuario;
		this.clave = clave;
		this.monedas = monedas;
	}
	public Usuario(String nombre_usuario, String clave, int monedas) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.clave = clave;
		this.monedas = monedas;
	}
	public int getId_jugador() {
		return id_jugador;
	}
	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}
	public int getId_central() {
		return id_central;
	}
	public void setId_central(int id_central) {
		this.id_central = id_central;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getMonedas() {
		return monedas;
	}
	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}
	
	
	
}
