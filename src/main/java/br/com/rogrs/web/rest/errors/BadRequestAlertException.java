package br.com.rogrs.web.rest.errors;

public class BadRequestAlertException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public final String entityName;

	public final String errorKey;

	public BadRequestAlertException(String defaultMessage, String entityName, String errorKey) {
		super(defaultMessage);
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

}
