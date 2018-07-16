package fr.epita.iam.services.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityReadException;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;
import fr.epita.iam.services.identity.ConsoleLogger;

public class UserJDBC {
	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = ConfigurationService.getProperty(ConfKey.DB_URL);
		Connection connection = null;

		// When I connect
		connection = DriverManager.getConnection(url, ConfigurationService.getProperty(ConfKey.DB_USER),
				ConfigurationService.getProperty(ConfKey.DB_PASSWORD));
		return connection;
	}
	
	public void create(String username, String password) throws EntityCreationException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection.prepareStatement("INSERT INTO USERS (USER_NAME, USER_PASSWORD) VALUES (?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			if(pstmt.executeUpdate() > 0) {
				ConsoleLogger.log("User created");
			} else {
				ConsoleLogger.log("User creation Failed");
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					ConsoleLogger.log(e1.getMessage());
				}
			}
			throw new EntityCreationException(null, e);
		}
	}
	
	public boolean checkLogin(String username, String password) throws EntityReadException {
		Connection connection = null;
		boolean valid = false;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection.prepareStatement("SELECT USER_NAME, USER_PASSWORD FROM USERS WHERE USER_NAME = ?");
			pstmt.setString(1, username);
			final ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				final String passwordStored = rs.getString("USER_PASSWORD");
				if (passwordStored.equals(password)) {
					valid = true;
				}
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					ConsoleLogger.log(e1.getMessage());
				}
			}
			throw new EntityReadException(e);
		}
		return valid;
	}
}
