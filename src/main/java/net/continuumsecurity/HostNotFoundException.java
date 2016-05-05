package net.continuumsecurity;

/**
 * Created by stephen on 23/02/2014.
 */
public class HostNotFoundException extends RuntimeException {
	private static final long	serialVersionUID	= -3210575534178430235L;

	public HostNotFoundException(String s) {
		super(s);
	}
}
