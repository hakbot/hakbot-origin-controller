package net.continuumsecurity;

/**
 * Created by stephen on 22/02/2014.
 */
public class PolicyNotFoundException extends RuntimeException {
	private static final long	serialVersionUID	= -4576683673706197594L;

	public PolicyNotFoundException(String s) {
		super(s);
	}
}
