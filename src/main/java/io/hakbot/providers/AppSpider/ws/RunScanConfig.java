
package io.hakbot.providers.AppSpider.ws;

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
 *         &lt;element name="SpiderConfig" type="{http://ntobjectives.com/webservices/}SPIDERCONFIG" minOccurs="0"/>
 *         &lt;element name="CrawlConfig" type="{http://ntobjectives.com/webservices/}CRAWLCONFIG" minOccurs="0"/>
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
    "spiderConfig",
    "crawlConfig"
})
@XmlRootElement(name = "RunScanConfig")
public class RunScanConfig {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "SpiderConfig")
    protected SPIDERCONFIG spiderConfig;
    @XmlElement(name = "CrawlConfig")
    protected CRAWLCONFIG crawlConfig;

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
     * Gets the value of the spiderConfig property.
     * 
     * @return
     *     possible object is
     *     {@link SPIDERCONFIG }
     *     
     */
    public SPIDERCONFIG getSpiderConfig() {
        return spiderConfig;
    }

    /**
     * Sets the value of the spiderConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link SPIDERCONFIG }
     *     
     */
    public void setSpiderConfig(SPIDERCONFIG value) {
        this.spiderConfig = value;
    }

    /**
     * Gets the value of the crawlConfig property.
     * 
     * @return
     *     possible object is
     *     {@link CRAWLCONFIG }
     *     
     */
    public CRAWLCONFIG getCrawlConfig() {
        return crawlConfig;
    }

    /**
     * Sets the value of the crawlConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRAWLCONFIG }
     *     
     */
    public void setCrawlConfig(CRAWLCONFIG value) {
        this.crawlConfig = value;
    }

}
