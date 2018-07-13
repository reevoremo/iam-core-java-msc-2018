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
 *   <pre><code>${type_name} instance = new ${type_name}();</code></pre>
 * </p>
 *
 * @since $${version}
 * @see See also $${link}
 * @author ${user}
 *
 * ${tags}
 */
public class EntityCreationException extends Exception {


	private Object entity;

	/**
	 *
	 */
	public EntityCreationException(Object entity, Throwable cause) {
		this.entity = entity;
		System.out.println(cause.getMessage());
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity creation has failed :" + entity.toString();
	}

}
