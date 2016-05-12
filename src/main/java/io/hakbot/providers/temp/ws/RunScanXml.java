
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
 *         &lt;element name="ScanConfig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoginMacro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Macros" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
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
    "scanConfig",
    "loginMacro",
    "macros"
})
@XmlRootElement(name = "RunScanXml")
public class RunScanXml {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "ScanConfig")
    protected String scanConfig;
    @XmlElement(name = "LoginMacro")
    protected String loginMacro;
    @XmlElement(name = "Macros")
    protected ArrayOfString macros;

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
     * Gets the value of the scanConfig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanConfig() {
        return scanConfig;
    }

    /**
     * Sets the value of the scanConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanConfig(String value) {
        this.scanConfig = value;
    }

    /**
     * Gets the value of the loginMacro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginMacro() {
        return loginMacro;
    }

    /**
     * Sets the value of the loginMacro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginMacro(String value) {
        this.loginMacro = value;
    }

    /**
     * Gets the value of the macros property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getMacros() {
        return macros;
    }

    /**
     * Sets the value of the macros property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setMacros(ArrayOfString value) {
        this.macros = value;
    }

}
