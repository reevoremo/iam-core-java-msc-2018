package fr.epita.iam.exceptions;

public class EntitySearchException extends Exception{
	
	private Object entity;
	/**
	 *
	 */
	public EntitySearchException(Throwable cause) {
		System.out.println(cause.getMessage());
		initCause(cause);
	}

	public String getUserMessage() {
		return "the search operation failed";
	}
}
