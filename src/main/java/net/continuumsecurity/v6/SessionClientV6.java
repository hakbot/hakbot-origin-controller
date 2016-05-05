package net.continuumsecurity.v6;

import java.util.logging.Logger;

import javax.security.auth.login.LoginException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.SessionClient;
import net.continuumsecurity.v6.model.Login;

/**
 * Created by stephen on 07/02/15.
 */
public class SessionClientV6 implements SessionClient {
	private Client				client;
	protected WebTarget			target;
	private String				token;
	private static final String	COOKIE_HEADER	= "X-Cookie";
	private static final String	APIKEY_HEADER	= "X-ApiKeys";
	private static Logger		log				= Logger.getLogger(ScanClientV6.class.toString());
	protected String			nessusUrl;
	private String authHeader;
	private String authParam;

	public SessionClientV6(String nessusUrl, boolean acceptAllHostNames) {
		this.nessusUrl = nessusUrl;
		client = ClientFactory.createV6Client(acceptAllHostNames);
		target = client.target(nessusUrl);
	}

	public void login(String username, String password) throws LoginException {
		WebTarget loginTarget = target.path("/session");
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		Login reply = loginTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), Login.class);
		token = reply.getToken();
		if(token == null || token.length() == 0)
			throw new LoginException("Error logging in");
		authHeader = COOKIE_HEADER;
		authParam = "token=" + token;
		log.info("Login OK.  Token: " + token);
	}

	public void setApiKeys(final String accessKey, final String secretKey) {
		authHeader = APIKEY_HEADER;
		authParam = "accessKey=" + accessKey + "; secretKey=" + secretKey;
	}

	public void logout() {
		WebTarget logoutTarget = target.path("/session");
		Response response = logoutTarget.request(MediaType.APPLICATION_JSON_TYPE).header(authHeader, authParam).delete(Response.class);
		if(response.getStatus() != 200)
			throw new RuntimeException("Error logging out. Received status code: " + response.getStatus() + " " + response.getStatusInfo().getReasonPhrase());
		log.info("Logout: " + response.getStatusInfo().getReasonPhrase());
	}

	protected <T> T postRequest(WebTarget target, Object object, Class<T> returnType) {
		return target.request(MediaType.APPLICATION_JSON_TYPE).header(authHeader, authParam).post(Entity.entity(object, MediaType.APPLICATION_JSON_TYPE), returnType);
	}

	protected <T> T getRequest(WebTarget target, Class<T> returnType) {
		return getRequest(target, returnType, MediaType.APPLICATION_JSON_TYPE);
	}

	protected <T> T getRequest(WebTarget target, Class<T> returnType, MediaType mediaType) {
		return target.request(mediaType).header(authHeader, authParam).get(returnType);
	}
}
