/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.ui;

import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.identity.ConsoleLogger;

/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public class ConsoleOperations {

	private final Scanner scanner;

	public ConsoleOperations() {
		scanner = new Scanner(System.in);
	}

	public Identity readIdentityFromConsole() {
		final Identity identity = new Identity();
		ConsoleLogger.log("please input the display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		ConsoleLogger.log("please input the email");
		line = scanner.nextLine();
		identity.setEmail(line);
		ConsoleLogger.log("please input uid");
		line = scanner.nextLine();
		identity.setUid(line);
		return identity;
	}

	public Identity readCriteriaFromConsole() {
		ConsoleLogger.log("Enter criteria");
		final Identity identity = new Identity();
		ConsoleLogger.log("please input the criterion for display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		ConsoleLogger.log("please input the criterion for email");
		line = scanner.nextLine();
		identity.setEmail(line);

		return identity;
	}

	public void displayIdentitiesInConsole(List<Identity> identities) {
		int i = 1;
		for (final Identity identity : identities) {
			System.out.print(i++);
			ConsoleLogger.log(" - " + identity);
		}
	}
	
	public String readUsernameFromConsole() {
		ConsoleLogger.log("Enter username");
		return scanner.nextLine();
	}
	
	public String readPasswordFromConsole() {
		ConsoleLogger.log("Enter password");
		return scanner.nextLine();
	}
	public String readIDFromConsole() {
		ConsoleLogger.log("Enter the ID");
		return scanner.nextLine();
	}
	public String menuSelectionFromConsole() {
		ConsoleLogger.log("Choose the operation");
		ConsoleLogger.log("1 - Create Identity");
		ConsoleLogger.log("2 - Update Identity");
		ConsoleLogger.log("3 - Search Identity");
		ConsoleLogger.log("4 - Search Identity by ID");
		ConsoleLogger.log("5 - Display All");
		ConsoleLogger.log("6 - Delete Identity");
		ConsoleLogger.log("7 - Exit");
		return scanner.nextLine();
	}
	
	public void releaseResources() {
		scanner.close();
	}
}