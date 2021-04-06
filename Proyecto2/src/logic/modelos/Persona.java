package logic.modelos;

import java.sql.Date;

public class Persona {
	protected int id_persona;
	protected String nombre;
	protected String apellido;
	protected char sexo;
	protected Date fecha_nacimiento;
	protected String correo;
	protected String telefono;
	protected int id_direccion;
	
	public Persona() {
		super();
	}
	
	public Persona(String nombre, String apellido, char sexo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
	}

	public Persona(String nombre, String apellido, String correo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}

	public Persona(String nombre, String apellido, Date fecha_nacimiento, String correo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
	}

	public Persona(String nombre, String apellido, char sexo, Date fecha_nacimiento, String correo, String telefono,
			int id_direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.telefono = telefono;
		this.id_direccion = id_direccion;
	}

	public Persona(int id_persona, String nombre, String apellido, char sexo, Date fecha_nacimiento, String correo,
			String telefono, int id_direccion) {
		super();
		this.id_persona = id_persona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.telefono = telefono;
		this.id_direccion = id_direccion;
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}
	
	
}
