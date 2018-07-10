/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.launcher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.identity.IdentityDAO;

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
		IdentityDAO dao = null;
		try {
			dao = IdentityDAOFactory.getDAO();
		} catch (final Exception e) {
			// TODO log

			return;
		}
		
		final ConsoleOperations console = new ConsoleOperations();
		// Welcome
		// Authentication

		// Menu

		// Create
		final Identity identity = console.readIdentityFromConsole();
		try {
			dao.create(identity);
		} catch (final EntityCreationException ece) {
			System.out.println(ece.getMessage());
		}
		// Search?
		final Identity criteria = console.readCriteriaFromConsole();
		List<Identity> resultList;
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
