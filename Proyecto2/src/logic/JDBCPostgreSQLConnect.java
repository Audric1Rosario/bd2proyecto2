package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPostgreSQLConnect {
	// jdbc url
	// jdbc username
	// jdbc password
	private static String url = "jdbc:postgresql://localhost/proyecto";	    // Indicar donde se conecta
	private static String user = "postproject";								// Usuario
	private static String password = "!j'P*+tQm8dYD6_,";					// Contraseña fuerte
	private static Boolean isConnected = false;
	
	// Conexión
	private static Connection conexion = null;
	
	/*public JDBCPostgreSQLConnect() {
		super();
	}*/
	
	// Conectar a la bd en postgresql
	private static Boolean conectar() {		
		Boolean result = true;
		try {
			conexion = DriverManager.getConnection(url, user, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	// Para poder conectarse
	public static Connection getConnection() {
		if (conexion != null) {
			return conexion;
		}
		
		if (conectar()) {
			isConnected = true;
			return conexion;
		}
		
		return null;
	}
	
	// Esto es para poder comprobar el estado
	public static Boolean sqlEstado() {
		return isConnected;
	}
	
	public static Boolean desconectar() {
		Boolean result = true;
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		if (result) {
			isConnected = false;
			conexion = null;
		}
		
		return result;
	}
}

