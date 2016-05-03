
package com.restjob.providers.appspider.ws;

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
 *         &lt;element name="PingSpiderComResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "pingSpiderComResult"
})
@XmlRootElement(name = "PingSpiderComResponse")
public class PingSpiderComResponse {

    @XmlElement(name = "PingSpiderComResult", required = true)
    protected Result pingSpiderComResult;

    /**
     * Gets the value of the pingSpiderComResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getPingSpiderComResult() {
        return pingSpiderComResult;
    }

    /**
     * Sets the value of the pingSpiderComResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setPingSpiderComResult(Result value) {
        this.pingSpiderComResult = value;
    }

}
