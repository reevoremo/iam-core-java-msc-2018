package fr.epita.iam.exceptions;

public class EntityUpdateException extends Exception{
	private Object entity;

	/**
	 *
	 */
	public EntityUpdateException(Object entity, Throwable cause) {
		this.entity = entity;
		System.out.println(cause.getMessage());
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity update has failed :" + entity.toString();
	}
}
