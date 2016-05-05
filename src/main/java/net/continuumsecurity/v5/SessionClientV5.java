package net.continuumsecurity.v5;

import java.util.logging.Logger;

import javax.security.auth.login.LoginException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.NessusException;
import net.continuumsecurity.SessionClient;
import net.continuumsecurity.v5.model.jaxrs.NessusReply;

/**
 * Created by stephen on 23/02/2014.
 */
public class SessionClientV5 implements SessionClient {
	private Client			client;
	protected WebTarget		target;
	private String			token;
	protected String		nessusUrl;
	private static Logger	log	= Logger.getLogger(ScanClientV5.class.toString());

	public SessionClientV5(String nessusUrl, boolean acceptAllHostNames) {
		this.nessusUrl = nessusUrl;
		client = ClientFactory.createV5Client(acceptAllHostNames);
		target = client.target(nessusUrl);
	}

	public void login(String username, String password) throws LoginException {
		WebTarget loginTarget = target.path("/login");
		Form form = new Form();
		form.param("login", username);
		form.param("password", password);
		form.param("seq", generateSeqNum());
		NessusReply reply = loginTarget.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), NessusReply.class);
		if(!"OK".equalsIgnoreCase(reply.getStatus()))
			throw new LoginException("Error logging in");
		token = reply.getContents().getToken();
		log.info("Login OK.  Token: " + token);
	}

	public void logout() {
		WebTarget logoutTarget = target.path("/logout");
		Form form = prepopulateForm();
		sendRequestAndCheckError(logoutTarget, form);
	}

	protected String generateSeqNum() {
		return "29823987434";
	}

	protected NessusReply sendRequestAndCheckError(WebTarget target, Form form) {
		NessusReply reply = target.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), NessusReply.class);
		if(!"OK".equalsIgnoreCase(reply.getStatus()))
			throw new NessusException("Error: Got status: " + reply.getStatus() + " for request to: " + target.getUri());
		return reply;
	}

	protected String getStringResponse(WebTarget target, Form form) {
		return target.request(MediaType.APPLICATION_XML_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
	}

	protected Form prepopulateForm() {
		Form form = new Form();
		form.param("seq", generateSeqNum());
		form.param("token", token);
		return form;
	}

	@Override
	public void setApiKeys(String accessKey, String secretKey) {
		throw new NessusException("Authentication using APIKeys is currently not supported for Nessusv5");
	}
}