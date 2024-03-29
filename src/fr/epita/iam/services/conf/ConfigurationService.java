/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.services.conf;

import java.io.FileInputStream;
import java.util.Properties;

import fr.epita.iam.services.identity.ConsoleLogger;

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
public class ConfigurationService {

	public static final String BACKEND_MODE = "backend.mode";
	public static final String FALLBACK_BACKEND_MODE = "fallback.backend.mode";
	public static final String DB_BACKEND = "db";
	public static final String FILE_BACKEND = "file";

	private static Properties properties;

	static {
		init();
	}

	private static void init() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream("test.properties"));
		} catch (final Exception e) {
			ConsoleLogger.log("File read error " + e.toString());
		}


	}

	public static Integer getIntProperty(ConfKey key) {
		final String valueAsString = getProperty(key);
		return Integer.valueOf(valueAsString);
	}

	public static String getProperty(ConfKey key) {
		return properties.getProperty(key.getKey());
	}

}
