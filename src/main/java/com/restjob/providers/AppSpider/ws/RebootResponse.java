
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
 *         &lt;element name="RebootResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "rebootResult"
})
@XmlRootElement(name = "RebootResponse")
public class RebootResponse {

    @XmlElement(name = "RebootResult", required = true)
    protected Result rebootResult;

    /**
     * Gets the value of the rebootResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getRebootResult() {
        return rebootResult;
    }

    /**
     * Sets the value of the rebootResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setRebootResult(Result value) {
        this.rebootResult = value;
    }

}
