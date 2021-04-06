package logic.modelos;

import java.io.Serializable;

public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_categoria;
	private String nombre;
	private String descripcion;
	private String clasificacion;
	private boolean listar;		// Listar para el borrado lógico.
	
	public Categoria() {
		super();
	}

	public Categoria(String nombre, String clasificacion, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.clasificacion = clasificacion;
		this.listar = true;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getClasificacion() {
		return clasificacion;
	}
	
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	
	
	
	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public boolean isListar() {
		return listar;
	}
	
	public void setListar(boolean listar) {
		this.listar = listar;
	}
}
