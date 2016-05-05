package net.continuumsecurity;

import javax.security.auth.login.LoginException;

/**
 * Created by stephen on 07/02/15.
 */
public interface SessionClient {
	public void login(String username, String password) throws LoginException;

	public void logout();

	public void setApiKeys(String accessKey, String secretKey);

}