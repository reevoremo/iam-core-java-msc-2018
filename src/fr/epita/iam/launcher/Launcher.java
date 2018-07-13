/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.EntityCreationException;
import fr.epita.iam.exceptions.EntitySearchException;
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
	 * This methods allows to ...
	 * </p>
	 *
	 * <h3>Usage</h3>
	 * <p>
	 * It should be used as follows :
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
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {
		// initialize resources
		IdentityDAO dao = new IdentityJDBCDAO();
		
		final ConsoleOperations console = new ConsoleOperations();
		// Welcome
		// Authentication
		String username = console.readUsernameFromConsole();
		String password = console.readPasswordFromConsole();
		
		boolean loggedIn = false;
		if (username.equals("root") && password.equals("pass")) {
			loggedIn = true;
		}
		else {
			System.out.println("Invalid login credentials");
		}
		// Menu
		while (loggedIn) {
			String selection = console.menuSelectionFromConsole();
			switch (selection) {
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				loggedIn = false;
				break;
			default:
				System.out.println("Invalid Selection");
			}
		}
		// Create
		final Identity identity = console.readIdentityFromConsole();
		try {
			dao.create(identity);
		} catch (final EntityCreationException ece) {
			System.out.println("Failed");
			System.out.println(ece.getUserMessage());
		}
		// Search?
		final Identity criteria = console.readCriteriaFromConsole();
		List<Identity> resultList;//iloveyou
		try {
			resultList = dao.search(criteria);
			console.displayIdentitiesInConsole(resultList);
		} catch (final EntitySearchException e) {
			System.out.println(e.getMessage());
		}


		// Update

		// Delete
		console.releaseResources();

	}

}
