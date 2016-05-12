
package io.hakbot.providers.AppSpider.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IPSCANRESULT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPSCANRESULT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Running" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IpRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PortRange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ErrorDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ListeningWebsites" type="{http://ntobjectives.com/webservices/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPSCANRESULT", propOrder = {
    "running",
    "ipRange",
    "portRange",
    "error",
    "errorDescription",
    "listeningWebsites"
})
public class IPSCANRESULT {

    @XmlElement(name = "Running")
    protected boolean running;
    @XmlElement(name = "IpRange")
    protected String ipRange;
    @XmlElement(name = "PortRange")
    protected String portRange;
    @XmlElement(name = "Error")
    protected boolean error;
    @XmlElement(name = "ErrorDescription")
    protected String errorDescription;
    @XmlElement(name = "ListeningWebsites")
    protected ArrayOfString listeningWebsites;

    /**
     * Gets the value of the running property.
     * 
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the value of the running property.
     * 
     */
    public void setRunning(boolean value) {
        this.running = value;
    }

    /**
     * Gets the value of the ipRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpRange() {
        return ipRange;
    }

    /**
     * Sets the value of the ipRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpRange(String value) {
        this.ipRange = value;
    }

    /**
     * Gets the value of the portRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortRange() {
        return portRange;
    }

    /**
     * Sets the value of the portRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortRange(String value) {
        this.portRange = value;
    }

    /**
     * Gets the value of the error property.
     * 
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     */
    public void setError(boolean value) {
        this.error = value;
    }

    /**
     * Gets the value of the errorDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the value of the errorDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDescription(String value) {
        this.errorDescription = value;
    }

    /**
     * Gets the value of the listeningWebsites property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getListeningWebsites() {
        return listeningWebsites;
    }

    /**
     * Sets the value of the listeningWebsites property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setListeningWebsites(ArrayOfString value) {
        this.listeningWebsites = value;
    }

}
