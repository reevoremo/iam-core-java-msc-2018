package fr.epita.iam.exceptions;

public class EntityDeletionException extends Exception{
	private Object entity;

	/**
	 *
	 */
	public EntityDeletionException(Object entity, Throwable cause) {
		this.entity = entity;
		System.out.println(cause.getMessage());
		initCause(cause);
	}

	public String getUserMessage() {
		return "the following entity deletion has failed :" + entity.toString();
	}
}
