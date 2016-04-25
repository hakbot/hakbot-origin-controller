
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
 *         &lt;element name="IsScanRunningResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "isScanRunningResult"
})
@XmlRootElement(name = "IsScanRunningResponse")
public class IsScanRunningResponse {

    @XmlElement(name = "IsScanRunningResult", required = true)
    protected Result isScanRunningResult;

    /**
     * Gets the value of the isScanRunningResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getIsScanRunningResult() {
        return isScanRunningResult;
    }

    /**
     * Sets the value of the isScanRunningResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setIsScanRunningResult(Result value) {
        this.isScanRunningResult = value;
    }

}
