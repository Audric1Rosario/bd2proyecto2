package visual.Categorias;

import java.io.Serializable;

public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private String clasificacion;
	private boolean listar;		// Listar para el borrado lógico.
	
	public Categoria(String nombre, String descripcion, String clasificacion) {
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
	
	public boolean isListar() {
		return listar;
	}
	
	public void setListar(boolean listar) {
		this.listar = listar;
	}

}
