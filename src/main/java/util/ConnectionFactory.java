package util;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.DAOException;

public class ConnectionFactory {

	public static Connection getConnection() throws DAOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/educafacil?useTimezone=true&serverTimezone=UTC", "novo_usuario", "novo_usuario");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}
	}
}