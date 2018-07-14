/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.services.identity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntityReadException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.services.conf.ConfKey;
import fr.epita.iam.services.conf.ConfigurationService;
import fr.epita.iam.ui.ConsoleOperations;

/**
 * <h3>Description</h3>
 * <p>
 * This class allows to ...
 * </p>
 *
 * <h3>Usage</h3>
 * <p>
 * This class should be used as follows:
 *
 * <pre>
 * <code>${type_name} instance = new ${type_name}();</code>
 * </pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 *         ${tags}
 */
public class IdentityJDBCDAO implements IdentityDAO {
	private static Connection getConnection() throws SQLException {
		// Given this context
		final String url = ConfigurationService.getProperty(ConfKey.DB_URL);
		Connection connection = null;

		// When I connect
		connection = DriverManager.getConnection(url, ConfigurationService.getProperty(ConfKey.DB_USER),

				ConfigurationService.getProperty(ConfKey.DB_PASSWORD));
		return connection;

	}

	@Override
	public void create(Identity identity) throws EntityCreationException {
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection.prepareStatement("INSERT INTO IDENTITIES (IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_UID) VALUES (?, ?, ?)");
			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			pstmt.setString(3, identity.getUid());
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Identity created");
			} else {
				System.out.println("Identity not created");
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
			throw new EntityCreationException(identity, e);
		}
	}

	@Override
	public void delete(Identity identity) throws EntityDeletionException{
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement("DELETE FROM identities WHERE identity_displayname = ? AND identity_email = ?");
			pstmt.setString(1, identity.getDisplayName());
			pstmt.setString(2, identity.getEmail());
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Record deleted Successfully");
			}
			else {
				System.out.println("Record not found");
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
			throw new EntityDeletionException(identity, e);
		}
	}

	@Override
	public void update(Identity identity) throws EntityUpdateException{
		Connection connection = null;
		System.out.println("Please enter the things to change");
		final ConsoleOperations console = new ConsoleOperations();
		Identity updateId = console.readIdentityFromConsole();
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement("UPDATE identities SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ?, IDENTITY_UID = ?  WHERE IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ?, IDENTITY_UID = ?");
			pstmt.setString(1, updateId.getDisplayName());
			pstmt.setString(2, updateId.getEmail());
			pstmt.setString(3, updateId.getUid());
			pstmt.setString(4, identity.getDisplayName());
			pstmt.setString(5, identity.getEmail());
			pstmt.setString(6, identity.getUid());
			if (pstmt.executeUpdate() > 0) {
				System.out.println("Record Updated Successfully");
			}
			else {
				System.out.println("Record not found");
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
			throw new EntityUpdateException(identity, e);
		}
	}

	@Override
	public Identity getById(Serializable id) throws EntityReadException {
		Identity identity = null;
		
		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement("select IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_UID from IDENTITIES"
							+ " where IDENTITY_ID = ?");
			pstmt.setString(1, id.toString());
			final ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				final String displayName = rs.getString("IDENTITY_DISPLAYNAME");
				final String email = rs.getString("IDENTITY_EMAIL");
				final String uid = rs.getString("IDENTITY_UID");
				identity = new Identity(displayName, uid, email);
				System.out.println("Found!");
			} else {
				System.out.println("Identity not found");
			}
			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e2) {
					System.out.println("Failed to close the connection" + e2.getMessage());
				}
			}
			throw new EntityReadException(e);
		}
		return identity;
	}

	@Override
	public List<Identity> search(Identity criteria) throws EntitySearchException {
		final List<Identity> list = new ArrayList<>();

		Connection connection = null;
		try {
			connection = getConnection();
			final PreparedStatement pstmt = connection
					.prepareStatement("select IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, IDENTITY_UID from IDENTITIES"
							+ " where IDENTITY_DISPLAYNAME like ? or IDENTITY_EMAIL like ?");
			pstmt.setString(1, "%" + criteria.getDisplayName() + "%");
			pstmt.setString(2, "%" + criteria.getEmail() + "%");
			final ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				final String displayName = rs.getString("IDENTITY_DISPLAYNAME");
				final String email = rs.getString("IDENTITY_EMAIL");
				final String uid = rs.getString("IDENTITY_UID");
				final Identity identity = new Identity(displayName, uid, email);
				list.add(identity);

			}

			pstmt.close();
			connection.close();
		} catch (final SQLException e) {
			if (connection != null) {
				try {
					connection.close();
				} catch (final SQLException e2) {
					System.out.println("Failed to close the connection" + e2.getMessage());
				}
			}
			throw new EntitySearchException(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see fr.epita.iam.services.IdentityDAO#healthCheck()
	 */
	@Override
	public boolean healthCheck() {
		try {
			final Connection connection = getConnection();
			connection.close();
			return true;
		} catch (final SQLException sqle) {
			// TODO log

		}
		return false;

	}

}
