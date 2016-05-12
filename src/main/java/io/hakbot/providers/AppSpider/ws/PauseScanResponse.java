
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
 *         &lt;element name="PauseScanResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "pauseScanResult"
})
@XmlRootElement(name = "PauseScanResponse")
public class PauseScanResponse {

    @XmlElement(name = "PauseScanResult", required = true)
    protected Result pauseScanResult;

    /**
     * Gets the value of the pauseScanResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getPauseScanResult() {
        return pauseScanResult;
    }

    /**
     * Sets the value of the pauseScanResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setPauseScanResult(Result value) {
        this.pauseScanResult = value;
    }

}
