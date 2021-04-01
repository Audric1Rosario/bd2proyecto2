package logic;

import java.io.Serializable;
import java.util.ArrayList;

import visual.Categorias.Categoria;

public class Juego implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Juego instancia = null;
	private ArrayList<Categoria> categorias;
	//private ArrayList<Cliente> clientes;
	//private ArrayList<Arcade> arcades;
	//private ArrayList<Puntuacion> puntuaciones;
	//private ArrayList<Premio> premios;
	//private ArrayList<Usuario> usuarios;
	
	//private Configuracion opcionesSistema;
	
	private Juego() {
		super();
		// Inicializar 
		this.setCategorias(new ArrayList<Categoria>());
		//this.clientes = new ArrayList<Cliente>();
		//this.arcades = new ArrayList<Arcade>();
		//this.puntuaciones = new ArrayList<Puntuacion>();
		//this.premios = new ArrayList<Premio>();
		//this.usuarios = new ArrayList<Usuario>();
		//this.opcionesSistema = new Configuracion();
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	// 0. Crear una sola clase controladora
	// Patrón singleton
	private synchronized static void createInstance() {
		if (instancia == null) {
			instancia = new Juego();
		}
	}

	public static Juego getInstance() {
		if (instancia == null) 
			createInstance();
		return instancia;
	}

	public static void setInstance(Juego data) {
		if (data != null)
			instancia = data;
	}
	
	//Buscar categorias
	
	public Categoria buscarCategoriaByNombre(String nombreCategoria) {
		Categoria buscado = null;
		boolean encontrado = false; int aux = 0;
		while (!encontrado && aux < categorias.size()) {
			if (categorias.get(aux).getNombre().equalsIgnoreCase(nombreCategoria) && categorias.get(aux).isListar()) {
				buscado = categorias.get(aux);
				encontrado = true;
			}
			aux++;
		}
		return buscado;
	}
	
	//Crear 
	
	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
	}
	
	//Verificar
	
	public boolean verificarCategoria(String nombreCategoria) {
		int aux = 0;
		boolean existe = false;
		while (!existe && aux < categorias.size()) {
			if (categorias.get(aux).getNombre().equalsIgnoreCase(nombreCategoria) && categorias.get(aux).isListar()) {
				existe = true;
			}
			aux++;
		}
		return !existe;
	}
	
}
