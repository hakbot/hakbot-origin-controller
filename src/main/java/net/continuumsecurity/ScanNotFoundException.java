package net.continuumsecurity;

/**
 * Created by stephen on 22/02/2014.
 */
public class ScanNotFoundException extends RuntimeException {
	private static final long	serialVersionUID	= -4748952779638872874L;

	public ScanNotFoundException() {}

	public ScanNotFoundException(String message) {
		super(message);
	}

	public ScanNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}