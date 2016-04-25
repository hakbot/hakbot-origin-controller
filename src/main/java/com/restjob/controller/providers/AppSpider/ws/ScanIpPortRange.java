
package com.restjob.controller.providers.AppSpider.ws;

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
 *         &lt;element name="szIpRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="szPortRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "szIpRange",
    "szPortRange"
})
@XmlRootElement(name = "ScanIpPortRange")
public class ScanIpPortRange {

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Password")
    protected String password;
    protected String szIpRange;
    protected String szPortRange;

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
     * Gets the value of the szIpRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzIpRange() {
        return szIpRange;
    }

    /**
     * Sets the value of the szIpRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzIpRange(String value) {
        this.szIpRange = value;
    }

    /**
     * Gets the value of the szPortRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSzPortRange() {
        return szPortRange;
    }

    /**
     * Sets the value of the szPortRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSzPortRange(String value) {
        this.szPortRange = value;
    }

}
