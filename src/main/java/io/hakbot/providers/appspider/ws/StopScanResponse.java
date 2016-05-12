
package io.hakbot.providers.appspider.ws;

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
 *         &lt;element name="StopScanResult" type="{http://ntobjectives.com/webservices/}Result"/>
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
    "stopScanResult"
})
@XmlRootElement(name = "StopScanResponse")
public class StopScanResponse {

    @XmlElement(name = "StopScanResult", required = true)
    protected Result stopScanResult;

    /**
     * Gets the value of the stopScanResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getStopScanResult() {
        return stopScanResult;
    }

    /**
     * Sets the value of the stopScanResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setStopScanResult(Result value) {
        this.stopScanResult = value;
    }

}
