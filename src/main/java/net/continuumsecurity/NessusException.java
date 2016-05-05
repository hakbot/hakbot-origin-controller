package net.continuumsecurity;

/**
 * Created by stephen on 23/02/2014.
 */
public class NessusException extends RuntimeException {
	private static final long	serialVersionUID	= -6385739015621999594L;

	public NessusException() {}

	public NessusException(String message) {
		super(message);
	}

	public NessusException(String message, Throwable cause) {
		super(message, cause);
	}
}