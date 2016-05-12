
package io.hakbot.providers.temp.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Host" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaxLinks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="AttackModules" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AuthUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthSingleSignonPage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LogFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "username",
    "password",
    "token",
    "host",
    "maxLinks",
    "attackModules",
    "authUsername",
    "authPassword",
    "authSingleSignonPage",
    "logFileName"
})
@XmlRootElement(name = "RunScan")
public class RunScan {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Host")
    protected String host;
    @XmlElement(name = "MaxLinks")
    protected int maxLinks;
    @XmlElement(name = "AttackModules")
    protected boolean attackModules;
    @XmlElement(name = "AuthUsername")
    protected String authUsername;
    @XmlElement(name = "AuthPassword")
    protected String authPassword;
    @XmlElement(name = "AuthSingleSignonPage")
    protected String authSingleSignonPage;
    @XmlElement(name = "LogFileName")
    protected String logFileName;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the maxLinks property.
     * 
     */
    public int getMaxLinks() {
        return maxLinks;
    }

    /**
     * Sets the value of the maxLinks property.
     * 
     */
    public void setMaxLinks(int value) {
        this.maxLinks = value;
    }

    /**
     * Gets the value of the attackModules property.
     * 
     */
    public boolean isAttackModules() {
        return attackModules;
    }

    /**
     * Sets the value of the attackModules property.
     * 
     */
    public void setAttackModules(boolean value) {
        this.attackModules = value;
    }

    /**
     * Gets the value of the authUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthUsername() {
        return authUsername;
    }

    /**
     * Sets the value of the authUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthUsername(String value) {
        this.authUsername = value;
    }

    /**
     * Gets the value of the authPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthPassword() {
        return authPassword;
    }

    /**
     * Sets the value of the authPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthPassword(String value) {
        this.authPassword = value;
    }

    /**
     * Gets the value of the authSingleSignonPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthSingleSignonPage() {
        return authSingleSignonPage;
    }

    /**
     * Sets the value of the authSingleSignonPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthSingleSignonPage(String value) {
        this.authSingleSignonPage = value;
    }

    /**
     * Gets the value of the logFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogFileName() {
        return logFileName;
    }

    /**
     * Sets the value of the logFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogFileName(String value) {
        this.logFileName = value;
    }

}
