package logic;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.modelos.*;

public class Juego implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Juego instancia = null;
	private ArrayList<Arcade> arcades;
	private ArrayList<Categoria> categorias;
	private ArrayList<Central> centrales;
	private ArrayList<Premio> premios;
	private ArrayList<Puntuaciones> puntuaciones;
	private ArrayList<Ticket> tickets;
	private ArrayList<Usuario> usuarios;
	private Usuario currentUser;
	//private ArrayList<Persona> personas;
	
	private Juego() {
		super();
		this.setCategorias(new ArrayList<Categoria>());
		//this.clientes = new ArrayList<Cliente>();
		this.arcades = new ArrayList<Arcade>();
		this.puntuaciones = new ArrayList<Puntuaciones>();
		this.premios = new ArrayList<Premio>();
		this.usuarios = new ArrayList<Usuario>();
		this.tickets = new ArrayList<Ticket>();
		this.centrales = new ArrayList<Central>();
		//this.opcionesSistema = new Configuracion();
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	// 0. Crear una sola clase controladora
	// Patr�n singleton
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
	
	// Categoria
	public Boolean addCategoria(Categoria categoria) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO categoria (nombre, descripcion, clasificacion) values (?, ?, ?)");
				preparedStatement.setString(1, categoria.getNombre());
				preparedStatement.setString(2, categoria.getDescripcion());
				preparedStatement.setString(3, categoria.getClasificacion());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		//if (result) {
			//categorias.add(categoria);
		//}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Arcade
	public Boolean addArcade(Arcade arcade) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (arcade.getId_categoria() == 0 ||
			arcade.getId_central() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO arcade (id_central, id_categoria, nombre, ticket_imp, req_monedas) values (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, arcade.getId_central());
				preparedStatement.setInt(2, arcade.getId_categoria());
				preparedStatement.setString(3, arcade.getNombre());
				preparedStatement.setInt(4, arcade.getTicket_imp());
				preparedStatement.setInt(5, arcade.getReq_monedas());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				}
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Ticket
	public Boolean addTicket(Ticket ticket) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (ticket.getId_arcade() == 0 ||
			ticket.getId_jugador() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO ticket (id_arcade, id_jugador, nombre) values (?, ?, ?)");
				preparedStatement.setInt(1, ticket.getId_arcade());
				preparedStatement.setInt(2, ticket.getId_jugador());
				preparedStatement.setString(3, ticket.getNombre());
				
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Premio
	public Boolean addPremio(Premio premio) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (premio.getId_central() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO premio (id_central, nombre_premio, puntuacion_req, ticket_req, limite) values (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, premio.getId_central());
				preparedStatement.setString(2, premio.getNombre_premio());
				preparedStatement.setInt(3, premio.getPuntuacion_req());
				preparedStatement.setInt(4, premio.getTicket_req());
				preparedStatement.setInt(5, premio.getLimite());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Direccion
	public int addDireccion(Direccion direccion) {
		int result = 0;
		PreparedStatement preparedStatement;
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO direccion (pais, region, codigo_postal) values (?, ?, ?)");
				preparedStatement.setString(1, direccion.getPais());
				preparedStatement.setString(2, direccion.getRegion());
				preparedStatement.setInt(3, direccion.getCodigo_postal());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate(); // Statement.RETURN_GENERATED_KEYS
				if (!(fin > 0)) {
					result = -1;
				} 
				
				PreparedStatement newStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT MAX(id_direccion) AS max FROM direccion");
				ResultSet datos = newStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
				
				// Ahora obtener el id de la direccion generada... 
				//try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				//	while (generatedKeys.next()) {
				//		result = generatedKeys.getInt(1);
				//	}
					
					/*if (generatedKeys.next()) {
						result = generatedKeys.getLong(1);
					} else {
						result = -1;
						throw new SQLException("Error al crear una direccion, no se obtuvo una ID.");
					}*/
				//}
				
				JDBCPostgreSQLConnect.desconectar();
				
				
			} catch (SQLException e) { 
				e.printStackTrace();
				result = -1;
			}
		}
		//if (result) {
			//categorias.add(categoria);
		//}
		if (result == -1) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Central
	public Boolean addCentral(Central central) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (central.getId_direccion() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO central (id_direccion, nombre, numero, telefono) values (?, ?, ?, ?)");
				preparedStatement.setInt(1, central.getId_direccion());
				preparedStatement.setString(2, central.getNombre());
				preparedStatement.setInt(3, central.getNumero());
				preparedStatement.setString(4, central.getTelefono());
				
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				
				if (!(fin > 0)) {
					result = false;
				} 
				
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		//if (result) {
			//categorias.add(categoria);
		//}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Arcade
	public Boolean addPersona(Persona persona) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (persona.getId_direccion() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO persona (nombre, apellido, sexo, fecha_nacimiento, correo, telefono, id_direccion) values (?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, persona.getNombre());
				preparedStatement.setString(2, persona.getApellido());
				preparedStatement.setString(3, Character.toString(persona.getSexo()));
				preparedStatement.setDate(4, persona.getFecha_nacimiento());
				preparedStatement.setString(5, persona.getCorreo());
				preparedStatement.setString(6, persona.getTelefono());
				preparedStatement.setInt(7, persona.getId_direccion());
				
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		//if (result) {
			//categorias.add(categoria);
		//}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	// Arcade
	public Boolean addUsuario(Usuario usuario) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		if (usuario.getId_central() == 0 || 
			usuario.getId_direccion() == 0 || 
			usuario.getId_persona() == 0) {
			return false;
		}
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO jugador (id_persona, nombre, apellido, sexo, fecha_nacimiento, correo, telefono, id_direccion, id_central, nombre_usuario, clave, monedas)"+
				" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, usuario.getId_persona());
				preparedStatement.setString(2, usuario.getNombre());
				preparedStatement.setString(3, usuario.getApellido());
				preparedStatement.setString(4, Character.toString(usuario.getSexo()));
				preparedStatement.setDate(5, usuario.getFecha_nacimiento());
				preparedStatement.setString(6, usuario.getCorreo());
				preparedStatement.setString(7, usuario.getTelefono());
				preparedStatement.setInt(8, usuario.getId_direccion());
				preparedStatement.setInt(9, usuario.getId_central());
				preparedStatement.setString(10, usuario.getNombre_usuario());
				preparedStatement.setString(11, usuario.getClave());
				preparedStatement.setInt(12, usuario.getMonedas());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	public Boolean addPuntuaciones(Puntuaciones puntuaciones) {
		Boolean result = true;
		PreparedStatement preparedStatement;
		
		if (puntuaciones.getId_central() == 0 || puntuaciones.getId_jugador() == 0) {
			return false;
		}
		
		if (JDBCPostgreSQLConnect.getConnection() != null) {
			
			try {
				// Preparar inserci�n
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("INSERT INTO puntuaciones (id_central, id_jugador, nombre, monedas_usadas, tiempo_jugado) values (?, ?, ?, ?, ?)");
				preparedStatement.setInt(1, puntuaciones.getId_central());
				preparedStatement.setInt(2, puntuaciones.getId_jugador());
				preparedStatement.setString(3, puntuaciones.getNombre());
				preparedStatement.setInt(4, puntuaciones.getMonedas_usadas());
				preparedStatement.setTime(5, puntuaciones.getTiempo_jugado());
				// Iniciar sentencia
				int fin = preparedStatement.executeUpdate();
				if (!(fin > 0)) {
					result = false;
				} 
				JDBCPostgreSQLConnect.desconectar();
			} catch (SQLException e) { 
				e.printStackTrace();
				result = false;
			}
		}
		
		if (!result) {
			JDBCPostgreSQLConnect.desconectar();
		}
		return result;
	}
	
	//Busqueda por id. (especifico)
	
	// Arcade
	public Arcade buscarArcadePorId(int id) {
		Arcade buscar = new Arcade();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM arcade WHERE id_arcade = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();

				while (datos.next()) {
					// tomar arcade
					buscar.setId_arcade(id);
					buscar.setId_central(datos.getInt("id_central"));
					buscar.setNombre(datos.getString("nombre"));
					buscar.setTicket_imp(datos.getInt("ticket_imp"));
					buscar.setReq_monedas(datos.getInt("req_monedas"));				
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}

		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Categor�a
	public Categoria buscarCategoriaPorId(int id) {
		Categoria buscar = new Categoria();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM categoria WHERE id_categoria = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();

				while (datos.next()) {
					// tomar arcade
					buscar.setId_categoria(id);
					buscar.setNombre(datos.getString("nombre"));
					buscar.setDescripcion(datos.getString("descripcion"));
					buscar.setClasificacion(datos.getString("clasificacion"));
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Central
	public Central buscarCentralPorId(int id) {
		Central buscar = new Central();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM central WHERE id_central = "+id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();

				while (datos.next()) {
					// tomar arcade
					buscar.setId_central(id);
					buscar.setId_direccion(id);
					buscar.setNombre(datos.getString("nombre"));
					buscar.setNumero(datos.getInt("numero"));
					buscar.setTelefono(datos.getString("telefono"));
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Direccion
	public Direccion buscarDireccionPorId(int id) {
		Direccion buscar = new Direccion();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM direccion WHERE id_direccion = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				while (datos.next()) {
					// tomar arcade
					buscar.setId_direccion(id);
					buscar.setPais(datos.getString("nombre"));
					buscar.setRegion(datos.getString("region"));
					buscar.setCodigo_postal(datos.getInt("codigo_postal"));
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Persona
	public Persona buscarPersonaPorId(int id) {
		Persona buscar = new Persona();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM persona WHERE id_persona = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				while (datos.next()) {
					// tomar arcade
					buscar.setId_persona(id);
					buscar.setNombre(datos.getString("nombre"));
					buscar.setApellido(datos.getString("apellido"));
					buscar.setSexo(datos.getString("sexo").toCharArray()[0]);
					buscar.setFecha_nacimiento(datos.getDate("fecha_nacimiento"));
					buscar.setCorreo(datos.getString("correo"));
					buscar.setTelefono(datos.getString("telefono"));
					buscar.setId_direccion(datos.getInt("id_direccion"));
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Premio
	public Premio buscarPremioPorId(int id) {
		Premio buscar = new Premio();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM premio WHERE id_premio = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// tomar arcade
					buscar.setId_premio(id);
					buscar.setId_central(datos.getInt("id_central"));
					buscar.setNombre_premio(datos.getString("nombre_premio"));
					buscar.setPuntuacion_req(datos.getInt("puntuacion_req"));
					buscar.setTicket_req(datos.getInt("ticket_req"));
					buscar.setLimite(datos.getInt("limite"));					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Puntuaciones
	public Puntuaciones buscarPuntuacionesPorId(int id) {
		Puntuaciones buscar = new Puntuaciones();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM puntuaciones WHERE id_puntuacion = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// tomar arcade
					buscar.setId_puntuacion(id);
					buscar.setId_central(datos.getInt("id_central"));
					buscar.setId_jugador(datos.getInt("id_jugador"));
					buscar.setNombre(datos.getString("nombre"));
					buscar.setMonedas_usadas(datos.getInt("monedas_usadas"));
					buscar.setTiempo_jugado(datos.getTime("tiempo_jugado"));					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Ticket
	public Ticket buscarTicketPorId(int id) {
		Ticket buscar = new Ticket();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM ticket WHERE id_ticket = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// tomar arcade
					buscar.setId_ticket(id);
					buscar.setId_arcade(datos.getInt("id_arcade"));
					buscar.setId_jugador(datos.getInt("id_jugador"));
					buscar.setNombre(datos.getString("nombre"));				
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	// Usuario
	public Usuario buscarUsuarioPorId(int id) {
		Usuario buscar = new Usuario();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM jugador WHERE id_jugador = " + id;
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				buscar  = new Usuario();
				while (datos.next()) {
					// tomar arcade
					buscar.setId_persona(datos.getInt("id_persona"));
					buscar.setNombre(datos.getString("nombre"));
					buscar.setApellido(datos.getString("apellido"));
					buscar.setSexo(datos.getString("sexo").toCharArray()[0]);
					buscar.setFecha_nacimiento(datos.getDate("fecha_nacimiento"));
					buscar.setCorreo(datos.getString("correo"));
					buscar.setTelefono(datos.getString("telefono"));
					buscar.setId_direccion(datos.getInt("id_direccion"));
					buscar.setId_jugador(id);
					buscar.setNombre_usuario(datos.getString("nombre_usuario"));
					buscar.setClave(datos.getString("clave"));
					buscar.setMonedas(datos.getInt("monedas"));
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return buscar;
	}
	
	
	//Busqueda por nombre. (lista)
	public ArrayList<Arcade> buscarArcade (String nombre) {
		ArrayList<Arcade> resultado = new ArrayList<Arcade>();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM arcade WHERE nombre LIKE '%"+nombre+"%'";
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// crear objeto.
					Arcade nuevo = new Arcade();
					// tomar arcade
					nuevo.setId_arcade(datos.getInt("id_arcade"));
					nuevo.setId_central(datos.getInt("id_central"));
					nuevo.setNombre(datos.getString("nombre"));
					nuevo.setTicket_imp(datos.getInt("ticket_imp"));
					nuevo.setReq_monedas(datos.getInt("req_monedas"));
					// Agregar a la lista
					resultado.add(nuevo);					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return resultado;
	}
	
	public ArrayList<Categoria> buscarCategoria (String nombre) {
		ArrayList<Categoria> resultado = new ArrayList<Categoria>();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM categoria WHERE nombre LIKE '%"+nombre+"%'";
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// crear objeto.
					Categoria nuevo = new Categoria();
					// tomar arcade
					nuevo.setNombre(datos.getString("nombre"));
					nuevo.setDescripcion(datos.getString("descripcion"));
					nuevo.setClasificacion(datos.getString("clasificacion"));
					nuevo.setId_categoria(datos.getInt("id_categoria"));
					// Agregar a la lista
					resultado.add(nuevo);					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return resultado;
	}
	
	public ArrayList<Premio> buscarPremio (String nombre) {
		ArrayList<Premio> resultado = new ArrayList<Premio>();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM premio WHERE nombre_premio LIKE '%"+nombre+"%'";
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// crear objeto.
					Premio nuevo = new Premio();
					// tomar arcade
					nuevo.setId_premio(datos.getInt("id_premio"));
					nuevo.setId_central(datos.getInt("id_central"));
					nuevo.setNombre_premio(datos.getString("nombre_premio"));
					nuevo.setPuntuacion_req(datos.getInt("puntuacion_req"));
					nuevo.setTicket_req(datos.getInt("ticket_req"));
					nuevo.setLimite(datos.getInt("limite"));					
					// Agregar a la lista
					resultado.add(nuevo);					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return resultado;
	}
	
	public ArrayList<Puntuaciones> buscarPuntuaciones (String nombre) {
		ArrayList<Puntuaciones> resultado = new ArrayList<Puntuaciones>();
		Boolean process = true;
				
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "SELECT * FROM puntuaciones WHERE nombre LIKE '%"+nombre+"%'";
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				ResultSet datos = preparedStatement.executeQuery();
				
				// Tomar los datos en un nuevo objeto
				while (datos.next()) {
					// crear objeto.
					Puntuaciones nuevo = new Puntuaciones();
					// tomar arcade
					nuevo.setId_puntuacion(datos.getInt("id_puntuacion"));
					nuevo.setId_central(datos.getInt("id_central"));
					nuevo.setId_jugador(datos.getInt("id_jugador"));
					nuevo.setNombre(datos.getString("nombre"));
					nuevo.setMonedas_usadas(datos.getInt("monedas_usadas"));
					nuevo.setTiempo_jugado(datos.getTime("tiempo_jugado"));
					// Agregar a la lista
					resultado.add(nuevo);					
				}
			} catch (SQLException e) {
				process = false;
				e.printStackTrace();
			}
		}
		if (!process) {
			return null;
		}
		JDBCPostgreSQLConnect.desconectar();
		return resultado;
	}

	// Modificar
	public Boolean modificarObjeto(String tipo, Object objetoMod, int id) {
		Boolean result = true;
		int fin = 0;

		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String updateQuery = "";
				
				//ResultSet datos = preparedStatement.executeQuery();
				
				String table = "", idTable = "";
				switch (tipo.toLowerCase()) {
				case "arcade":
					if (objetoMod instanceof Arcade) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
									   "arcade",
									   "id_central = " + ((Arcade)objetoMod).getId_central() +
									   ", id_categoria = " + ((Arcade)objetoMod).getId_categoria() + 
									   ", nombre = " + ((Arcade)objetoMod).getNombre() +
									   ", ticket_imp = " + ((Arcade)objetoMod).getTicket_imp() +
									   ", req_monedas = " + ((Arcade)objetoMod).getReq_monedas(),
									   "id_arcade = " + ((Arcade)objetoMod).getId_arcade());
					} else {
						result = false;
					}
					break;
				case "central":
					if (objetoMod instanceof Central) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "central",
								   "id_direccion = " + ((Central)objetoMod).getId_direccion() + 
								   ", nombre = " + ((Central)objetoMod).getNombre() + 
								   ", numero = " + ((Central)objetoMod).getNumero() +
								   ", telefono = " + ((Central)objetoMod).getTelefono(),
								   "id_central = " + ((Central)objetoMod).getId_central());
					} else {
						result = false;
					}
					break;
				case "direccion":
					if (objetoMod instanceof Direccion) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "direccion",
								   "pais = " + ((Direccion)objetoMod).getPais() +
								   ", region = " + ((Direccion)objetoMod).getRegion() +
								   ", codigo_postal = " + ((Direccion)objetoMod).getCodigo_postal(),
								   "id_direccion = " + ((Direccion)objetoMod).getId_direccion());
					} else {
						result = false;
					}
					break;
				case "categoria":
					if (objetoMod instanceof Categoria) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "categoria",
								   "nombre = " + ((Categoria)objetoMod).getNombre() +
								   ", descripcion = " + ((Categoria)objetoMod).getDescripcion() + 
								   ", clasificacion = " + ((Categoria)objetoMod).getClasificacion(),
								   "id_categoria = " + ((Categoria)objetoMod).getId_categoria());
					} else {
						result = false;
					}
					break;
				case "persona":
					if (objetoMod instanceof Persona) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "persona",
								   "nombre = " + ((Persona)objetoMod).getNombre() +
								   ", apellido = " + ((Persona)objetoMod).getApellido() + 
								   ", sexo = " + Character.toString(((Persona)objetoMod).getSexo()) + 
								   ", correo = " + ((Persona)objetoMod).getCorreo() + 
								   ", telefono = " + ((Persona)objetoMod).getTelefono() +
								   ", id_direccion = " + ((Persona)objetoMod).getId_direccion(),
								   "id_persona = " + ((Persona)objetoMod).getId_persona());
					} else {
						result = false;
					}
					break;
				case "premio":
					if (objetoMod instanceof Premio) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "premio",
								   "id_central =" + ((Premio)objetoMod).getId_central() +
								   ", nombre_premio = " + ((Premio)objetoMod).getNombre_premio() + 
								   ", puntuacion_req = " + ((Premio)objetoMod).getPuntuacion_req() + 
								   ", ticket_req = " + ((Premio)objetoMod).getTicket_req() + 
								   ", limite = " + ((Premio)objetoMod).getLimite(),
								   "id_premio = " + ((Premio)objetoMod).getId_premio());
					} else {
						result = false;
					}
					break;
				case "puntuaciones":
					if (objetoMod instanceof Puntuaciones) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "puntuaciones",
								   "id_central = " + ((Puntuaciones)objetoMod).getId_central() + 
								   ", id_jugador = " + ((Puntuaciones)objetoMod).getId_jugador() + 
								   ", nombre = " + ((Puntuaciones)objetoMod).getNombre() + 
								   ", monedas_usadas = " + ((Puntuaciones)objetoMod).getMonedas_usadas(),
								   "id_puntuacion = " + ((Puntuaciones)objetoMod).getId_puntuacion());
					} else {
						result = false;
					}
					break;
				case "ticket":
					if (objetoMod instanceof Ticket) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "ticket",
								   "id_arcade = " + ((Ticket)objetoMod).getId_arcade() + 
								   ", id_jugador =" + ((Ticket)objetoMod).getId_jugador() + 
								   ", nombre = " + ((Ticket)objetoMod).getNombre(),
								   "id_ticket = " + ((Ticket)objetoMod).getId_ticket());
					} else {
						result = false;
					}
					break;
				case "usuario":
					if (objetoMod instanceof Usuario) {
						updateQuery = String.format("UPDATE %s SET %s WHERE %s", 
								   "usuario",
								   "nombre = " + ((Usuario)objetoMod).getNombre() +
								   ", apellido = " + ((Usuario)objetoMod).getApellido() + 
								   ", sexo = " + Character.toString(((Usuario)objetoMod).getSexo()) + 
								   ", correo = " + ((Usuario)objetoMod).getCorreo() + 
								   ", telefono = " + ((Usuario)objetoMod).getTelefono() +
								   ", id_direccion = " + ((Usuario)objetoMod).getId_direccion() + 
								   ", id_central = " + ((Usuario)objetoMod).getId_central() +
								   ", nombre_usuario = " + ((Usuario)objetoMod).getNombre_usuario() + 
								   ", clave = " + ((Usuario)objetoMod).getClave() + 
								   ", monedas = " + ((Usuario)objetoMod).getMonedas(),
								   "id_usuario = " + ((Usuario)objetoMod).getId_jugador());
					} else {
						result = false;
					}
					break;
				default:
					result = false;
					break;
				}
				
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(updateQuery);
				// Ejecutar update
				if (result) {
					fin = preparedStatement.executeUpdate();
				}
				
			} catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
		}
		JDBCPostgreSQLConnect.desconectar();
		return result;
	}
	
	// Eliminar
	public Boolean eliminarObjeto(String tipo, int id) {
		Boolean result = true;
		int fin = 0;

		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return null; // Fall�
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				String sqlQuery = "DELETE FROM ? WHERE ? = ?";
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement(sqlQuery);
				//ResultSet datos = preparedStatement.executeQuery();
				
				String table = "", idTable = "";
				switch (tipo.toLowerCase()) {
				case "arcade":
					table = "arcade";
					idTable = "id_arcade";
					break;
				case "central":
					table = "central";
					idTable = "id_central";
					break;
				case "direccion":
					table = "direccion";
					idTable = "id_direccion";
					break;
				case "categoria":
					table = "categoria";
					idTable = "id_categoria";
					break;
				case "persona":
					table = "persona";
					idTable = "id_persona";
					break;
				case "premio":
					table = "premio";
					idTable = "id_premio";
					break;
				case "puntuaciones":
					table = "puntuaciones";
					idTable = "id_puntuacion";
					break;
				case "ticket":
					table = "ticket";
					idTable = "id_ticket";
					break;
				case "usuario":
					table = "usuario";
					idTable = "id_usuario";
					break;
				default:
					result = false;
					break;
				}
				
				// Ejecutar delete
				if (result) {
					preparedStatement.setString(1, table);
					preparedStatement.setString(2, idTable);
					preparedStatement.setInt(3, id);
					
					fin = preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				result = false;
				e.printStackTrace();
			}
		}
		JDBCPostgreSQLConnect.desconectar();
		return result;
	}
	
	// Select count para cada uno.
	public long totalArcade() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM arcade");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalCategoria() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM categoria");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalTicket() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM ticket");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalCentral() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM central");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalPremio() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM premio");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalUsuario() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM jugador");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalPersona() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM persona");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalDireccion() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM direccion");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public long totalPuntuaciones() {
		long result = -2; // -2 no llego a obtenerse nada.
		if (JDBCPostgreSQLConnect.getConnection() == null) {
			return -1; // Fallo...
		}
		
		if (JDBCPostgreSQLConnect.sqlEstado()) {
			PreparedStatement preparedStatement;
			try {
				preparedStatement = JDBCPostgreSQLConnect.getConnection()
						.prepareStatement("SELECT COUNT(*) AS total FROM puntuaciones");
				ResultSet datos = preparedStatement.executeQuery();
				while (datos.next()) {
					result = datos.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
