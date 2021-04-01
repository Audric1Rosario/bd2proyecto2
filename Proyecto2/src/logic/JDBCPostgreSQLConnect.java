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
	private static String url = "jdbc:postgresql://localhost/proyecto";
	private static String user = "postgres";
	private static String password = "123";
	
	public static Connection conectar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, user, password);
		
			if(conexion != null) {
				System.out.println("Conexion a PostgreSQL con exito!");
			} else {
				System.out.println("Fallo en la conexion");
			}
			
			/*
			Statement statement = conexion.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
			if(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion;
	}
}

