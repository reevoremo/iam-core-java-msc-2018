/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntityReadException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
import fr.epita.iam.services.Session;
import fr.epita.iam.services.identity.ConsoleLogger;
import fr.epita.iam.services.identity.IdentityDAO;
import fr.epita.iam.services.identity.IdentityJDBCDAO;
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
public class Launcher {

	/**
	 *
	 * <h3>Description</h3>
	 * <p>
	 * This methods allows to call all other available methods inside the application
	 * </p>
	 *
	 * <h3>Usage</h3>
	 * <p>
	 * It should be used as follows : Console will get input and produce outputs
	 *
	 * <pre>
	 * <code> ${enclosing_type} sample;
	 *
	 * //...
	 *
	 * sample.${enclosing_method}();
	 *</code>
	 * </pre>
	 * </p>
	 *
	 * @since $${version}
	 * @see Voir aussi $${link}
	 * @author ${user}
	 *
	 *         ${tags}
	 * @throws IOException
	 * @throws EntityReadException 
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		// initialize resources
		IdentityDAO dao = new IdentityJDBCDAO();
		
		final ConsoleOperations console = new ConsoleOperations();
		
		// Welcome
		// Authentication
		String username = console.readUsernameFromConsole();
		String password = console.readPasswordFromConsole();
		
		Session session = new Session();
		
		boolean loggedIn = false;
		if (session.login(username, password)) {
			loggedIn = true;
		}
		else {
			ConsoleLogger.log("Invalid login credentials");
		}
		// Menu
		while (loggedIn) {
			String selection = console.menuSelectionFromConsole();
			switch (selection) {
			case "1":
				final Identity identity = console.readIdentityFromConsole();
				try {
					dao.create(identity);
				} catch (final EntityCreationException ece) {
					ConsoleLogger.log(ece.getUserMessage());
				}
				break;
			case "2":
				final Identity updateIdentity = console.readIdentityFromConsole();
				try {
					dao.update(updateIdentity);
				} catch (final EntityUpdateException ece) {
					ConsoleLogger.log(ece.getUserMessage());
				}
				break;
			case "3":
				final Identity criteria = console.readCriteriaFromConsole();
				List<Identity> resultList;
				try {
					resultList = dao.search(criteria);
					console.displayIdentitiesInConsole(resultList);
				} catch (final EntitySearchException e) {
					ConsoleLogger.log(e.getMessage());
				}
				break;
			case "4":
				final String id = console.readIDFromConsole();
				List<Identity> resultListID = new ArrayList<>();
				try {
					final Identity identityByID = dao.getById(id);
					resultListID.add(identityByID);
					console.displayIdentitiesInConsole(resultListID);
				} catch (final EntityReadException e) {
					ConsoleLogger.log(e.getMessage());
				}
				break;
			case "5":
				List<Identity> resultListAll = new ArrayList<>();
				try {
					resultListAll = dao.getAll();
					console.displayIdentitiesInConsole(resultListAll);
				} catch (final EntityReadException e) {
					ConsoleLogger.log(e.getMessage());
				}
				break;
			case "6":
				final Identity deleteIdentity = console.readIdentityFromConsole();
				try {
					dao.delete(deleteIdentity);
				} catch (final EntityDeletionException ece) {
					ConsoleLogger.log(ece.getUserMessage());
				}
				break;
			case "7":
				loggedIn = false;
				break;
			default:
				ConsoleLogger.log("Invalid Selection");
			}
		}
		ConsoleLogger.log("Disconnected");
		console.releaseResources();

	}

}
