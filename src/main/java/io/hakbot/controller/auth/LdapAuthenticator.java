/*
 * This file is part of Hakbot Origin Controller.
 *
 * Hakbot Origin Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hakbot Origin Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Hakbot Origin Controller. If not, see http://www.gnu.org/licenses/.
 */
package io.hakbot.controller.auth;

import io.hakbot.controller.Config;
import org.apache.commons.lang3.StringUtils;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class LdapAuthenticator {

    private static final String ldapUrl = Config.getInstance().getProperty(Config.Key.LDAP_SERVER_URL);
    private static final String domainName = Config.getInstance().getProperty(Config.Key.LDAP_DOMAIN);

    public LdapContext getConnection(String username, String password) throws NamingException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new NamingException("Username or password cannot be empty or null");
        }
        Hashtable<String, String> props = new Hashtable<>();
        String principalName = username + "@" + domainName;
        props.put(Context.SECURITY_PRINCIPAL, principalName);
        props.put(Context.SECURITY_CREDENTIALS, password);
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, ldapUrl);

        try{
            return new InitialLdapContext(props, null);
        } catch(javax.naming.CommunicationException e){
            throw new NamingException("Failed to connect to directory server");
        } catch(NamingException e){
            throw new NamingException("Failed to authenticate user");
        }
    }

    public boolean validateCredentials(String username, String password) {
        LdapContext ldapContext = null;
        try {
            ldapContext = getConnection(username, password);
            return true;
        } catch (NamingException e) {
            return false;
        } finally {
            if (ldapContext != null) {
                try {
                    ldapContext.close();
                } catch (NamingException e) { }
            }
        }
    }

}
