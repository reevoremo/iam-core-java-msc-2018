/**
 * Thomas BROUSSARD
 * Behan Remoshan
 */
package fr.epita.iam.exceptions;

/**
 * <h3>Description</h3>
 * <p>This class allows to ...</p>
 *
 * <h3>Usage</h3>
 * <p>This class should be used as follows:
 *   <pre><code>${EntityReadException} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public class EntityReadException extends Exception {


	private Object entity;

	/**
	 *
	 */
	public EntityReadException(Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following ID reading has failed";
	}

}
