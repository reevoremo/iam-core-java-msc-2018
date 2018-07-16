package fr.epita.iam.exceptions;

public class EntityUpdateException extends Exception{
	private Object entity;

	/**
	 *
	 */
	public EntityUpdateException(Object entity, Throwable cause) {
		this.entity = entity;
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity update has failed :" + entity.toString();
	}
}
