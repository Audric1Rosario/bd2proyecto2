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
	private final String url = "jdbc:postgresql://localhost/proyecto";
	private final String user = "postgres";
	private final String password = "123";
	
	private void conexion() {
		try(Connection connection = DriverManager.getConnection(url, user, password);) {
			if(connection != null) {
				System.out.println("Conexion a PostgreSQL con exito!");
			} else {
				System.out.println("Fallo en la conexion");
			}
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
			if(resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JDBCPostgreSQLConnect sqlconexion = new JDBCPostgreSQLConnect();
		sqlconexion.conexion();
	}
}

