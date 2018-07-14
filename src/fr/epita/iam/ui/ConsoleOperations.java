/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.ui;

import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;

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
		System.out.println("please input the display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		System.out.println("please input the email");
		line = scanner.nextLine();
		identity.setEmail(line);
		System.out.println("please input uid");
		line = scanner.nextLine();
		identity.setUid(line);
		return identity;
	}

	public Identity readCriteriaFromConsole() {
		System.out.println("Enter criteria");
		final Identity identity = new Identity();
		System.out.println("please input the criterion for display name : ");
		String line = scanner.nextLine();
		identity.setDisplayName(line);
		System.out.println("please input the criterion for email");
		line = scanner.nextLine();
		identity.setEmail(line);

		return identity;
	}

	public void displayIdentitiesInConsole(List<Identity> identities) {
		int i = 1;
		for (final Identity identity : identities) {
			System.out.print(i++);
			System.out.println(" - " + identity);
		}
	}
	
	public String readUsernameFromConsole() {
		System.out.println("Enter username");
		String line = scanner.nextLine();
		return line;
	}
	
	public String readPasswordFromConsole() {
		System.out.println("Enter password");
		String line = scanner.nextLine();
		return line;
	}
	public String readIDFromConsole() {
		System.out.println("Enter the ID");
		String line = scanner.nextLine();
		return line;
	}
	public String menuSelectionFromConsole() {
		System.out.println("Choose the operation");
		System.out.println("1 - Create Identity");
		System.out.println("2 - Update Identity");
		System.out.println("3 - Search Identity");
		System.out.println("4 - Search Identity by ID");
		System.out.println("5 - Display All");
		System.out.println("6 - Delete Identity");
		System.out.println("7 - Exit");
		String line = scanner.nextLine();
		return line;
	}
	
	public void releaseResources() {
		scanner.close();
	}
}