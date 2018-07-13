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
import fr.epita.iam.exceptions.EntityDeletionException;
import fr.epita.iam.exceptions.EntitySearchException;
import fr.epita.iam.exceptions.EntityUpdateException;
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
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws IOException {
		// initialize resources
		IdentityDAO dao = new IdentityJDBCDAO();
		
		final ConsoleOperations console = new ConsoleOperations();
		// Welcome
		// Authentication
		//String username = console.readUsernameFromConsole();
		//String password = console.readPasswordFromConsole();
		
		boolean loggedIn = true;
		/*if (username.equals("root") && password.equals("pass")) {
			loggedIn = true;
		}
		else {
			System.out.println("Invalid login credentials");
		}*/
		// Menu
		while (loggedIn) {
			String selection = console.menuSelectionFromConsole();
			switch (selection) {
			case "1":
				final Identity identity = console.readIdentityFromConsole();
				try {
					dao.create(identity);
				} catch (final EntityCreationException ece) {
					System.out.println("Failed");
					System.out.println(ece.getUserMessage());
				}
				break;
			case "2":
				final Identity updateIdentity = console.readIdentityFromConsole();
				try {
					dao.update(updateIdentity);
				} catch (final EntityUpdateException ece) {
					System.out.println("Failed");
					System.out.println(ece.getUserMessage());
				}
				break;
			case "3":
				final Identity criteria = console.readCriteriaFromConsole();
				List<Identity> resultList;
				try {
					resultList = dao.search(criteria);
					console.displayIdentitiesInConsole(resultList);
				} catch (final EntitySearchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "4":
				break;
			case "5":
				final Identity deleteIdentity = console.readIdentityFromConsole();
				try {
					dao.delete(deleteIdentity);
				} catch (final EntityDeletionException ece) {
					System.out.println("Failed");
					System.out.println(ece.getUserMessage());
				}
				break;
			case "6":
				loggedIn = false;
				break;
			default:
				System.out.println("Invalid Selection");
			}
		}
		console.releaseResources();

	}

}
